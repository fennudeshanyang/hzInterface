package cn.zrar.hzjk.job;

import cn.zrar.hzjk.mapper.ObtainDataMapper;
import cn.zrar.hzjk.service.ObtainDataService;
import cn.zrar.hzjk.service.ObtainDataServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJob extends QuartzJobBean {

    //判断该任务是否正在执行,trues即有同一类型任务正在执行false则没有
    private static boolean isRun = false;


    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //如果触发器监控到时间已经到了，则开始回调该方法
        /**
         *  1.从定时任务中获取spring容器
         *  2.从容器中获取Mapper对象
         *  3.通过Mapper对象执行任务操作
          */

        if(isRun){
            System.out.println("前一次任务未执行完成，跳过本次任务");
            return;
        }
        isRun=true;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("job执行开始时间:"+sdf.format(now));
        Date startTime = jobExecutionContext.getTrigger().getStartTime();
        Date endTime = jobExecutionContext.getTrigger().getEndTime();
        System.out.println("定时任务开始时间:"+sdf.format(startTime));
        //System.out.println("定时任务结束时间:"+sdf.format(endTime));
        System.out.println("我是定时任务");
        try {
            Date date1 =sdf.parse("2018-12-20 13:51:05");
            Date date2 =sdf.parse("2018-12-20 13:51:00");
            if(date1.getTime()>=now.getTime() && date2.getTime()<=now.getTime()){
                System.out.println("时间到了循环任务啦");
                int n=0;
                while(true){
/*                    n++;
                    if(n==1000){
                        n=n/0;
                    }*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //isRun=false;
        }


        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getJobDetail().getJobDataMap().get("applicationContext");
        ObtainDataService obtainDataMapper = applicationContext.getBean(ObtainDataService.class);
/*        Map<String,String> map = new HashMap<String,String>();
        map.put("id","1193");
        List<Ywdj> ywdj = obtainDataMapper.getData();

        for(Ywdj dj : ywdj){
            System.out.println(dj.toString());
        }*/
        Date end = new Date();
        System.out.println("job执行结束时间:"+sdf.format(end));
        isRun=false;




    }
}
