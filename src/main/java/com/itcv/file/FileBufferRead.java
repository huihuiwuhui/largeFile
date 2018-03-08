package com.itcv.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileBufferRead {
  public static void main(String[] args) {
    readFileByBytes("D:/newText.txt");
    FileBufferRead fbr = new FileBufferRead();
    try {
		//fbr.spiltReadFile();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
}
  
  public FileBufferRead(){
	  
  }
  
  public static void readFileByBytes(String fileName) {
      File file = new File(fileName);
      InputStream in = null;
     /* try {
          System.out.println("以字节为单位读取文件内容，一次读一个字节：");
          // 一次读一个字节
          in = new FileInputStream(file);
          int tempbyte;
          while ((tempbyte = in.read()) != -1) {
        	//System.out.print("内容是："+tempbyte);
              System.out.write(tempbyte);
               
          }	
          in.close();
      } catch (IOException e) {
          e.printStackTrace();
          return;
      }*/
      try {
          System.out.println("以字节为单位读取文件内容，一次读多个字节：");
          // 一次读多个字节
          byte[] tempbytes = new byte[512];
          int byteread = 0;
          in = new FileInputStream(fileName);
         // ReadFromFile.showAvailableBytes(in);
          // 读入多个字节到字节数组中，byteread为一次读入的字节数
          while ((byteread = in.read(tempbytes)) != -1) {
              //System.out.write(tempbytes, 0, byteread);
              String bytesAsString = new String(tempbytes, StandardCharsets.UTF_8);
              System.out.println(bytesAsString);
          }
      } catch (Exception e1) {
          e1.printStackTrace();
      } finally {
          if (in != null) {
              try {
                  in.close();
              } catch (IOException e1) {
              }
          }
      }
  }
  
  private BufferedInputStream fileIn;
  private long fileLength;
  private int arraySize;
  private byte[] array;

  public FileBufferRead(String fileName, int arraySize) throws IOException {
      this.fileIn = new BufferedInputStream(new FileInputStream(fileName), arraySize);
      this.fileLength = fileIn.available();
      this.arraySize = arraySize;
  }

  public int read() throws IOException {
      byte[] tmpArray = new byte[arraySize];
      int bytes = fileIn.read(tmpArray);// 暂存到字节数组中
      if (bytes != -1) {
          array = new byte[bytes];// 字节数组长度为已读取长度
          System.arraycopy(tmpArray, 0, array, 0, bytes);// 复制已读取数据
          String bytesAsString = new String(tmpArray, StandardCharsets.UTF_8);
          System.out.println(bytesAsString);
          return bytes;
      }
      return -1;
  }

  public void close() throws IOException {
      fileIn.close();
      array = null;
  }

  public byte[] getArray() {
      return array;
  }

  public long getFileLength() {
      return fileLength;
  }
  
  public void spiltReadFile() throws Exception{
	  FileBufferRead reader = new FileBufferRead("D:/newText.txt", 65536);
	  int i =0;
      long start = System.nanoTime();
      while (reader.read() != -1) ;
      long end = System.nanoTime();
      i++;
      System.out.println("第"+i+"次执行====================================================================================");
      reader.close();
      System.out.println("StreamFileReader: " + (end - start));
  }

  
}
