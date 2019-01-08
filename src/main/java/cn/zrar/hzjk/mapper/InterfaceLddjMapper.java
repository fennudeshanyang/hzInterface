package cn.zrar.hzjk.mapper;

import cn.zrar.hzjk.pojo.InterfaceLddj;
import com.github.abel533.mapper.Mapper;
import org.springframework.stereotype.Repository;

@Repository("interfaceLddjMapperDao")
public interface InterfaceLddjMapper extends Mapper<InterfaceLddj> {

    /**
     * 根据字段判断该信访件是否已经在中间库存在
     * @param lddj
     * @return  0：不存在   大于0的数字则表示已存在
     */
    //public Integer existCount(InterfaceLddj lddj);

}
