package cn.byhieg.iotutorial.charsetstreamio;

import java.io.File;
import java.io.FileReader;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileReaderExample {

    public void readFromFile() throws Exception{
        try (FileReader reader = new FileReader("D:" + File.separator + "read_file.txt")) {
            char[] chars = new char[1024];
            while (reader.read(chars) != -1) {
                System.out.println(chars);
            }
        }
    }
}
