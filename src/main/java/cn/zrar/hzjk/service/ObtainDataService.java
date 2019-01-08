package cn.zrar.hzjk.service;

import cn.zrar.hzjk.pojo.*;

import java.util.List;
import java.util.Map;

public interface ObtainDataService {

    //=========================================业务库上传数据给中间库的整个过程中需要用到的方法============================================
    /**
     * 从业务库查找需要上传的数据,并封装到集合对象里
     * @param map
     * @return
     */
    public List<InterfaceLddj> findUploadData(Map map);

    /**
     *  在业务库将从业务库查到的要上传的数据进行一定程度的转化，以满足上传的需求
     * @param lddj
     * @return
     */
    public Boolean ywkRefineUploadData(InterfaceLddj lddj);

    /**
     * 在中间库将从业务库查到的要上传的数据再进行一定程度的转化，以满足上传的需求
     * @param lddj
     * @param failSituation
     * @return
     */
    public Boolean zjkRefineUploadData(InterfaceLddj lddj,FailSituation failSituation);

    /**
     * 根据map里的sxfjbh值修改业务库XF_YWDJ表里对应数据的数据交互状态，即值加1或减1或改为特定的值
     * @param map
     */
    public void updateUploadDataSjjhzt(Map map);

    /**
     *  由中间库处理上传的数据，将数据保存到中间库，并将保存成功和失败以及主键冲突的数据封装起来
     *  1.将上传的json数据转换为集合对象，若转换失败返回结果集
     *  2.遍历转换成功的集合对象，一一判断是否主键冲突，若不冲突则进行保存操作，若保存不成功则针对这一条信访件的数据进行数据保存回滚，然后继续下一条数据的判断保存的操作
     *  3.返回结果集
     * @param jsonData  上传的json数据字符串
     * @param jsonResult 结果集的实例对象
     * @return 添加了处理结果的结果集的实例对象
     */
    public JsonResult dealUploadData(String jsonData, JsonResult jsonResult);



    //=========================================中间库回传数据给业务库的整个过程中需要用到的方法============================================
    /**
     * 从中间库查找需要回传的数据,并封装到SjBlResult对象集合里
     * @param map
     * @return
     */
    public List<SjBlResult> findBackData(Map map);

    /**
     * 根据map里的id值修改中间库SJBLRESULT对应数据的数据交互状态，即值加1或减1或改为特定的值
     * @param map
     */
    public void updateBackDataSjjhzt(Map map);

    /**
     *  由业务库处理回传的数据，将数据保存到业务库，并将保存成功和失败以及主键冲突的数据封装起来
     *  1.将回传的json数据转换为集合对象，若转换失败返回结果集
     *  2.遍历转换成功的集合对象，一一判断是否主键冲突，若不冲突则进行保存操作，若保存不成功则继续下一条数据的判断保存的操作
     *  3.返回结果集
     * @param jsonData  上传的json数据字符串
     * @param jsonResult 结果集的实例对象
     * @return 添加了处理结果的结果集的实例对象
     */
    public JsonResult dealBackData(String jsonData, JsonResult jsonResult);


}
