package cn.zrar.test;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3 {

    public static void main(String[] args) {

        File fp=new File("d:\\a.txt");

        Date end = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    @Test
    public void test2(){

        String str = "asdqwdqw,dqwdqw,weqweqw,";

        String substring = str.substring(0, str.length() - 1);


        System.out.println(substring);

    }



}
