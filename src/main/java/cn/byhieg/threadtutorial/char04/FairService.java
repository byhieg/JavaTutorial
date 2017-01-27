package cn.byhieg.threadtutorial.char04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 17/1/27.
 * Mail to byhieg@gmail.com
 */
public class FairService {
    private Lock lock;
    private boolean isFair;


    public FairService(boolean isFair){
        lock = new ReentrantLock(isFair);
    }

    public void setFair(boolean fair) {
        isFair = fair;
    }

    public void serviceMethod(){
        try{
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "获得锁定");
        }finally {
            lock.unlock();
        }
    }
}
