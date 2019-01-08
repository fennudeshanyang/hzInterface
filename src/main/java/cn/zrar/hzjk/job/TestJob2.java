package cn.zrar.hzjk.job;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJob2 extends QuartzJobBean {

    //判断该任务是否正在执行,trues即有同一类型任务正在执行false则没有
    private static boolean isRun = false;

    private static int num = 0;


    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //如果触发器监控到时间已经到了，则开始回调该方法
        /**
         *  1.从定时任务中获取spring容器
         *  2.从容器中获取Mapper对象
         *  3.通过Mapper对象执行任务操作
         */

/*        if(isRun){
            System.out.println("前一次任务未执行完成，跳过本次任务");
            return;
        }*/
        num++;
        System.out.println("定时任务TestJob2同时执行个数:"+num);
        if(num>=100){
            num--;
            System.out.println("同时执行的定时任务超出限额,本次不执行");
            return;
        }


        //isRun=true;

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("job执行开始时间:"+sdf.format(now));
        Date startTime = jobExecutionContext.getTrigger().getStartTime();
        Date endTime = jobExecutionContext.getTrigger().getEndTime();
        //System.out.println("定时任务开始时间:"+sdf.format(startTime));
        //System.out.println("定时任务结束时间:"+sdf.format(endTime));
       // System.out.println("我是定时任务");

        HttpPost httpPost = new HttpPost("http://localhost:8090/obtainData/obtainJsonData");
        List formparams=new ArrayList<Object>();
        formparams.add(new BasicNameValuePair("username","tom"));
        formparams.add(new BasicNameValuePair("password","123"));

        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getJobDetail().getJobDataMap().get("applicationContext");
        HttpClient httpClient = (HttpClient)applicationContext.getBean("httpClient");
        RequestConfig requestConfig = (RequestConfig) applicationContext.getBean("requestConfig");

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formparams,"UTF-8"));
            httpPost.setConfig(requestConfig);

            HttpResponse response = httpClient.execute(httpPost);
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("httpClient返回的内容:"+content);
        }catch(ClientProtocolException e){
            System.out.println("ClientProtocolException");
            e.printStackTrace();
            isRun=false;
        }catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
            isRun=false;
        }
        Date end = new Date();
        System.out.println("job执行结束时间:"+sdf.format(end));
        //isRun=false;
        num--;

        File fp=new File("d:\\a.txt");

        //Date end = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str="超时时间"+sdf.format(end);
        PrintWriter pfp= null;
        try {
            pfp = new PrintWriter(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pfp.print(str);
        pfp.close();


    }

}
