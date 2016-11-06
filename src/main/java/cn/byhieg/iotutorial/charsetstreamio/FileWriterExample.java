package cn.byhieg.iotutorial.charsetstreamio;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileWriterExample {

    public void writeToFile() throws Exception {
        try (FileWriter writer = new FileWriter("D:" + File.separator + "write_file.txt")) {
            for (int i = 0 ; i< 1000000;i++) {
                writer.write("我是byhieg");
            }
        }
    }
}
