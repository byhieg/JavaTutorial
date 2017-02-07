package cn.byhieg.collectiontutorial.listtutorial;

import java.util.Arrays;

/**
 * Created by byhieg on 17/2/7.
 * Mail to byhieg@gmail.com
 */
public class SimpleArrayList<E> {


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

    public E get(int index){

    }

    
}
