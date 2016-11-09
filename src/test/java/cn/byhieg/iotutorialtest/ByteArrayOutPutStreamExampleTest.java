package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.bytestreamio.ByteArrayOutPutStreamExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class ByteArrayOutPutStreamExampleTest extends TestCase {
    public void testWriteFromFile() throws Exception {
        new ByteArrayOutPutStreamExample().writeToBytes();
    }

}