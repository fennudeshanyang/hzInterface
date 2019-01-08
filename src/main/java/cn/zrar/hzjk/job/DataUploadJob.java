package cn.zrar.hzjk.job;

import cn.zrar.hzjk.controller.ObtainDataController;
import cn.zrar.hzjk.pojo.FailSituation;
import cn.zrar.hzjk.pojo.InterfaceLddj;
import cn.zrar.hzjk.pojo.JsonResult;
import cn.zrar.hzjk.service.ObtainDataService;
import cn.zrar.hzjk.tool.CommonTools;
import cn.zrar.hzjk.tool.Constant;
import com.mysql.jdbc.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.io.IOException;
import java.util.*;

public class DataUploadJob extends QuartzJobBean {
    //判断该任务是否正在执行,trues即有同一类型任务正在执行false则没有
    private static boolean isRun = false;
    private static int timingTaskNum = 0;
    private static final Logger logger = LoggerFactory.getLogger(ObtainDataController.class);

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if("zjk".equals(CommonTools.myIdentity)){
            logger.warn("系统身份验证有误,数据上传定时任务启动失败");
            return;
        }
        timingTaskNum++;
        if(timingTaskNum>=15){
            timingTaskNum--;
            System.out.println("数据上传定时任务同时执行个数达到了" + timingTaskNum + ",超出限额,本次不执行");
            return;
        }

        //如果触发器监控到时间已经到了，则开始回调该方法
        /**
         *  1.从定时任务中获取spring容器
         *  2.从容器中获取Mapper对象
         *  3.通过Mapper对象执行任务操作
         */
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getJobDetail().getJobDataMap().get("applicationContext");
        ObtainDataService obtainDataService = (ObtainDataService)applicationContext.getBean("obtainDataServiceImpl");
        //获取本次定时任务所要回传的数据
        List<InterfaceLddj> lddjList = obtainDataService.findUploadData(null);
        if(lddjList.size()==0){
            logger.warn("业务库中并未获取到符合上传条件的数据");
            return;
        }
        Map<String,String> map = new HashMap<String,String>();
        //完善本次定时任务所要上传的数据并修改它在业务库的数据交互状态
        for(InterfaceLddj lddj : lddjList){
            obtainDataService.ywkRefineUploadData(lddj);
            map.put("sxfjbh", lddj.getSxfjbh());
            map.put("symbol","+");
            obtainDataService.updateUploadDataSjjhzt(map);
        }
        //将list集合转换为json字符串
        String jsonData = CommonTools.objectToJsonStr(lddjList);
        if(jsonData==null){
            logger.warn("数据上传定时任务，集合对象转换json串异常");
            return;
        }
        //获取发起http请求所需要的字符串
        String httpStr = CommonTools.obtainHttpStr("zjk");
        logger.warn("业务库中成功获取到符合上传条件的数据"+lddjList.size()+"个:" + jsonData + ",开始上传数据");
        //发起httpClient请求
        String jsonContent = CommonTools.QuartzSendHttpPost(applicationContext, httpStr, jsonData);
        JsonResult jsonResult = null;
        //对httpClient请求返回的结果进行处理    1.有结果返回         2.连接超时
        if(jsonContent!=null){//1.有结果返回
            logger.warn("本次上传数据结果:"+ jsonContent);
            try {
                jsonResult = CommonTools.jsonStrToObject(jsonContent, JsonResult.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.warn("数据上传定时任务中,httpclient返回的json数据转换对象异常");
                return;
            }
            //如果交互成功，则将对应的数据按照返回的结果打上对应的上传状态
            if(Constant.SUCCESS.equals(jsonResult.getCode())){
                if(!StringUtils.isNullOrEmpty(jsonResult.getSuccessStr())){
                    String[] successArray = jsonResult.getSuccessStr().split(",");
                    map.put("sjjhzt","20");
                    for(String sxfjbh : successArray){
                        map.put("sxfjbh",sxfjbh);
                        obtainDataService.updateUploadDataSjjhzt(map);
                    }
                }
                if(!StringUtils.isNullOrEmpty(jsonResult.getConflictStr())){
                    String[] conflictArray = jsonResult.getConflictStr().split(",");
                    map.put("sjjhzt","30");
                    for(String sxfjbh : conflictArray){
                        map.put("sxfjbh",sxfjbh);
                        obtainDataService.updateUploadDataSjjhzt(map);
                    }
                }
/*                if(!StringUtils.isNullOrEmpty(jsonResult.getFailStr())){
                    String[] failArray = jsonResult.getFailStr().split(",");
                    map.put("sjjhzt","40");
                    for(String sxfjbh : failArray){
                        map.put("sxfjbh",sxfjbh);
                        obtainDataService.updateUploadDataSjjhzt(map);
                    }
                }*/
                if(jsonResult.getFailSituationList()!=null && jsonResult.getFailSituationList().size()>0){
                    map.put("sjjhzt","40");
                    for(FailSituation failSituation : jsonResult.getFailSituationList()){
                        //TODO  对保存失败的数据的信息进行相对应的操作
                        if(StringUtils.isNullOrEmpty(failSituation.getFailId())){
                            logger.warn("上传数据中sxfjbh为null的值:" +failSituation.getFailReason());
                            continue;
                        }
                        logger.warn(failSituation.getFailId()+":" +failSituation.getFailReason());
                        map.put("sxfjbh",failSituation.getFailId());
                        if(StringUtils.isNullOrEmpty(failSituation.getFailReason())){
                            map.put("sjjhsbyy","");
                        }else{
                            map.put("sjjhsbyy",failSituation.getFailReason());
                        }
                        obtainDataService.updateUploadDataSjjhzt(map);
                    }
                }
            }else{//如果交互失败，则将数据的状态还原
                //修改本次定时任务所要上传的数据在业务库的数据交互状态,即将状态恢复成定时任务执行前的状态
                map.put("symbol","-");
                recoverySjjhzt(lddjList,map,obtainDataService);
                logger.warn("数据上传任务中,httpclient交互失败:"+jsonResult.getMsg());
            }
        }else{//2.连接超时的处理，则将数据的状态还原
            //修改本次定时任务所要上传的数据在业务库的数据交互状态,即将状态恢复成定时任务执行前的状态
            map.put("symbol","-");
            recoverySjjhzt(lddjList,map,obtainDataService);
            logger.warn("数据上传定时任务中,httpclient连接超时,上传数据的数据交互状态恢复成定时任务执行前的状态");
        }
        timingTaskNum--;
    }

    /**
     *  将中间库中本次交互的数据的数据交互状态还原到定时任务执行之前的状态
     * @param lddjList
     * @param map
     * @param obtainDataService
     */
    private void recoverySjjhzt(List<InterfaceLddj> lddjList, Map map, ObtainDataService obtainDataService){
        for(InterfaceLddj lddj : lddjList){
            map.put("sxfjbh",lddj.getSxfjbh());
            obtainDataService.updateUploadDataSjjhzt(map);
        }
    }



}
