package cn.zrar.test;

import cn.zrar.hzjk.util.MD5Utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Test4 {

    @Test
    public void test1(){
        String str = "nihaoa";
        str = "你好啊";
        String result1 = DigestUtils.md5Hex(str);
        String result2 = MD5Utils.md5(str);
        System.out.println("result1:"+result1);
        System.out.println("result2:"+result2);


    }

    @Test
    public void test2(){
        //  verifyStr@url
        String httpStr= "sdqwdqwdqwdqwd&http://localhost:8090/obtainData/obtainJsonData";
        String[] split = httpStr.split("&");
        String verifyStr = split[0];
        String url = split[1];
        System.out.println(verifyStr);
        System.out.println(url);
    }


}
