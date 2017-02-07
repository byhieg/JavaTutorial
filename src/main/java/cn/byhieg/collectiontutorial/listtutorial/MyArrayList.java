package cn.byhieg.collectiontutorial.listtutorial;

/**
 * Created by byhieg on 17/2/7.
 * Mail to byhieg@gmail.com
 */
public class MyArrayList<E> {


    private final static int DEFAULT_CAPACITY = 10;
    private int size;

    private Object [] elementData;

    //默认的构造方法
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int size){
        if (size < 0){
            throw new IllegalArgumentException("默认的大小" + size);
        }else{
            elementData = new Object[size];
        }
    }

    
}
