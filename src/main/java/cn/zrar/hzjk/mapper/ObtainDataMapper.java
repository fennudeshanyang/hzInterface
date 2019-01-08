package cn.zrar.hzjk.mapper;

import cn.zrar.hzjk.pojo.InterfaceLddj;
import cn.zrar.hzjk.pojo.SjBlResult;

import java.util.List;
import java.util.Map;

public interface ObtainDataMapper {

    //======================================业务库上传数据给中间库的整个过程中用到的==================================================
    /**
     * 从业务库里找出要上传给中间库的数据
     * @param map
     * @return
     */
    public List<InterfaceLddj> findUploadData(Map map);

    /**
     *  根据map里的sxfjbh值修改业务库XF_YWDJ表里对应数据的数据交互状态，即值加1或减1或改为特定的值
     * @param map
     */
    public void updateUploadDataSjjhzt(Map map);

    /**
     * 根据字段判断该条上传数据是否已经在中间库存在
     * @param lddj
     * @return  0：不存在   大于0的数字则表示已存在
     */
    public Integer upLoadDataExistCount(InterfaceLddj lddj);

    /**
     * 根据业务表的内容分类，查找省系统平台的内容分类编码表里对应的值
     * @param lddj
     */
    public String findFlidByNrfl(InterfaceLddj lddj);

    //======================================中间库回传数据给业务库的整个过程中用到的==================================================
    /**
     * 从中间库里找出要返回给业务库的数据
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
     * 根据字段判断该条回传数据是否已经在业务库存在
     * @param sjBlResult
     * @return  0：不存在   大于0的数字则表示已存在
     */
    public Integer backDataExistCount(SjBlResult sjBlResult);


}
