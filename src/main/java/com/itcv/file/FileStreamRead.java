package com.itcv.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 以文件流的形式读取文件
 * @author zf
 *
 */
public class FileStreamRead {
    public static void main(String[] args) throws Exception{
		FileInputStream inputStream = new FileInputStream(new File("D:/text.txt"));
		Scanner sc = null;
		try {
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		         System.out.println(line);
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
	}
}
