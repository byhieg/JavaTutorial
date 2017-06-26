package cn.byhieg.algorithmtutorial;

/**
 * Created by byhieg on 2017/6/24.
 * Mail to byhieg@gmail.com
 */


/**
 * 红黑树，一种通过红黑两种节点来维持二叉搜索树的一种树
 * 这样树比原先的BST而言，不会出现最坏的查找情况是o(N)的情况
 * 但是对于插入和删除的节点而言，就需要调整树的平衡，也就是维持红黑树的定义
 *
 * 红黑树的定义如下：
 * 1. 任何节点要不是黑色要不是红色
 * 2. 根节点是黑色节点
 * 3. 红节点的两个子节点都是黑色节点
 * 4. 空节点是黑色节点
 * 5. 任何一个节点下面遍历其子孙的叶子节点，经过的黑色节点的个数必须相等。
 *
 * 红黑树也是通过第5点进行维持平衡的，而为了维持平衡，需要对树进行调整，即进行左旋，右旋。
 *
 * 左旋是指   A
 *         B   C
 * 对C左旋 变成
 *
 *          C
 *        A
 *      B
 *
 * 节点从左节点变成根节点就是左旋
 *
 * 右旋是指  A
 *        B   C
 *
 * 对B右旋 变成
 *          B
 *            A
 *               C
 *
 * 节点从右节点变成根节点就是右旋
 *
 */
public class RedBlackTree {

    Node root;

    public RedBlackTree(){
    }

    public RedBlackTree(int value) {
        root = new Node(value);
    }

    public Node find(int value) {
        if (root == null) {
            throw new RuntimeException("树是空的");
        }

        Node currentNode = root;
        while (currentNode != null && currentNode.getValue() != value) {
            if (currentNode.getValue() < value) {
                currentNode = currentNode.getLeft();
            }else{
                currentNode = currentNode.getRight();
            }
        }

        return currentNode;
    }


    public void insertNode(int value) {
        Node node = new Node(value);
        insertNode(node);

    }

    /**
     * 插入节点
     * 该方法首先找到要插入的位置，然后设置插入的节点为红色节点
     * 然后因为可能会破坏平衡，因此需要进行平衡调整
     * @param node
     */
    public void insertNode(Node node) {
        int cmp;
        Node y = null;
        Node x = this.root;

        while (x != null) {
            y = x;
            cmp = node.getValue() - x.getValue();
            if (cmp < 0) {
                x = x.left;
            }else{
                x = x.right;
            }
        }

        node.parent = y;
        if (y != null) {
            cmp = node.getValue() - y.getValue();
            if (cmp < 0) {
                y.left = node;
            }else{
                y.right = node;
            }
        }else{
            this.root = node;
        }

        node.isRed = true;
        insertFixUp(node);

    }

    /**
     * 插入修复： 新插入的节点是红色节点，插入修复操作如果遇到父节点的颜色为黑色则修复结束
     * 也就是说只有在父节点为红色节点的时候才需要插入修复操作
     * 插入修复操作分为3种情况，
     * 1. 叔叔节点也为红色节点
     * 2. 叔叔节点为空，且祖父节点，父节点与新节点在一个斜线上
     * 3. 叔叔节点为空，且祖父节点，父节点与新节点不在一个斜线上
     *
     *
     * 解决办法：对于第一种，只需要将祖父节点与父节点以及叔叔节点的颜色对调即可。
     * 即原祖父节点是黑色，现在变成红色，父节点与叔叔节点都变成黑色。
     * 对于第二种，我们将新插入的节点为C,父节点为B,祖父节点为A.
     * 如果BC都是左节点，要现将B右旋，然后调整B与C的颜色，即B变成黑色，A变成红色
     * 如果BC都是右节点，要现将B左旋，然后调整B与C的颜色，即B变成黑色，A变成红色
     * 对于第三种，我们将新插入的节点为C,父节点为B,祖父节点为A.
     * 如果C为右节点，B为左节点，要先将C左旋，然后就变成第二种的情况
     * 如果C为左节点，B为右节点，要先将C右旋，然后就变成第二种的情况
     *
     * @param node
     */
    private void insertFixUp(Node node) {


    }


    static class Node {
        private int value;
        private Node parent;
        private boolean isRed;
        private Node left;
        private Node right;

        public Node(){

        }

        public Node(int value) {
            this.value = value;

        }

        public Node(int value, boolean isRed) {
            this.value = value;
            this.isRed = isRed;
        }
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public boolean isRed() {
            return isRed;
        }

        public boolean isBlack(){
            return !isRed();
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void makeRed(){
            isRed = true;
        }

        public void makeBlack(){
            isRed = false;
        }
    }
}
