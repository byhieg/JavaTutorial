package cn.byhieg.algorithmtutorial;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by byhieg on 17/4/15.
 * Mail to byhieg@gmail.com
 */
public class BinaryTree {

    /**
     * 递归的形式实现先续遍历
     * 根-左-右
     *
     * @param root
     */
    public static void preOrder1(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder1(root.left);
            preOrder1(root.right);
        }
    }

    /**
     * 非递归的形式实现先续遍历
     * 根-左-右
     *
     * @param root
     */
    public static void preOrder2(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                System.out.print(cur.data + " ");
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 递归实现中序遍历
     * 左-根-右
     *
     * @param root
     */
    public static void inOrder1(Node root) {
        if (root != null) {
            inOrder1(root.left);
            System.out.print(root.data + " ");
            inOrder1(root.right);
        }
    }

    /**
     * 非递归实现中序遍历
     * 左-根-右
     *
     * @param root
     */
    public static void inOrder2(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            Node cur = root;
            while (!stack.isEmpty() || cur != null) {
                if (cur == null) {
                    Node node = stack.pop();
                    System.out.print(node.data + " ");
                    cur = node.right;
                } else {
                    stack.push(cur);
                    cur = cur.left;
                }

            }
        }
    }

    /**
     * 递归实现树的后续遍历
     * 左-右-根
     *
     * @param root
     */
    public static void postOrder1(Node root) {
        if (root != null) {
            postOrder1(root.left);
            postOrder1(root.right);
            System.out.print(root.data + " ");
        }
    }

    /**
     * 非递归试树的后续遍历
     * 左-右-根
     *
     * @param root
     */
    public static void postOrder2(Node root) {
        if (root != null) {
            Stack<Node> tmpStack = new Stack<>();
            Stack<Node> resStack = new Stack<>();
            tmpStack.push(root);
            while (!tmpStack.isEmpty()) {
                Node cur = tmpStack.pop();
                resStack.push(cur);
                if (cur.left != null) {
                    tmpStack.push(cur.left);
                }
                if (cur.right != null) {
                    tmpStack.push(cur.right);
                }
            }

            while (!resStack.isEmpty()) {
                Node cur = resStack.pop();
                System.out.print(cur.data + " ");
            }
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public static void levelOrder(Node root) {
        if (root != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                System.out.print(cur.data + " ");
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
    }

    /**
     * 统计树的节点数
     *
     * @param root
     */
    public static int getNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return getNodes(root.left) + getNodes(root.right) + 1;
    }

    public static int getLeafs(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 1;
        }
        return getLeafs(root.left) + getLeafs(root.right);

    }

    public static class Node {
        public int data;
        public Node left;
        public Node right;


        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
