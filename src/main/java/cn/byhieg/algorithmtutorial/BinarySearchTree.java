package cn.byhieg.algorithmtutorial;

/**
 * Created by byhieg on 17/3/30.
 * Mail to byhieg@gmail.com
 */

import java.util.Stack;

/**
 * 该类是二叉搜索树
 * 该树在实现的时候，不考虑数组中有重复的数字。
 * 节点的左子节点的值都小于这个节点的值，节点的右子节点的值都大于等于这个节点的值
 * 该树并不是平衡二叉树，也就是说左右子树的树高可以存在很大的差距，这就导致了在极端情况下，存在只有左边的树，这样的查找时间复杂度就是o(N)
 * 平均情况下 时间复杂度是o(lgN)
 *
 * 可以考虑是用平衡二叉树，保持了树的左右高度 不会超过1，但是带来的插入和删除的额外开销，因为每次插入删除，都需要调整平衡
 * 但他的查找的时间复杂度是o(lgN)
 *
 * 二叉搜索树(BST)相比二分查找的优势，在于他不需要基于有序的数组，并且插入和删除的操作显然比二分查找的数组有快的多，
 * 但是时间复杂度没有二分查找稳定
 * 相比二叉平衡搜索树(AVL)而言，虽然多了插入和删除的优势，但是没有AVL稳定
 */
public class BinarySearchTree {

    private Node root;


    public BinarySearchTree() {

    }


    public BinarySearchTree(int[] nums) {
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i]);
            insert(nodes[i]);
        }
    }

    /**
     * 查找指定的元素
     *
     * @param des
     * @return
     */
    public Node find(int des) {
        if (root == null) {
            System.out.println("树是空的");
            throw new RuntimeException();
        }
        Node current = root;
        while (current.data != des) {
            if (current.data < des) {
                current = current.right;
            } else {
                current = current.left;
            }
            if (current == null) return null;
        }
        return current;
    }

    /**
     * 对BST执行插入操作，采用非递归的形式
     * 保证插入后，左节点的值小于根节点的值，根节点的值小于右节点的值
     *
     * @param node
     * @return
     */
    public boolean insert(Node node) {
        if (root == null) {
            root = node;
            return true;
        }

        if (find(node.data) != null) {
            System.out.println("不允许插入相同data的数");
            throw new RuntimeException();
        }

        Node current = root;
        while (current != null) {
            if (current.data < node.data) {
                if (current.right == null) {
                    current.right = node;
                    return true;
                }
                current = current.right;
            } else {
                if (current.left == null) {
                    current.left = node;
                    return true;
                }
                current = current.left;
            }
        }
        return false;

    }

    /**
     * 树的前序遍历，递归实现
     */
    public void preOrder(Node node) {
        System.out.print(node.data + "-->");
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    /**
     * 树的中序遍历，递归实现
     * 针对BST，该结果会从小到大输出树
     *
     * @param node
     */
    public void inOrder(Node node) {
        if (node.left != null) {
            inOrder(node.left);
        }
        System.out.print(node.data + "-->");
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    /**
     * 树的后续遍历，递归实现
     */
    public void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        System.out.print(node.data + "-->");
    }


    /**
     * 树的先续遍历，非递归实现
     * 1. 建立一个栈，现将头结点压入栈中。
     * 2. 现将每出栈一个节点，打印他的值，然后都要先加入他的右节点，在加入他的左节点。因为栈的后进先出的特性，才能让左边先出。
     * 3. 不断重复2，直到栈空
     */
    public void preOrder2(Node node) {
        if (node != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Node tmp = stack.pop();
                System.out.print(tmp.data + "-->");
                if (tmp.right != null) {
                    stack.push(tmp.right);
                }
                if (tmp.left != null) {
                    stack.push(tmp.left);
                }
            }
        }
    }

    /**
     * 树的中序遍历，非递归实现
     * 1. 设定cur,初始化cur = root节点，不断遍历里cur.left,并将其压入栈中，直到null。
     * 2. 出栈一个节点，打印他的值，然后cur = node.right,并不断重复第二步
     * 3. 当栈为空，并且cur为空时，停止
     */
    public void inorder2(Node node) {
        if (node != null) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            while (!stack.isEmpty() || cur != null) {
                if (cur == null) {
                    cur = stack.pop();
                    System.out.print(cur.data + "-->");
                    cur = cur.right;
                } else {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
    }

    /**
     * 树的后续遍历，非递归实现
     * 1. 树的先续遍历中，是栈存放顺序是根，右节点，左节点。
     * 2. 我们可以将其反过来，用栈存放是根，左节点，右节点。然后出栈的时候，将出栈的结果存放到另一个栈里。
     * 3. 第二栈里的顺序从上到下就是左节点，右节点，根的顺序。
     *
     * @param node
     */

    public void postOrder2(Node node) {
        if (node != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> result = new Stack<>();
            Node cur = node;
            stack.push(cur);
            while (!stack.isEmpty()) {
                Node tmp = stack.pop();
                result.push(tmp);
                if (tmp.left != null) {
                    stack.push(tmp.left);
                }
                if (tmp.right != null) {
                    stack.push(tmp.right);
                }
            }

            while (!result.isEmpty()) {
                System.out.print(result.pop().data + "-->");
            }
        }
    }


    /**
     * 得到树中最小的节点
     *
     * @return
     */
    public Node getMinNode() {
        if (root == null) {
            throw new RuntimeException("树为空");
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;

    }

    /**
     * 得到树中最大的节点
     *
     * @return
     */
    public Node getMaxNode() {
        if (root == null) {
            throw new RuntimeException("树为空");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }


    /**
     * 通过先序遍历和中序遍历恢复一棵树
     * @param preOrders
     * @param inOrders
     * @param isLeft
     */
    public void getTree(int[] preOrders, int[] inOrders,boolean isLeft) {
        int root = preOrders[0];
        if (isLeft) {
            System.out.println("左" + root);
        }else{
            System.out.println("右" + root);
        }

        int index = new Find().binarySearchFind(inOrders, root);
        int[] left = new int[index];
        int[] preLeft = new int[index];
        if (left.length != 0) {
            for (int i = 0; i < index; i++) {
                left[i] = inOrders[i];
                preLeft[i] = preOrders[i + 1];
            }
        }
        int size = inOrders.length - index - 1;
        int[] right = new int[inOrders.length - index - 1];
        int[] preRight = new int[size];
        if (right.length != 0) {
            for (int i = 0; i < size; i++) {
                right[i] = inOrders[i + index + 1];
                preRight[i] = preOrders[preLeft.length + i + 1];
            }
        }

        if (preLeft.length != 0) {
            getTree(preLeft, left,true);
        }

        if (preRight.length != 0) {
            getTree(preRight, right,false);
        }
        System.out.println();

    }

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node getRoot() {
        return root;
    }
}
