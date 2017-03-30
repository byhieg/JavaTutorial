package cn.byhieg.collectiontutorialtest;

import cn.byhieg.algorithmtutorial.BinarySearchTree;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by byhieg on 17/3/30.
 * Mail to byhieg@gmail.com
 */
public class BinarySearchTreeTest extends TestCase {
    int [] nums;
    BinarySearchTree tree;
    public void setUp() throws Exception {
        super.setUp();
        nums = new int[]{10,6,2,8,7,3,4,1};
        tree = new BinarySearchTree(nums);
    }

    public void tearDown() throws Exception {
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

    public void testGetMaxData() throws Exception {
//        Assert.assertEquals(12,tree.getMaxNode().data);
    }

    public void testGetMinData() throws Exception {
//        Assert.assertEquals(1,tree.getMinNode().data);
    }
}