package com.itcv.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
/**
 * 文件通道读取大文件
 * @author zf
 *
 */
public class ChannelFileReader {
	private FileInputStream fileIn;
    private ByteBuffer byteBuf;
    private long fileLength;
    private int arraySize;
    private byte[] array;

    public ChannelFileReader(String fileName, int arraySize) throws IOException {
        this.fileIn = new FileInputStream(fileName);
        this.fileLength = fileIn.getChannel().size();
        this.arraySize = arraySize;
        this.byteBuf = ByteBuffer.allocate(arraySize);
    }

    public int read() throws IOException {
        FileChannel fileChannel = fileIn.getChannel();
        int bytes = fileChannel.read(byteBuf);// 读取到ByteBuffer中
        if (bytes != -1) {
            array = new byte[bytes];// 字节数组长度为已读取长度
            byteBuf.flip();
            byteBuf.get(array);// 从ByteBuffer中得到字节数组
            byteBuf.clear();
            String bytesAsString = new String(array, StandardCharsets.UTF_8);
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

    public static void main(String[] args) throws IOException {
        ChannelFileReader reader = new ChannelFileReader("D:/newText.txt", 65536);
        long start = System.nanoTime();
        while (reader.read() != -1) ;
        long end = System.nanoTime();
        reader.close();
        System.out.println("ChannelFileReader: " + (end - start));
    }
}
