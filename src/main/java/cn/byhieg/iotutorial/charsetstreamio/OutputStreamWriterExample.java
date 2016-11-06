package cn.byhieg.iotutorial.charsetstreamio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class OutputStreamWriterExample {

    public void writeToFile() throws Exception{
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("D:" + File.separator + "write_file.txt"))) {
            for (int i = 0 ; i < 10000;i++) {
                writer.write("我是中国人" + "");
                writer.write("\r\n");
            }
        }
    }
}
