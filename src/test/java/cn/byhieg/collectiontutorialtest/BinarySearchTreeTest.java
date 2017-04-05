package cn.byhieg.collectiontutorialtest;

import cn.byhieg.algorithmtutorial.BinarySearchTree;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by byhieg on 17/3/30.
 * Mail to byhieg@gmail.com
 */
public class BinarySearchTreeTest extends TestCase {
    int[] nums;
    BinarySearchTree tree;

    public void setUp() throws Exception {
        super.setUp();
        nums = new int[]{1,2,3,4,5};
        tree = new BinarySearchTree(nums);
    }

    public void tearDown() throws Exception {
        BinarySearchTree.Node node = new BinarySearchTree.Node(10);
        tree.levelRead(node);
    }


    public void testInsert() throws Exception {
    }

    public void testInOrder() throws Exception {
        System.out.println("中序遍历");
        tree.inorder2(tree.getRoot());
        System.out.println();
    }

    public void testPreOrder() throws Exception {
        System.out.println("先续遍历");
        tree.preOrder2(tree.getRoot());
        System.out.println();
    }

    public void testPostOrder() throws Exception {
        System.out.println("后续遍历");
        tree.postOrder2(tree.getRoot());
        System.out.println();
    }

    public void testGetTree() throws Exception {
        System.out.println("树");
        int[] pre = new int[]{1,2,4,5,3};
        int[] in = new int[]{4,2,5,1,3};
        BinarySearchTree.Node node = new BinarySearchTree.Node(1);
        tree.getTree(pre, in,node);
        tree.levelRead(node);
    }

//    public void testGetMaxData() throws Exception {
//        Assert.assertEquals(10,tree.getMaxNode().data);
//    }
//
//    public void testGetMinData() throws Exception {
//        Assert.assertEquals(1,tree.getMinNode().data);
//    }
}