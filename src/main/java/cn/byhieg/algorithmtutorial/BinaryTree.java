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

    /**
     * 得到树的叶子节点的数目
     * @param root
     * @return
     */
    public static int getLeafs(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 1;
        }
        return getLeafs(root.left) + getLeafs(root.right);

    }


    /**
     * 计算树的深度
     * @param root
     * @return
     */
    public static int getHeight(Node root){
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left) + 1;
        int rightHeight = getHeight(root.right) + 1;
        return leftHeight > rightHeight ? leftHeight : rightHeight;
    }

    /**
     * 计算第K层的节点数
     * @param root
     * @param k
     * @return
     */
    public static int calcKNodes(Node root, int k) {
        if (root == null || k < 0) {
            return 0;
        }
        if (k == 0){
            return 1;
        }
        return calcKNodes(root.left, k - 1) + calcKNodes(root.right, k - 1);

    }

    /**
     * 判断两个树的结构是否相同
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isCommon(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }else{
            boolean isLeftCommon = isCommon(root1.left, root2.left);
            boolean isRightCommon = isCommon(root1.right, root2.right);
            return isLeftCommon && isRightCommon;
        }
    }

    /**
     * 得到树的镜像，即对于每一个节点，交换他们的左右孩子节点。
     * @param root
     */
    public static void mirror(Node root) {
        if (root != null) {
            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            mirror(root.left);
            mirror(root.right);
        }
    }

    /**
     * 得到两个节点的最近公共祖先节点。
     * 递归左右子树，如果返回的值都不为空，则表示在左右子树各找到一个target，因为最近的祖先就是cur
     * 如果有一个为空，则就不为空就是最近公共祖先。
     * @param root
     * @param target1
     * @param target2
     * @return
     */
    public static Node findLCA(Node root, Node target1, Node target2) {
        if (root == null)
            return null;

        if (root == target1 || root == target2) {
            return root;
        }
        Node left = findLCA(root.left, target1, target2);
        Node right = findLCA(root.right, target1, target2);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left:right;
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
