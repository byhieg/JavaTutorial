package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.bytestreamio.FileInputStreamExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileInputStreamExampleTest extends TestCase {

    public void testReadFromFile() throws Exception {
        new FileInputStreamExample().readFromFile();
    }

}