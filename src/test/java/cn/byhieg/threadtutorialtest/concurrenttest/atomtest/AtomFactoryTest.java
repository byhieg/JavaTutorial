package cn.byhieg.threadtutorialtest.concurrenttest.atomtest;

import cn.byhieg.threadtutorial.concurrent.atom.AtomFactory;

import cn.byhieg.threadtutorial.concurrent.atom.MyObject;
import junit.framework.TestCase;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by shiqifeng on 2017/5/5.
 * Mail byhieg@gmail.com
 */
public class AtomFactoryTest extends TestCase {
    AtomicInteger integer;
    AtomicIntegerArray array;
    AtomicReference<MyObject> reference;
    AtomicIntegerFieldUpdater<MyObject> updater;

    public void setUp() throws Exception {
        super.setUp();
        integer = AtomFactory.getInstance().createAtomInt(1);
    }

    public void testAtomInt() throws Exception {
        System.out.println("int原子类");
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                integer.getAndIncrement();
                System.out.println(getName() + " " + integer.get());
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                integer.getAndIncrement();
                System.out.println(getName() + " " + integer.get());
            }
        }).start();

        Thread.sleep(1000);
    }

    public void testAtomArray() throws Exception {
        System.out.println("原子类数组");
        int [] value = new int[]{1,2,3,4};
        array = AtomFactory.getInstance().createAtomArray(value);
        array.getAndSet(1,10);
        System.out.println(array.get(1));
        System.out.println(value[1]);
    }

    public void testAtomRef()throws Exception {
        System.out.println("原子类");
        MyObject object = new MyObject();
        reference = AtomFactory.getInstance().createAtomReference(object);
        reference.set(object);
        MyObject newObject = new MyObject();
        newObject.name = "xiaoli";
        reference.compareAndSet(object, newObject);
        System.out.println(reference.get().name);
    }


    public void testUpdater() throws Exception {
        System.out.println("原子类更新字段");
        updater = AtomFactory.getInstance().createAtomIntegerUpdate("id");
        MyObject object = new MyObject();
        System.out.println(updater.getAndIncrement(object));
        System.out.println(updater.get(object));
    }


    public void tearDown() throws Exception {

    }


}