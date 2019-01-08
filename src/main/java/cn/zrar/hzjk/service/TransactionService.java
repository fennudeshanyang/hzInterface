package cn.zrar.hzjk.service;

import cn.zrar.hzjk.pojo.InterfaceLddj;

public interface TransactionService {

    /**
     * 针对单个信访件进行四个表的表记录插入操作，若某一个表的插入操作失败了，则回滚这四个插入操作
     * @param lddj
     * @return  四个表都插入成功过返回true，否则false
     * @throws Exception
     */
    public boolean saveUploadData(InterfaceLddj lddj) throws Exception;

}
