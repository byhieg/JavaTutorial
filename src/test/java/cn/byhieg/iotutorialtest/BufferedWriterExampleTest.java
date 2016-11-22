package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.charsetstreamio.BufferedWriterExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class BufferedWriterExampleTest extends TestCase {
    public void testWriteToFile() throws Exception {

        new BufferedWriterExample().writeToFile();

    }

}