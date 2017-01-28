package cn.byhieg.threadtutorial.char04;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by byhieg on 17/1/28.
 * Mail to byhieg@gmail.com
 */
public class ReadWriteService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(){
        try{
            try{
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName()
                + " " + System.currentTimeMillis());
                Thread.sleep(1000 * 10);
            }finally {
                lock.readLock().unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

   public void write(){
        try{
            try{
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName()
                + " " + System.currentTimeMillis());
                Thread.sleep(1000 * 10);
            }finally {
                lock.writeLock().unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
