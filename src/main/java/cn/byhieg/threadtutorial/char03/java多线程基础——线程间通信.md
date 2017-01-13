# Java多线程基础——线程间通信
在使用多线程的时候，经常需要多个线程进行协作来完成一件事情。在前面两章分析了Java多线程的基本使用以及利用`synchronized`来实现多个线程同步调用方法或者执行代码块。但上面两章的内容涉及到的例子以及使用的多线程代码都是独自运行，两个程序除了竞争同一个对象以外，没有任何联系。
这次内容将讲解当多个线程需要协作来完成一件事情的时候，如何去等待其他线程执行，又如何当线程执行完去通知其他线程结束等待。
本次主要介绍如下内容：
- 等待/通知机制
- join方法的使用

所有的代码均在[char03线程间通信](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/threadtutorial/char03)

## 等待/通知机制
Java中对多线程类提供了两个方法来完成等待/通知机制，等待的方法是-`wait()`，通知的方法是`notify()`。先说一下什么是等待/通知机制，所谓等待/通知机制，就是线程A在执行的时候，需要一个其他线程来提供的结果，但是其他线程还没有告诉他这个结果是什么，于是线程A开始等待，当其他线程计算出结果之后就将结果通知给线程A，A线程唤醒，继续执行。这个过程就是等待/通知机制。
等待/通知机制实际上多个线程之间的一种互动，而为了保证这个互动仅限于期望的那些线程，因此需要多个线程拥有一个统一的对象监视器，也就是都要在`synchronized（x）`同步代码块中执行`x.wait`以及`x.notify`方法。
如果细心观察，会发现wait方法和notify方法是Object类自带的方法。这个原因是因为任何一个对象都能成为监视器，而wait和notify只有对同一个监视器才能起到预期的作用。也就是说任何一个监视器都能用wait以及notify方法，任何对象都有的方法，自然就需要放到Object中
### wait方法与notify方法的讲解
wait方法会使执行该wait方法的线程停止，直到等到了notify的通知。细说一下，执行了wait方法的那个线程会因为wait方法而进入等待状态，该线程也会进入阻塞队列中。而执行了notify那个线程在执行完同步代码之后会通知在阻塞队列中的线程，使其进入就绪状态。被重新唤醒的线程会试图重新获得临界区的控制权，也就是对象锁，然后继续执行临界区也就是同步语句块中wait之后的代码。
上面这个描述，可以看出一些细节。
1.   wait方法进入了阻塞队列，而上文讲过执行notify操作的线程与执行wait的线程是拥有同一个对象监视器，也就说wait方法执行之后，立刻释放掉锁，这样，另一个线程才能执行同步代码块，才能执行notify。
2.   notify线程会在执行完同步代码之后通知在阻塞队列中的线程，也就是说notify的那个线程并不是立即释放锁，而是在同步方法执行完，释放锁以后，wait方法的那个线程才会继续执行。
3.   被重新唤醒的线程会试图重新获得锁，也就说，在notify方法的线程释放掉锁以后，其通知的线程是不确定的，看具体是哪一个阻塞队列中的线程获取到对象锁。

下面看一个例子：
```
public class Service {
    public void testMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin wait()");
                lock.wait();
                System.out.println(" end wait()");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin notify() ThreadName=" + Thread.currentThread().getName() +
                        " time=" +System.currentTimeMillis());
                lock.notify();
                Thread.sleep(1000 * 1);
                System.out.println("end notify() ThreadName=" + Thread.currentThread().getName() +
                        " time=" + System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
```
该Service中有两个方法，一个是testMethod方法，包含了wait方法，另一个是synNotifyMethod方法了notify方法，我们首先看一下，wait方法会释放锁的测试。
```
public class ServiceThread extends Thread{
    private Object lock;

    public ServiceThread(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        Service service = new Service();
        service.testMethod(lock);
    }
}
```
测试方法如下：
```
public void testRun() throws Exception {
    Object lock = new Object();
    new ServiceThread(lock).start();
    new ServiceThread(lock).start();
    Thread.sleep(1000 * 4);
}
```
结果如下：
```
begin wait()
begin wait()
```
很明显结果是执行了2次同步代码块，其执行的原因，就是因为第一个wait之后，释放掉了对象锁，所以第二个线程才会执行同步代码块。

还是利用上面的代码，现在我们看一下，notify方法通知等待的线程， 但是不会立即释放锁的例子。
```
public class NotifyServiceThread extends Thread{
    private Object lock;
    public NotifyServiceThread(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        Service service = new Service();
        service.synNotifyMethod(lock);
    }
}
```
测试的例子如下：
```
public class NotifyServiceThreadTest extends TestCase {
    public void testRun() throws Exception {
        Object lock = new Object();
        ServiceThread a = new ServiceThread(lock);
        a.start();
        Thread.sleep(1000);
        new NotifyServiceThread(lock).start();
        new NotifyServiceThread(lock).start();

        Thread.sleep(1000 * 10);
    }

}
```
其结果如下：
```
begin wait()
begin notify() ThreadName=Thread-1 time=1484302436105
end notify() ThreadName=Thread-1 time=1484302437108
end wait()
begin notify() ThreadName=Thread-2 time=1484302437108
end notify() ThreadName=Thread-2 time=1484302438110
```
测试方法，首先调用上wait的例子，让ServiceThread线程进入等待状态，然后执行2个含有notify操作的线程，可以看出，第一个notify执行完，wait线程并没有立即开始运行，而是Thread-1继续执行后续的notify方法，直到同步语句块结束，然后wait线程立即得到锁，并继续运行。之后Thread-2开始运行，直到结束，因为已经没有等待的线程，所以不会有后续的等待的线程运行。
这里，可以看出一个细节，竞争锁的线程有3个，一个包含wait线程，两个包含notify线程。第一个notify执行结束，获得锁一定是阻塞的线程，而不是另一个notify的线程。









