package cn.zrar.hzjk.tool;

import cn.zrar.hzjk.pojo.FailSituation;
import cn.zrar.hzjk.pojo.InterfaceLddj;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 公共静态方法集合
 */
public class CommonTools {

    private static final Logger logger = LoggerFactory.getLogger(CommonTools.class);
    private static ResourceBundle resourceVerify=null;
    private static ResourceBundle resourceHttpUrl=null;
    public static String myIdentity = null;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<String> identityKeyList = new ArrayList<String>();

    public static int timingTaskNum = 0;

    /**
     * 加载发起请求和请求验证相关的properties文件
     */
    static{
        //Properties properties = new Properties();
        //config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可
        resourceVerify = ResourceBundle.getBundle("identity-verification");
        //String verify = resourceVerify.getString("hzs");
        resourceHttpUrl = ResourceBundle.getBundle("http-url");
        //加载properties文件，确定本系统作为哪个区县还是杭州市还是中间库来使用
        ResourceBundle resourceMyIdentity = ResourceBundle.getBundle("my-identity");
        //保存系统身份的静态字符串
        myIdentity = resourceMyIdentity.getString("identity");

        //从identity-verification.properties文件中获取key值，然后将key为zjk和value为空的过滤掉，
        //再将http-url.properties文件中对应的value为空的过滤掉，将剩余的key放到identityKeyList集合里
        Enumeration<String> keys = resourceVerify.getKeys();
        while(keys.hasMoreElements()){
            String key = (String)keys.nextElement();
            try{
                if("zjk".equals(key) || StringUtils.isNullOrEmpty(resourceVerify.getString(key)) || StringUtils.isNullOrEmpty(resourceHttpUrl.getString(key+"Url"))){
                    continue;
                }
            }catch(Exception e){
                //e.printStackTrace();
                continue;
            }
            identityKeyList.add(key);
        }
    }

    /**
     * 通过定时任务上次回传数据到哪个系统，然后切换到下一个系统
     * @param system
     * @return
     */
    public static String obtainDataReturnSystem(String system){
        if(StringUtils.isNullOrEmpty(system)){
            return identityKeyList.get(0);
        }
        for(int i=0; i<identityKeyList.size();i++){
            if(identityKeyList.get(i).equals(system)){
                if(i==(identityKeyList.size()-1)){
                    return identityKeyList.get(0);
                }
                return identityKeyList.get(i+1);
            }
        }
        return null;
    }


    /**
     * 身份验证,通过验证返回访问的来源，并返回数据来源相关字符串
     * @param verifyStr     传递过来的密文
     * @return              数据来源相关字符串（可以判断年数据来源于哪个区县），如果返回值是null，则验证不通过
     */
    public static String IdentityVerfication(String verifyStr){
        if(verifyStr!=null){
            Enumeration<String> keys = resourceVerify.getKeys();
            while(keys.hasMoreElements()){
                String key = (String)keys.nextElement();//调用nextElement方法获得元素
                String verify = DigestUtils.md5Hex(resourceVerify.getString(key));
                if(verify.equals(verifyStr)){
                    return key;
                }
            }
        }
        return null;
    }


    /**
     * 身份验证,通过验证返回访问的来源，并返回数据来源相关字符串
     * @param verifyStr     传递过来的明文
     * @return              数据来源相关字符串（可以判断年数据来源于哪个区县），如果返回值是null，则验证不通过
     */
    public static String IdentityVerficationTest(String verifyStr){
        if(verifyStr!=null){
            Enumeration<String> keys = resourceVerify.getKeys();
            while(keys.hasMoreElements()){
                String key = (String)keys.nextElement();//调用nextElement方法获得元素
                String verify = resourceVerify.getString(key);
                if(verify.equals(verifyStr)){
                    return key;
                }
            }
        }
        return null;
    }


    /**
     *  从properties文件里获取url和 验证字符串  然后将验证字符串用MD5加密 并利用 & 的符号将二者拼接起来，返回拼接后所得到的字符串
     * @param dataObtainSource  要访问哪个服务，值需要跟identity-verification.properties文件里的key值对应 如余杭 则dataObtainSource值为yh
     * @return  MD5加密之后的验证字符串 & url
     */
    public static String obtainHttpStr(String dataObtainSource){
        //dataObtainSource的值为identity-verification.properties文件里的key值
        //1.从identity-verification.properties里获取验证字符串，即dataObtainSource所对应的value值，然后进行MD5加密
        //2.将dataObtainSource的值后面拼接 Url 字符串，然后作为key值从 http-url.properties文件里获取对应的value值
        //3.加密后的验证字符串 与 访问地址拼接来的，中间用字符串 & 连接,如 asdqwdwqdqwdwq&http://localhost:8090/obtainData/obtainJsonData
        if(dataObtainSource!=null) {
            String plaintext = resourceVerify.getString(dataObtainSource);
            String httpUrl = resourceHttpUrl.getString(dataObtainSource + "Url");
            if(plaintext!=null && httpUrl!=null){
                return DigestUtils.md5Hex(plaintext) + "&" +httpUrl;
            }
        }
        return null;
    }

    /**
     *  httpClient，模拟浏览器访问web应用，从而获取想要的数据，和定时任务结合起来使用
     * @param applicationContext    spring容器，用来获取HttpClient和RequestConfig的实例对象
     * @param httpStr   加密后的验证字符串 与 访问地址拼接来的，中间用字符串 & 连接,如 sdqwdqwdqwdqwd&http://localhost:8090/obtainData/obtainJsonData
     * @return  返回response缓冲区里的数据
     */
    public static String QuartzSendHttpPost(ApplicationContext applicationContext, String httpStr, String jsonData){
        //  verifyStr@url
        String[] split = httpStr.split("&");
        String verifyStr = split[0];
        String url = split[1];
        List formparams = new ArrayList<Object>();
        formparams.add(new BasicNameValuePair("httpRequestVerify", verifyStr));
        if(jsonData!=null){
            formparams.add(new BasicNameValuePair("jsonData", jsonData));
        }

        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = (HttpClient) applicationContext.getBean("httpClient");
        RequestConfig requestConfig = (RequestConfig) applicationContext.getBean("requestConfig");
        CloseableHttpResponse response = null;
        String content = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            httpPost.setConfig(requestConfig);
            // 设置请求头消息User-Agent
            //httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
            response = (CloseableHttpResponse)httpClient.execute(httpPost);
/*            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }*/
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("httpclient超时了");
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return content;
    }

    /**
     *  根据json字符串转换为对应的对象集合
     * @param jsonData  json字符串
     * @param T
     * @return
     * @throws IOException
     */
    public static List<?> jsonStrToList(String jsonData, Class<?> T) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, T);
        List<?> list = (List<?>)objectMapper.readValue(jsonData, javaType);
        return list;
    }


    /**
     *  根据json字符串转换为对应的对象
     * @param jsonData  json字符串
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonStrToObject(String jsonData, Class<T> cls) throws IOException {
        return (T)objectMapper.readValue(jsonData, cls);
    }


    /**
     * 将对象转换成json字符串
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String objectToJsonStr(Object object){
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    /**
     * 获取客户端的ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //如果访问时候使用了代理
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //正常通过此方法即可获取客户端ip
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 检查上传数据是否符合要求
     * @param lddj
     * @return
     */
    public static boolean checkUploadData(InterfaceLddj lddj,FailSituation failSituation){
        if(StringUtils.isNullOrEmpty((lddj.getSxfjbh()))){
            logger.warn("上传数据的省信访件编号不能为null:"+ objectToJsonStr(lddj));
            failSituation.setFailReason("上传数据的省信访件编号不能为null:"+ objectToJsonStr(lddj) );
            return false;
        }
        failSituation.setFailId(lddj.getSxfjbh());
        if(lddj.getXfj()==null || lddj.getXfr()==null || lddj.getBlfss()==null ){
            logger.warn("上传数据"+lddj.getSxfjbh()+ ":的xfj,xfr或blfs不能为null");
            failSituation.setFailReason("上传数据"+lddj.getSxfjbh()+ ":的xfj,xfr或blfs不能为null");
            return false;
        }
        if(lddj.getBlfss().size()!=1){
            logger.warn("上传数据"+lddj.getSxfjbh()+ ":的blfs上传了" + lddj.getBlfss().size() + "个，应该上传1个");
            failSituation.setFailReason("上传数据"+lddj.getSxfjbh()+ ":的blfs上传了" + lddj.getBlfss().size() + "个，应该上传1个");
            return false;
        }
        return true;
    }


}
