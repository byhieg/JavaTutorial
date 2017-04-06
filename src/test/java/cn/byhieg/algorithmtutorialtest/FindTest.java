package cn.byhieg.algorithmtutorialtest;

import cn.byhieg.algorithmtutorial.Find;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/3/29.
 * Mail byhieg@gmail.com
 */
public class FindTest extends TestCase {
    int[] nums;
    int result;
    public void setUp() throws Exception {
        super.setUp();
        nums = new int[]{};
    }

    public void tearDown() throws Exception {
        System.out.println(result);
    }

    public void testBinarySerachFind() throws Exception {
        result = new Find().binarySearchFind(nums,2);
    }

//    public void testBinarySearchMinFind() throws Exception {
//        result = new Find().binarySearchMinFind(nums,1);
//    }

//    public void testBinarySearchMaxFind() throws Exception {
//        result = new Find().binarySearchMaxFind(nums, 2);
//    }

}