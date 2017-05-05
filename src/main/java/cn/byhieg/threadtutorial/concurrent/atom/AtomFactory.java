package cn.byhieg.threadtutorial.concurrent.atom;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by shiqifeng on 2017/5/5.
 * Mail byhieg@gmail.com
 */
public class AtomFactory {

    private static final AtomFactory atomFactory = new AtomFactory();

    private AtomFactory(){

    }

    public static AtomFactory getInstance(){
        return atomFactory;
    }

    public AtomicInteger createAtomInt(int a){
        return new AtomicInteger(a);
    }

    public AtomicIntegerArray createAtomArray(int[] a) {
        return new AtomicIntegerArray(a);
    }

    public AtomicReference<MyObject> createAtomReference(MyObject object){
        AtomicReference<MyObject> objectAtomicReference = new AtomicReference<>();
        return objectAtomicReference;
    }
}
