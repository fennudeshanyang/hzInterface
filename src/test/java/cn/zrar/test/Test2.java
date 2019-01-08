package cn.zrar.test;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        /**
         *  1.创建HttpClient的实例
         *  2.创建某种连接方法的实例，在这里是GetMethod。在 GetMethod 的构造函数中传入待连接的地址
         *  3.调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
         *  4.读 response
         *  5.释放连接。无论执行方法是否成功，都必须释放连接
         *  6.对得到后的内容进行处理
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8090/obtainData/obtainJsonData");

        List formparams=new ArrayList<Object>();
        formparams.add(new BasicNameValuePair("username","tom"));
        formparams.add(new BasicNameValuePair("password","123"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formparams,"UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            httpClient=null;
        }


    }


}
