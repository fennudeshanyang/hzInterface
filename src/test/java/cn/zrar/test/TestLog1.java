package cn.zrar.test;



import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog1 {

    /**
     * 两种方式都可以获取logger对象,推荐使用LoggerFactory的方式
     */
    //import org.apache.log4j.Logger;
    //private static Logger log = Logger.getLogger(TestLog1.class.getClass());

    //import org.slf4j.Logger;
    //private static final Logger logger = LoggerFactory.getLogger(TestLog1.class);

    @Test
    public void test1(){

        //logger.info("你好啊info");
        //logger.error("你好啊eror");
        //logger.warn("你好啊warn");
        //logger.debug("你好啊debug");

    }



}