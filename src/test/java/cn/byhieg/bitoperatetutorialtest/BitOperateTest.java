package cn.byhieg.bitoperatetutorialtest;

import cn.byhieg.bitoperatetutorial.BitOperate;
import junit.framework.TestCase;

/**
 * Created by byhieg on 2017/6/27.
 * Mail to byhieg@gmail.com
 */
public class BitOperateTest extends TestCase {

    BitOperate bitOperate;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bitOperate = new BitOperate();
    }

    public void testGetRightestOne() throws Exception {
        assertEquals(bitOperate.getRightestOne(Integer.valueOf("100100",2)),"100");
    }

}