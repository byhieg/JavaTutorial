package cn.byhieg.algorithmtutorial;

/**
 * Created by byhieg on 17/5/2.
 * Mail to byhieg@gmail.com
 */
public class SingleLinkList {


    public Node head;

    /**
     * 在当前链表尾部插入一个节点
     *
     * @param data
     * @return
     */
    public Node insertFromTail(int data) {
        Node cur = getHead();
        Node node = new Node(data);
        if (cur == null) {
            head = node;
            return head;
        } else {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        return cur;
    }

    /**
     * 在当前链表头部插入一个节点
     *
     * @param data
     * @return
     */
    public Node insertFromHead(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        return head;
    }


    public Node reverseLinkList() {
        if (head == null) {
            return head;
        }
        Node reverseHead = null;
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            Node next = cur.next;
            if (next == null) {
                reverseHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return reverseHead;
    }

    /**
     * 打印链表
     */
    public void printLinkList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
    }

    public Node getHead() {
        return head;
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
