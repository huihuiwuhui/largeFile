package com.itcv.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.http.entity.StringEntity;

public class CreateFile {
    public static void main(String[] args) throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    	System.out.println("��ʼ�����ļ�ʱ�䣺"+sdf.format(new Date()));
    	 File file = new File("D:/newText.txt");  
         if (!file.exists()) {  
             file.createNewFile();  
         }  
         FileOutputStream fos = new FileOutputStream(file);  
         OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");  
         BufferedWriter out = new BufferedWriter(osw);  
         Random r = new Random();   
         long i = 1000L;  
         while(i<300000000L){  
             i++;  
             out.write(i+"");  
             out.write(",");  
             out.newLine();  
             if(i%100000 ==0){  
                 out.flush();  
             }             
         }         
         out.close();  
         System.out.println("�������ɵ�"+file);  
         System.out.println("�����ļ�������"+sdf.format(new Date()));
         StringEntity se = new StringEntity("aaa");
	}
}
