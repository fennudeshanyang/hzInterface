package cn.zrar.hzjk.service;

import cn.zrar.hzjk.mapper.*;
import cn.zrar.hzjk.pojo.InterfaceLddj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private InterfaceLddjMapper interfaceLddjMapper;
    @Autowired
    private InterfaceXfjMapper interfaceXfjMapper;
    @Autowired
    private InterfaceXfrMapper interfaceXfrMapper;
    @Autowired
    private InterfaceBlfsMapper interfaceBlfsMapper;

    @Override
    public boolean saveUploadData(InterfaceLddj lddj) throws Exception {
        interfaceLddjMapper.insert(lddj);
        interfaceXfjMapper.insert(lddj.getXfj());
        interfaceXfrMapper.insert(lddj.getXfr());
        interfaceBlfsMapper.insert(lddj.getBlfss().get(0));
        return true;
    }
}
