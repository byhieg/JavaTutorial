package cn.byhieg.algorithmtutorialtest;

import cn.byhieg.algorithmtutorial.BinaryTree;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/4/15.
 * Mail to byhieg@gmail.com
 */
public class BinaryTreeTest extends TestCase {

    BinaryTree.Node root = new BinaryTree.Node(1);
    public void setUp() throws Exception {
        super.setUp();
        BinaryTree.Node[] nodes = new BinaryTree.Node[10];
        nodes[0] = new BinaryTree.Node(2);
        root.left = nodes[0];
        for (int i = 1 ; i < 10;i++) {
            nodes[i] = new BinaryTree.Node(2 + i);
            if (i % 2 == 0){
                nodes[i - 1].left = nodes[i];
            }else{
                nodes[i - 1].right = nodes[i];
            }
        }

    }

    public void tearDown() throws Exception {
        System.out.println();
    }

    public void testPreOrder1() throws Exception {
        System.out.println("递归的先续遍历");
        BinaryTree.preOrder1(root);
    }

    public void testPreOrder2() throws Exception {
        System.out.println("非递归的先续遍历");
        BinaryTree.preOrder2(root);
    }


    public void testInOrder1() throws Exception {
        System.out.println("递归的中序遍历");
        BinaryTree.inOrder1(root);
    }

    public void testInOrder2() throws Exception {
        System.out.println("非递归的中序遍历");
        BinaryTree.inOrder2(root);
    }

    public void testPostOrder1() throws Exception {
        System.out.println("递归的后续遍历");
        BinaryTree.postOrder1(root);
    }

    public void testPostOrder2() throws Exception {
        System.out.println("非递归的后续遍历");
        BinaryTree.postOrder2(root);
    }

    public void testLevelOrder() throws Exception {
        System.out.println("层次遍历");
        BinaryTree.levelOrder(root);
    }

    public void testGetNodes() throws Exception {
        System.out.print("节点数" + BinaryTree.getNodes(root));
    }

    public void testGetLeafs() throws Exception {
        System.out.print("叶子数" + BinaryTree.getLeafs(root));
    }

    public void testGetHeight() throws Exception {
        System.out.print("树的高度" + BinaryTree.getHeight(root));
    }

    public void testCalcKNodes() throws Exception {
        System.out.print("第2层的节点数" + BinaryTree.calcKNodes(root,2));
    }

    public void testMirror() throws Exception {
        BinaryTree.mirror(root);
    }
}