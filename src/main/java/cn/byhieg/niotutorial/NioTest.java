package cn.byhieg.niotutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by shiqifeng on 2017/4/10.
 * Mail byhieg@gmail.com
 */
public class NioTest {

    public static void main(String[] args) throws IOException {
        String s = File.separator;
        RandomAccessFile aFile = new RandomAccessFile("D:" + s + "read_file.txt","rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();
    }
}
