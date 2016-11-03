package cn.byhieg.iotutorial.bytestreamio;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class BufferdOutputStreamExample {

    public void writeToFile() throws Exception{
        String s = File.separator;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:" + s + "write_file.txt"))) {
            for (int i = 0 ; i < 10000000 ; i++) {
                String str = i + "";
                bos.write(str.getBytes(Charset.forName("UTF-8")));
                bos.flush();
            }
        }
    }
}
