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
上面的程序展现了等待/通知机制是如何通过wait和notify实现。在这里，我们可以看出wait方法使线程进入等待，和`Thread.sleep`是很相似的。但是两者却截然不同，区别如下：
-   wait使线程进入等待，是可以被通知唤醒的，但是sleep只能自己到时间唤醒。
-   wait方法是对象锁调用的成员方法，而sleep却是Thread类的静态方法
-   wait方法出现在同步方法或者同步代码块中，但是sleep方法可以出现在非同步代码中。

wait和notify还提供了几个其他API，如`wait(long timeout)`该方法可以提供一个唤醒的时间，如果在时间内，没有其他线程唤醒该等待线程，则到设定的时间，会自动结束等待。
因为notify仅仅能唤醒一个线程，所以Java提供了一个`notifyAll()`的方法来唤醒所有的线程，让所有的线程来竞争。我们看一下只唤醒一个线程和唤醒所有线程的不同。
```
public class CommonWait {

    private Object object;
    public CommonWait(Object object){
        this.object = object;
    }

    public void doSomething() throws Exception{
        synchronized (object){
            System.out.println("begin wait  " + Thread.currentThread().getName());
            object.wait();
            System.out.println("end wait " + Thread.currentThread().getName());
        }
    }
}
```
```
public class CommonNotify {

    private Object object;
    public CommonNotify(Object object){
        this.object = object;
    }

    public void doNotify(){
        synchronized (object){
            System.out.println("准备通知");
            object.notify();
            System.out.println("通知结束");
        }
    }
}
```
测试通知一个等待线程
```
 public void testRun() throws Exception{
        Object lock = new Object();
        new Thread(()->{
            try {
                new CommonWait(lock).doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                new CommonWait(lock).doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(()->{
            new CommonNotify(lock).doNotify();
        }).start();

        Thread.sleep(1000 * 3);

    }
```
结果如下：
```
begin wait  Thread-0
begin wait  Thread-1
准备通知
通知结束
end wait Thread-0
```
结果看来，只有一个线程结束了等待，继续往下面执行。另一个线程直到结束也没有执行。
现在看一下notifyAll的效果，把`CommonNotify`这个类中的`object.notify();`改成`object.notifyAll()`
其他的不变，看看结果：
```
begin wait  Thread-0
begin wait  Thread-1
准备通知
通知结束
end wait Thread-1
end wait Thread-0
```
很明显，两个等待线程都执行了，而且这次Thread-1的线程先执行，可见通知唤醒是随机的。
这里详细说一下，这个结果。wait使线程进入了阻塞状态，阻塞状态可以细分为3种：
-  等待阻塞：运行的线程执行wait方法，JVM会把该线程放入等待队列中。
-  同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池当中。
-   其他阻塞： 运行的线程执行了`Thread.sleep`或者`join`方法，或者发出I/O请求时，JVM会把该线程置为阻塞状态。当`sleep()`状态超时、join()等待线程终止，或者超时、或者I/O处理完毕时，线程重新转入可运行状态。

可运行状态就是线程执行`start`时，就是可运行状态，一旦CPU切换到这个线程就开始执行里面的run方法就进入了运行状态。
上面会出现这个结果，就是因为notify仅仅让一个线程进入了可运行状态，而另一个线程则还在阻塞中。而`notifyAll`则使所有的线程都从等待队列中出来，而因为同步代码的关系，获得锁的线程进入可运行态，没有得到锁的则进入锁池，也是阻塞状态，但是会因为锁的释放而重新进入可运行态。所以notifyAll会让所有wait的线程都会继续执行。

## join方法的使用
wait方法使线程进入阻塞，并且因为通知而唤醒执行，sleep方法同样使线程进入阻塞，并且因此超时而结束阻塞。以上两者都是因为特定的条件而结束阻塞，现在主线程需要知道子线程的结果再继续执行，这个时候要怎么做，用通知/等待不是很容易实现这个操作，sleep则完全不知道要等待的时间。因此Java提供了一个`join()`方法，`join()`方法是Thread对象的方法，他的功能是使所属的线程对象x正常执行run方法的内容，而使当前线程z进行无限期的阻塞，等待线程x销毁后在继续执行线程z后面的代码。这说起来有点绕口，其实看例子就很简单。
```
public class JoinThread extends Thread{
    @Override
    public void run() {
        super.run();
        try{
            int secondValue = (int)(Math.random() * 10000);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
```
其测试的方法如下：
```
    public void testRun() throws Exception {
        JoinThread joinThread = new JoinThread();
        joinThread.start();
        joinThread.join();
        System.out.println("我想当Join对象执行完毕后我再执行，我做到了");

    }
```
结果如下：
```
3519
我想当Join对象执行完毕后我再执行，我做到了
```
看上去join方法很神奇，可以实现线程在执行上面的次序。但是实际上join方法内部是通过wait实现的。
```
 public final synchronized void join(long millis)
    throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            while (isAlive()) {
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }
```
这个join的原理很简单，前面那些if条件不管，主要看while循环里面的，while循环就是不断去判断`this.isAlive`的结果，用上面的例子，这个this就是`joinThread`。然后关键的代码就是`wait(delay);`一个定时的wait。这个wait的对象也是this,就是`joinThread`。上面我们已经讲了wait一定要在同步方法或者同步代码块中，源码中join方法的修饰符就是一个`synchronized`，表明这是一个同步的方法。
不要看调用wait是`joinThread`，是一个线程。但是真正因为wait进入阻塞状态的，是持有对象监视器的线程，这里的对象监视器是`joinThread`,持有他的是main线程，因为在main线程中执行了join这个同步方法。
所以main线程不断的wait，直到调用join方法那个线程对象销毁，才继续向下执行。
但是源码中只有wait的方法，没有notify的方法。因为notify这个操作是JVM通过检测线程对象销毁而调用的native方法，是C++实现的，在源码中是找不到对应这个wait方法而存在的notify方法的。

## 总结
这里介绍了线程间通信的一种常见的方式——等待/通知机制。此外，还介绍了一种指定线程执行顺序的方法——join方法，并且讲解了其内部的实现。
全部的代码都在[char03线程间通信](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/threadtutorial/char03)







