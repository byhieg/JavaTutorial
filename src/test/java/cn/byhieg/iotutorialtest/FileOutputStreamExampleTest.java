package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.bytestreamio.FileOutputStreamExample;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileOutputStreamExampleTest extends TestCase {
    public void testWriteToFile() throws Exception {
        new FileOutputStreamExample().writeToFile();
        String s = File.separator;
        try (FileInputStream fis = new FileInputStream("D:" + s + "write_file.txt")) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            while (fis.read(bytes) != -1) {
                baos.write(bytes);
            }

            System.out.println(baos.toString());
        }


    }

}