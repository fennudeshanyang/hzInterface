package cn.zrar.hzjk.job;

import cn.zrar.hzjk.controller.ObtainDataController;
import cn.zrar.hzjk.pojo.FailSituation;
import cn.zrar.hzjk.pojo.JsonResult;
import cn.zrar.hzjk.pojo.SjBlResult;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBackJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(ObtainDataController.class);
    private static String system = null;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       if(!"zjk".equals(CommonTools.myIdentity)){
            logger.warn("系统身份验证有误,数据回传定时任务启动失败");
            return;
        }
        system = CommonTools.obtainDataReturnSystem(system);
        if(system==null){
            logger.warn("回传数据接收系统切换错误,数据回传定时任务启动失败");
            return;
        }

        /**
         *  1.从定时任务中获取spring容器
         *  2.从容器中获取Mapper对象
         *  3.通过Mapper对象执行任务操作
         */
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getJobDetail().getJobDataMap().get("applicationContext");
        ObtainDataService obtainDataService = (ObtainDataService)applicationContext.getBean("obtainDataServiceImpl");
        //获取本次定时任务所要回传的数据
        Map<String,String> map = new HashMap<String,String>();
        map.put("sjss",system);
        List<SjBlResult> backDataList = obtainDataService.findBackData(map);
        if(backDataList.size()==0){
            logger.warn("中间库中并未获取到符合回传条件的" + system + "数据");
            return;
        }

        //修改本次定时任务所要回传的数据在中间库的数据交互状态
        for(SjBlResult sjBlResult : backDataList){
            map.put("id",sjBlResult.getId());
            map.put("symbol","+");
            obtainDataService.updateBackDataSjjhzt(map);
        }
        //将list集合转换为json字符串
        String jsonData = CommonTools.objectToJsonStr(backDataList);
        if(jsonData==null){
            logger.warn("数据回传定时任务，集合对象转换json串异常");
            return;
        }
        //获取发起http请求所需要的字符串
        String httpStr = CommonTools.obtainHttpStr(system);
        logger.warn("中间库中成功获取到符合回传条件的" + system + "数据:" + jsonData + ",开始回传数据");
        //发起httpClient请求
        String jsonContent = CommonTools.QuartzSendHttpPost(applicationContext, httpStr, jsonData);
        JsonResult jsonResult = null;
        //对httpClient请求返回的结果进行处理    1.有结果返回         2.连接超时
        if(jsonContent!=null){//1.有结果返回
            logger.warn("本次回传数据结果:"+ jsonContent);
            try {
                jsonResult = CommonTools.jsonStrToObject(jsonContent, JsonResult.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.warn("数据回传定时任务中,httpclient返回的json数据转换对象异常");
                return;
            }
            //如果交互成功，则将对应的数据按照返回的结果打上对应的上传状态
            if(Constant.SUCCESS.equals(jsonResult.getCode())){
                if(!StringUtils.isNullOrEmpty(jsonResult.getSuccessStr())){
                    String[] successArray = jsonResult.getSuccessStr().split(",");
                    map.put("sjjhzt","20");
                    for(String id : successArray){
                        map.put("id",id);
                        obtainDataService.updateBackDataSjjhzt(map);
                    }
                }
                if(!StringUtils.isNullOrEmpty(jsonResult.getConflictStr())){
                    String[] conflictArray = jsonResult.getConflictStr().split(",");
                    map.put("sjjhzt","30");
                    for(String id : conflictArray){
                        map.put("id",id);
                        obtainDataService.updateBackDataSjjhzt(map);
                    }
                }

                //要注释的部分================
/*                if(!StringUtils.isNullOrEmpty(jsonResult.getFailStr())){
                    String[] failArray = jsonResult.getFailStr().split(",");
                    map.put("sjjhzt","40");
                    for(String id : failArray){
                        map.put("id",id);
                        obtainDataService.updateBackDataSjjhzt(map);
                    }
                }*/
                //=============================
                if(jsonResult.getFailSituationList()!=null && jsonResult.getFailSituationList().size()>0){
                    map.put("sjjhzt","40");
                    for(FailSituation failSituation : jsonResult.getFailSituationList()){
                        //TODO  对保存失败的数据的信息进行相对应的操作
                        if(StringUtils.isNullOrEmpty(failSituation.getFailId())){
                            logger.warn("回传数据中主键为null的值:" +failSituation.getFailReason());
                            continue;
                        }
                        logger.warn(failSituation.getFailId()+":" +failSituation.getFailReason());
                        map.put("id",failSituation.getFailId());
                        obtainDataService.updateBackDataSjjhzt(map);
                    }
                }
            }else{//如果交互失败，则将数据的状态还原
                //修改本次定时任务所要回传的数据在中间库的数据交互状态,即将状态恢复成定时任务执行前的状态
                map.put("symbol","-");
                recoverySjjhzt(backDataList,map,obtainDataService);
                logger.warn("数据回传任务中,httpclient交互失败:"+jsonResult.getMsg());
            }
        }else{//2.连接超时的处理，则将数据的状态还原
            //修改本次定时任务所要回传的数据在中间库的数据交互状态,即将状态恢复成定时任务执行前的状态
            map.put("symbol","-");
            recoverySjjhzt(backDataList,map,obtainDataService);
            logger.warn("数据回传定时任务中,httpclient连接超时,回传数据的数据交互状态恢复成定时任务执行前的状态");
        }
    }

    /**
     *  将中间库中本次交互的数据的数据交互状态还原到定时任务执行之前的状态
     * @param backDataList
     * @param map
     * @param obtainDataService
     */
    private void recoverySjjhzt(List<SjBlResult> backDataList, Map map, ObtainDataService obtainDataService){
        for(SjBlResult sjBlResult : backDataList){
            map.put("id",sjBlResult.getId());
            obtainDataService.updateBackDataSjjhzt(map);
        }
    }


}
