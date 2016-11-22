package cn.byhieg.iotutorial.charsetstreamio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class BufferedReaderExample {

    public void readFromFile() throws Exception{
        try(BufferedReader reader = new BufferedReader(new FileReader("D:" + File.separator + "read_file.txt"))){
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        }
    }
}
