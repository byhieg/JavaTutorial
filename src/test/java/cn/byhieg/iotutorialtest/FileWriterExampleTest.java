package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.charsetstreamio.FileWriterExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileWriterExampleTest extends TestCase {
    public void testWriteToFile() throws Exception {
        new FileWriterExample().writeToFile();
    }

}