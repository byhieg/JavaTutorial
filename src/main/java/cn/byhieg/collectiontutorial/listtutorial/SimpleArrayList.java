package cn.byhieg.collectiontutorial.listtutorial;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.RandomAccess;

/**
 * Created by byhieg on 17/2/7.
 * Mail to byhieg@gmail.com
 */
public class SimpleArrayList<E> implements RandomAccess,Cloneable,Serializable{


    private final static int DEFAULT_CAPACITY = 10;
    private int size = 0;

    private Object [] elementData;

    public SimpleArrayList(){
        this(DEFAULT_CAPACITY);
    }


    public SimpleArrayList(int size){
        if (size < 0){
            throw new IllegalArgumentException("默认的大小" + size);
        }else{
            elementData = new Object[size];
        }
    }


    public void add(E e){
        isCapacityEnough(size + 1);
        elementData[size++] = e;
    }

    public void add(int index, E e) {
        checkRangeForAdd(index);
        isCapacityEnough(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = e;
        size++;
    }

    private void isCapacityEnough(int size){
        if (size > DEFAULT_CAPACITY){
            explicitCapacity(size);
        }
        if (size < 0){
            throw new OutOfMemoryError();
        }
    }
    private final static int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private void explicitCapacity(int capacity){
        int newLength = elementData.length * 2;
        if (newLength - capacity < 0){
            newLength = capacity;
        }
        if (newLength > (MAX_ARRAY_LENGTH)){
            newLength = (capacity > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
        }
        elementData = Arrays.copyOf(elementData, newLength);
    }

    private void checkRangeForAdd(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }

    private void checkRange(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }
    public E get(int index){
        checkRange(index);
        return (E)elementData[index];
    }

    public int indexOf(Object o){
        if (o != null) {
            for (int i = 0 ; i < size ; i++){
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }else {
            for (int i = 0 ; i < size ; i++){
                if (elementData[i] == null) {
                    return i;
                }
            }
        }

        return -1;
    }

    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public E remove(int index) {
        E value = get(index);
        int moveSize = size - index - 1;
        if (moveSize > 0){
            System.arraycopy(elementData,index + 1, elementData,index,size - index - 1);
        }
        elementData[--size] = null;
        return value;
    }

    public boolean remove(Object o){
        if (contains(o)){
            remove(indexOf(o));
            return true;
        }else {
            return false;
        }
    }
}
