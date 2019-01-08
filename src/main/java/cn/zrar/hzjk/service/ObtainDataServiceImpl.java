package cn.zrar.hzjk.service;

import cn.zrar.hzjk.controller.ObtainDataController;
import cn.zrar.hzjk.mapper.*;
import cn.zrar.hzjk.pojo.*;
import cn.zrar.hzjk.tool.CommonTools;
import cn.zrar.hzjk.tool.Constant;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ObtainDataServiceImpl implements ObtainDataService {
    @Autowired
    private ObtainDataMapper obtainDataMapper;
    @Autowired
    private TransactionService transactionService;
    @Autowired SjBlResultMapper sjBlResultMapper;

    private static final Logger logger = LoggerFactory.getLogger(ObtainDataServiceImpl.class);

    //=========================================业务库上传数据给中间库的整个过程中需要用到的方法============================================
    /**
     * 从业务库查找需要上传的数据,并封装到集合对象里
     * @param map
     * @return
     */
    @Override
    public List<InterfaceLddj> findUploadData(Map map){
        return obtainDataMapper.findUploadData(map);
    }

    /**
     *  在业务库将从业务库查到的要上传的数据进行一定程度的转化，以满足上传的需求
     * @param lddj
     * @return
     */
    @Override
    public Boolean ywkRefineUploadData(InterfaceLddj lddj) {
        //lddj的uuid
        lddj.setStoreid(UUID.randomUUID().toString());
        //xfj的uuid
        lddj.getXfj().setXfjid(UUID.randomUUID().toString());
        //xfr的uuid
        lddj.getXfr().setXfrid(UUID.randomUUID().toString());
        lddj.getXfr().setXfjid(lddj.getXfj().getXfjid());
        //blfs的uuid
        List<InterfaceBlfs> blfss = lddj.getBlfss();
        for(InterfaceBlfs blfs : blfss){
            blfs.setBlfsid(UUID.randomUUID().toString());
            blfs.setXfjid(lddj.getXfj().getXfjid());
        }
        return true;
    }

    /**
     * 在中间库将从业务库查到的要上传的数据再进行一定程度的转化，以满足上传的需求
     * @param lddj
     * @param failSituation
     * @return
     */
    @Override
    public Boolean zjkRefineUploadData(InterfaceLddj lddj,FailSituation failSituation) {
        if(StringUtils.isNullOrEmpty(lddj.getNrfl())){
            logger.warn("上传数据"+lddj.getSxfjbh()+ "的内容分类值不能为null");
            failSituation.setFailReason("上传数据"+lddj.getSxfjbh()+ "的内容分类值不能为null");
            return false;
        }
        try {
            String flid = obtainDataMapper.findFlidByNrfl(lddj);
            if (StringUtils.isNullOrEmpty(flid)) {
                flid = "999901";
            }
            lddj.setNrfl(flid);
            lddj.getXfj().setNrfldm(flid);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            logger.warn("上传数据"+lddj.getSxfjbh()+ "的内容分类在中间库转换过程中出现异常");
            failSituation.setFailReason("上传数据"+lddj.getSxfjbh()+ "的内容分类值不能为null");
            return false;
        }
    }

    /**
     *  根据map里的sxfjbh值修改业务库XF_YWDJ表里对应数据的数据交互状态，即值加1或减1或改为特定的值
     * @param map
     */
    @Override
    public void updateUploadDataSjjhzt(Map map) {
        obtainDataMapper.updateUploadDataSjjhzt(map);
    }

    /**
     *  由中间库处理上传的数据，将数据保存到中间库，并将保存成功和失败以及主键冲突的数据封装起来
     *  1.将上传的json数据转换为集合对象，若转换失败返回结果集
     *  2.遍历转换成功的集合对象，一一判断是否主键冲突，若不冲突则进行保存操作，若保存不成功则针对这一条信访件的数据进行数据保存回滚，然后继续下一条数据的判断保存的操作
     *  3.返回结果集
     * @param jsonData  上传的json数据字符串
     * @param jsonResult 结果集的实例对象
     * @return 添加了处理结果的结果集的实例对象
     */
    @Override
    public JsonResult dealUploadData(String jsonData, JsonResult jsonResult) {
        List<InterfaceLddj> lddjList = null;
        //将jsonData数据转换为集合对象
        try {
            lddjList =  (List<InterfaceLddj>) CommonTools.jsonStrToList(jsonData, InterfaceLddj.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传数据"+ jsonData + "由json字符转换成对象报错，即接口参数格式错误" +e.getMessage());
            jsonResult.setCode(Constant.FORMAT_ERROR);
            jsonResult.setMsg(Constant.map.get(Constant.FORMAT_ERROR));
            return jsonResult;
        }
        String successStr = "";
        String conflictStr = "";
        //String failStr = "";
        Integer existCount = null;
        List<FailSituation> failSituationList = new ArrayList<FailSituation>();
        for(int i=0; i < lddjList.size(); i++){
            InterfaceLddj lddj = lddjList.get(i);
            FailSituation failSituation = new FailSituation();
            if(!CommonTools.checkUploadData(lddj,failSituation)){       //1.failStr -->true
                //failStr = failStr + lddj.getSxfjbh() + ",";
                failSituationList.add(failSituation);
                continue;
            }
            try {
                existCount = obtainDataMapper.upLoadDataExistCount(lddj);
            }catch (Exception e){       //2.failStr -->true
                e.printStackTrace();
                //failStr = failStr + lddj.getSxfjbh() + ",";
                logger.error("上传数据的主键:"+ lddj.getSxfjbh() +e.getMessage());
                logger.warn("上传数据"+lddj.getSxfjbh()+ ":在查询其在中间库是否已存在的时候出现异常");
                failSituation.setFailReason("上传数据"+lddj.getSxfjbh()+ ":在查询其在中间库是否已存在的时候出现异常。"+ e.getMessage());
                failSituationList.add(failSituation);
                continue;
            }
            if(existCount>0){
                conflictStr = conflictStr + lddj.getSxfjbh() + ",";
                continue;
            }

            //中间库进一步完善上传数据
            Boolean bool = zjkRefineUploadData(lddj,failSituation);
            if(!bool){  //3.failStr -->true
                //failStr = failStr + lddj.getSxfjbh() + ",";
                failSituationList.add(failSituation);
                continue;
            }

            try {
                transactionService.saveUploadData(lddj);
                successStr = successStr + lddj.getSxfjbh() + ",";
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("上传数据的主键:"+ lddj.getSxfjbh() + "在往中间库插值时出现异常。" +e.getMessage());
                failSituation.setFailReason("上传数据的主键:"+ lddj.getSxfjbh() + "在往中间库插值时出现异常。" +e.getMessage());
                failSituationList.add(failSituation);
            }
        }
        if(!"".equals(successStr)){
            successStr = successStr.substring(0,successStr.length()-1);
            jsonResult.setSuccessStr(successStr);
        }
        if(!"".equals(conflictStr)){
            conflictStr = conflictStr.substring(0,conflictStr.length()-1);
            jsonResult.setConflictStr(conflictStr);
        }
        if(failSituationList.size()>0){
            jsonResult.setFailSituationList(failSituationList);
        }
        jsonResult.setCode(Constant.SUCCESS);
        jsonResult.setMsg(Constant.map.get(Constant.SUCCESS));
        return jsonResult;
    }



    //=========================================中间库回传数据给业务库的整个过程中需要用到的方法============================================
    /**
     * 从中间库查找需要回传的数据,并封装到SjBlResult对象集合里
     * @param map
     * @return
     */
    @Override
    public List<SjBlResult> findBackData(Map map) {
        return obtainDataMapper.findBackData(map);
    }

    /**
     * 根据sjBlResult对象里的id主键修改中间库的数据交互状态，即值加1或减1
     * @param map
     */
    @Override
    public void updateBackDataSjjhzt(Map map) {
        obtainDataMapper.updateBackDataSjjhzt(map);
    }

    /**
     *  由业务库处理回传的数据，将数据保存到业务库，并将保存成功和失败以及主键冲突的数据封装起来
     *  1.将回传的json数据转换为集合对象，若转换失败返回结果集
     *  2.遍历转换成功的集合对象，一一判断是否主键冲突，若不冲突则进行保存操作，若保存不成功则继续下一条数据的判断保存的操作
     *  3.返回结果集
     * @param jsonData  上传的json数据字符串
     * @param jsonResult 结果集的实例对象
     * @return 添加了处理结果的结果集的实例对象
     */
    @Override
    public JsonResult dealBackData(String jsonData, JsonResult jsonResult) {
        List<SjBlResult> sjBlResultList = null;
        //将jsonData数据转换为集合对象
        try {
            sjBlResultList =  (List<SjBlResult>) CommonTools.jsonStrToList(jsonData, SjBlResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("回传数据"+ jsonData + "由json字符转换成对象报错，即接口参数格式错误" +e.getMessage());
            jsonResult.setCode(Constant.FORMAT_ERROR);
            jsonResult.setMsg(Constant.map.get(Constant.FORMAT_ERROR));
            return jsonResult;
        }
        String successStr = "";
        String conflictStr = "";
        Integer existCount = null;
        List<FailSituation> failSituationList = new ArrayList<FailSituation>();
        for(int i=0; i < sjBlResultList.size(); i++){
            SjBlResult sjBlResult = sjBlResultList.get(i);
            FailSituation failSituation = new FailSituation();
            try {
                existCount = obtainDataMapper.backDataExistCount(sjBlResult);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("回传数据"+sjBlResult.getId()+ ":在查询其在业务库是否已存在的时候出现异常。"+ e.getMessage());
                failSituation.setFailReason("回传数据"+sjBlResult.getId()+ ":在查询其在业务库是否已存在的时候出现异常。"+ e.getMessage());
                failSituationList.add(failSituation);
                continue;
            }
            if(existCount>0){
                conflictStr = conflictStr + sjBlResult.getId() + ",";
                continue;
            }
            try {
                sjBlResultMapper.insert(sjBlResult);
                successStr = successStr + sjBlResult.getId() + ",";
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("回传数据的主键:"+ sjBlResult.getId() + ",在往业务库插值时出现异常" +e.getMessage());
                failSituation.setFailReason("回传数据的主键:"+ sjBlResult.getId() + ",在往业务库插值时出现异常" +e.getMessage());
                failSituationList.add(failSituation);
            }
        }
        if(!"".equals(successStr)){
            successStr = successStr.substring(0,successStr.length()-1);
            jsonResult.setSuccessStr(successStr);
        }
        if(!"".equals(conflictStr)){
            conflictStr = conflictStr.substring(0,conflictStr.length()-1);
            jsonResult.setConflictStr(conflictStr);
        }
        if(failSituationList.size()>0){
            jsonResult.setFailSituationList(failSituationList);
        }
        jsonResult.setCode(Constant.SUCCESS);
        jsonResult.setMsg(Constant.map.get(Constant.SUCCESS));
        return jsonResult;
    }


}
