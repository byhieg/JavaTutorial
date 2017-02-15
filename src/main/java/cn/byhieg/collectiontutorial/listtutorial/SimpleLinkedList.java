package cn.byhieg.collectiontutorial.listtutorial;

/**
 * Created by byhieg on 17/2/15.
 * Mail to byhieg@gmail.com
 */
public class SimpleLinkedList<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;


    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;

        }
    }
}
