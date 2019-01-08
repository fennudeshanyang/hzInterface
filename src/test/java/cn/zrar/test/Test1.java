package cn.zrar.test;

import java.util.concurrent.*;

public class Test1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //我的任务
        FutureTask<String> future = new FutureTask<String>(
                new Callable<String>() {
                    public String call() {
                        System.out.println("begin");

                        try {
                            int i =0;
                            for(i =0; i<999999999; i++){
                                System.out.println("i的值:"+i);
                                Thread.sleep(1000);
                            }
                            System.out.println("结束i的值:"+i);
                        } catch (Exception e) {
                            System.out.println("Thread Exception");
                        }


/*                       try {
                            Thread.sleep(700000000);

                        } catch (InterruptedException e) {
                            System.out.println("Thread InterruptedException");
                        }*/
                        System.out.println("end");
                        return null;
                    }
                });
        executor.execute(future);

        try {
            future.get(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            future.cancel(true);
            System.out.println("exec cancel InterruptedException");
        } catch (ExecutionException e) {
            future.cancel(true);
            System.out.println("exec cancel ExecutionException");
        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println("exec cancel TimeoutException");
        } finally {
            executor.shutdown();
            System.out.println("exec shutdown");
        }
        System.out.println("你好");
    }

}
