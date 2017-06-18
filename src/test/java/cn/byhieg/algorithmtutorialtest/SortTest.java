package cn.byhieg.algorithmtutorialtest;

import cn.byhieg.algorithmtutorial.Sort;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/3/28.
 * Mail byhieg@gmail.com
 */
public class SortTest extends TestCase {
    int [] nums;
    public void setUp() throws Exception {
        super.setUp();
        nums = new int[]{10, 20, 2, 3, 1, 100, 45, 22, 51, 21};
    }

    public void tearDown() throws Exception {
        for (int i = 0 ; i < nums.length;i++){
            System.out.print(nums[i] + " ");
        }
    }
//
//    public void testChooseSort() throws Exception {
//       new Sort().chooseSort(nums);
//    }
//
//
//    public void testInsertDirectlySort() throws Exception {
//        new Sort().insertDirectlySort(nums);
//    }
//
//    public void testInsertBinarySort() throws Exception {
//        new Sort().insertBinarySort(nums);
//    }
//
//    public void testBubbleSort() throws Exception {
//        new Sort().bubbleSort2(nums);
//    }
//
//    public void testQuickSort() throws Exception {
//        new Sort().quickSort(nums);
//    }

//    public void testHeapSort() throws Exception {
//        new Sort().heapSort(nums);
//    }

    public void testMergeSort() throws Exception {
        new Sort().mergeSort(nums);

    }

}