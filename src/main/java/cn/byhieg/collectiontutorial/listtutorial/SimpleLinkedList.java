package cn.byhieg.collectiontutorial.listtutorial;

/**
 * Created by byhieg on 17/2/15.
 * Mail to byhieg@gmail.com
 */
public class SimpleLinkedList<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;


    public boolean add(E element) {
        addAtLast(element);
        return true;
    }

    private void addAtLast(E element) {
        Node<E> l = last;
        Node<E> node = new Node<>(element, null, l);
        last = node;
        if (l == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

    public void add(int index, E element) {
        checkRangeForAdd(index);
        if (index == size) {
            addAtLast(element);
        } else {
            Node<E> l = node(index);
            addBeforeNode(element, l);
        }
    }

    private void addBeforeNode(E element, Node<E> specifiedNode) {
        Node<E> preNode = specifiedNode.prev;
        Node<E> newNode = new Node<>(element, specifiedNode, preNode);
        if (preNode == null) {
            first = newNode;
        } else {
            preNode.next = newNode;
        }
        specifiedNode.prev = newNode;
        size++;
    }


    private Node<E> node(int index) {
        if (index < (size << 1)) {
            Node<E> cursor = first;
            for (int i = 0; i < index; i++) {
                cursor = cursor.next;
            }
            return cursor;
        } else {
            Node<E> cursor = last;
            for (int i = size - 1; i > index; i--) {
                cursor = cursor.prev;
            }
            return cursor;
        }
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }

    public E get(int index) {
        checkRange(index);
        return node(index).item;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("指定index超过界限");
        }
    }

    public int indexOf(Object element) {
        Node<E> cursor = first;
        int count = 0;
        while (cursor != null) {
            if (element != null) {
                if (element.equals(cursor.item)) {
                    return count;
                }
            }else{
                if (cursor.item == null) {
                    return count;
                }
            }
            count ++;
            cursor = cursor.next;
        }
        return -1;
    }

    public E remove(int index) {
        checkRange(index);
        return deleteLink(index);
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0){
            return false;
        }
        deleteLink(index);
        return true;
    }

    private E deleteLink(int index) {
        Node<E> l = node(index);
        E item = l.item;
        Node<E> prevNode = l.prev;
        Node<E> nextNode = l.next;

        if (prevNode == null) {
            first = nextNode;
        }else{
            prevNode.next = nextNode;
            l.next = null;
        }

        if (nextNode == null) {
            last = prevNode;
        }else{
            nextNode.prev = prevNode;
            l.prev = null;
        }
        size--;
        l.item = null;
        return item;
    }



    public int size(){
        return size;
    }
    private static class Node<E> {
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
