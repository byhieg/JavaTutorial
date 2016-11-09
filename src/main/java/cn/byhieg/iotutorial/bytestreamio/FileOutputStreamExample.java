package cn.byhieg.iotutorial.bytestreamio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileOutputStreamExample {

    public void writeToFile() throws Exception{
        String s = File.separator;
        try (FileOutputStream fos = new FileOutputStream("D:" + s + "write_file.txt")) {
            for (int i  = 0 ; i < 10 ; i++) {
                String str = i + "";
                fos.write(str.getBytes(Charset.forName("UTF-8")));
                fos.flush();
            }
        }
    }
}
