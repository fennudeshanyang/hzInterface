package cn.zrar.hzjk.controller;

import cn.zrar.hzjk.pojo.JsonResult;
import cn.zrar.hzjk.service.ObtainDataService;
import cn.zrar.hzjk.tool.CommonTools;
import cn.zrar.hzjk.tool.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/obtainData")
public class ObtainDataController {

    private static final Logger logger = LoggerFactory.getLogger(ObtainDataController.class);

    @Autowired
    private ObtainDataService obtainDataService;

    /**
     * 业务库端提供的接口（通过定时任务从中间库获取回传的数据，然后通过业务库端的项目提供的接口将数据保存到业务库里）
     * @param res
     * @param req
     * @param httpRequestVerify
     * @param jsonData
     */
    @RequestMapping("/obtainBackData")
    public void backData(HttpServletResponse res, HttpServletRequest req, String httpRequestVerify, String jsonData){
        JsonResult jsonResult = new JsonResult();
        //接口服务验证，通过系统身份验证该系统是否提供该接口服务
        if("zjk".equals(CommonTools.myIdentity)){
            jsonResult.setCode(Constant.NONEXISTENT_SERVICE);
            jsonResult.setMsg(Constant.map.get(Constant.NONEXISTENT_SERVICE));
            logger.warn(CommonTools.getIpAddr(req)+"访问本系统不存在的接口服务(将回传数据保存到业务库(/obtainData/obtainBackData)的接口服务)");
        }else if(CommonTools.IdentityVerfication(httpRequestVerify) == null){
            //权限验证，若没有权限，则拒绝提供接口服务
            jsonResult.setCode(Constant.INSUFFICIENT_PRIVILEGES);
            jsonResult.setMsg(Constant.map.get(Constant.INSUFFICIENT_PRIVILEGES));
            logger.warn("权限验证不通过，系统拒绝提给"+CommonTools.getIpAddr(req)+"提供接口服务(将回传数据保存到业务库(/obtainData/obtainBackData)的接口服务)");
        }else{
            //权限验证通过，进行数据保存
            jsonResult = obtainDataService.dealBackData(jsonData, jsonResult);

        }
        res.setContentType("text/html;charset=UTF-8");
        try {
            res.getWriter().write(CommonTools.objectToJsonStr(jsonResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 中间库端提供的接口（通过定时任务从业务库获取上传的数据，然后通过中间库端的项目提供的接口将数据保存到中间库里）
     * @param res
     * @param req
     * @param httpRequestVerify
     * @param jsonData
     */
    @RequestMapping("/obtainUploadData")
    public void uploadData(HttpServletResponse res, HttpServletRequest req, String httpRequestVerify, String jsonData){
        JsonResult jsonResult = new JsonResult();
        //接口服务验证，通过系统身份验证该系统是否提供该接口服务
        if(!"zjk".equals(CommonTools.myIdentity) && false){
            jsonResult.setCode(Constant.NONEXISTENT_SERVICE);
            jsonResult.setMsg(Constant.map.get(Constant.NONEXISTENT_SERVICE));
            logger.warn(CommonTools.getIpAddr(req)+"访问本系统不存在的接口服务(将上传数据保存到中间库(/obtainData/obtainUploadData)的接口服务)");
        }else if(CommonTools.IdentityVerfication(httpRequestVerify) == null  && false){
            //权限验证，若没有权限，则拒绝提供接口服务
            jsonResult.setCode(Constant.INSUFFICIENT_PRIVILEGES);
            jsonResult.setMsg(Constant.map.get(Constant.INSUFFICIENT_PRIVILEGES));
            logger.warn("权限验证不通过，系统拒绝提给"+CommonTools.getIpAddr(req)+"提供接口服务(将上传数据保存到中间库(/obtainData/obtainUploadData)的接口服务)");
        }else{
            //权限验证通过，进行数据保存
            jsonResult = obtainDataService.dealUploadData(jsonData, jsonResult);
        }
        res.setContentType("text/html;charset=UTF-8");
        try {
            res.getWriter().write(CommonTools.objectToJsonStr(jsonResult));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


















//=================================================================================================================
/*

    @RequestMapping("/obtainJsonData")
    public void jsonData(String callback, HttpServletResponse res, String verStr){
        List<Ywdj> data = obtainDataService.getData();

        String jsonDatas = "你好啊";

        //CommonTools.IdentityVerficationTest(verStr);




        Map<String,String> map = new HashMap<String,String>();
        map.put("xzqh","123456");
        List<InterfaceLddj> lddjs = obtainDataService.findUploadData(map);

        //将list集合转换为json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json="";
        try {
            json = objectMapper.writeValueAsString(lddjs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //json字符串再转换为集合对象
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, InterfaceLddj.class);
        List<InterfaceLddj> lddjs2 = null;
        try {
            lddjs2 = (List<InterfaceLddj>)objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }


        logger.info("你好啊info测试");
        logger.warn("你好啊warn测试");
        res.setContentType("text/html;charset=UTF-8");
        //int i=1/0;
       try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("你好");
        try {
            res.getWriter().write(jsonDatas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/httpClientTest")
    public String httpClientTest(Model model){
        String str= CommonTools.IdentityVerfication(MD5Utils.md5("jd"));

        HttpPost httpPost = new HttpPost("http://localhost:8090/obtainData/obtainJsonData");
        List formparams=new ArrayList<Object>();
        formparams.add(new BasicNameValuePair("username","tom"));
        formparams.add(new BasicNameValuePair("password","123"));
        // 设置请求头消息User-Agent
        //httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formparams,"UTF-8"));
            httpPost.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpPost);
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(content);
        }catch(ClientProtocolException e){
            System.out.println("ClientProtocolException");
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
        return "/index.jsp";
    }
*/







}
