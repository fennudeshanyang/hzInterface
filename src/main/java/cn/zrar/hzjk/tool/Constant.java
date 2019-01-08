package cn.zrar.hzjk.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * 常数类
 */
public class Constant {
    /**
     * 接口方案处理成功
     */
    public static final String SUCCESS = "0001";

    /**
     *  不存在的接口服务
     */
    public static final String NONEXISTENT_SERVICE = "0301";

    /**
     *  没有访问该接口的权限
     */
    public static final String INSUFFICIENT_PRIVILEGES= "0304";

    /**
     *  接口参数格式错误
     */
    public static final String FORMAT_ERROR = "0202";


    /**
     * 未知错误(接口执行异常)
     */
    public static final String UNKOWN_ERROR = "04";


    /**
     * 业务处理异常
     */
    public static final String BUSINESS_EXCEPTION = "0404";

    /**
     * 系统繁忙，升级维护中
     */
    public static final String BUSY_SYSTEM = "-1";
    /**
     * 请求过于频繁，请稍后访问
     */
    public static final String FREQUENT_REQUEST="-2";

    /**
     *
     */
    public static final Map<String, String> map = new HashMap<String, String>();

    static {
        map.put(Constant.BUSY_SYSTEM, "系统繁忙，升级维护中");
        map.put(Constant.SUCCESS, "成功");
        map.put(Constant.UNKOWN_ERROR, "未知错误");
        map.put(Constant.BUSINESS_EXCEPTION, "业务处理错误");
        map.put(Constant.FREQUENT_REQUEST, "请求过于频繁，请稍后访问");
        map.put(Constant.INSUFFICIENT_PRIVILEGES,"没有访问该接口的权限");
        map.put(Constant.FORMAT_ERROR,"接口参数格式错误");
        map.put(Constant.NONEXISTENT_SERVICE,"不存在的接口服务");
    }







}
