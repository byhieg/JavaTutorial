package cn.byhieg.threadtutorial.char04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 17/1/27.
 * Mail to byhieg@gmail.com
 */

public class ConditionAllService {
    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA时间为 " + System.currentTimeMillis() +
                    "ThreadName=" + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end awaitA时间为" + System.currentTimeMillis() +
                    "ThreadName=" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println("begin awaitB时间为 " + System.currentTimeMillis() +
                    "ThreadName=" + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end awaitB时间为" + System.currentTimeMillis() +
                    "ThreadName=" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signAAll() {
        try {
            lock.lock();
            System.out.println("signAll的时间为" + System.currentTimeMillis() +
                    "ThreadName=" + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signBAll() {
        try {
            lock.lock();
            System.out.println("signAll的时间为" + System.currentTimeMillis() +
                    "ThreadName=" + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
