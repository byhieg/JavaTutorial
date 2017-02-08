之前已经说道，JVM提供了synchronized关键字来实现对变量的同步访问以及用wait和notify来实现线程间通信。在jdk1.5以后，JAVA提供了Lock类来实现和synchronized一样的功能，并且还提供了Condition来显示线程间通信。
Lock类是Java类来提供的功能，丰富的api使得Lock类的同步功能比synchronized的同步更强大。本文章的所有代码均在[Lock类例子的代码](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/threadtutorial/char04)
本文主要介绍一下内容：

1. Lock类
2. Lock类其他功能
3. Condition类
4. Condition类其他功能
5. 读写锁

## Lock类
Lock类实际上是一个接口，我们在实例化的时候实际上是实例化实现了该接口的类`Lock lock = new ReentrantLock();`。用synchronized的时候，synchronized可以修饰方法，或者对一段代码块进行同步处理。
前面讲过，针对需要同步处理的代码设置对象监视器，比整个方法用synchronized修饰要好。Lock类的用法也是这样，通过Lock对象lock，用`lock.lock`来加锁，用`lock.unlock`来释放锁。在两者中间放置需要同步处理的代码。
具体的例子如下：

```
public class MyConditionService {

    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i = 0 ;i < 5;i++){
            System.out.println("ThreadName = " + Thread.currentThread().getName() + (" " + (i + 1)));
        }
        lock.unlock();
    }
}
```

测试的代码如下：

```
        MyConditionService service = new MyConditionService();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();
        new Thread(service::testMethod).start();

        Thread.sleep(1000 * 5);
```

结果太长就不放出来，具体可以看我源码。总之，就是每个线程的打印1-5都是同步进行，顺序没有乱。
通过下面的例子，可以看出Lock对象加锁的时候也是一个对象锁，持续对象监视器的线程才能执行同步代码，其他线程只能等待该线程释放对象监视器。

```
public class MyConditionMoreService {

    private Lock lock = new ReentrantLock();
    public void methodA(){
        try{
            lock.lock();
            System.out.println("methodA begin ThreadName=" + Thread.currentThread().getName() +
                    " time=" + System.currentTimeMillis());
            Thread.sleep(1000 * 5);

            System.out.println("methodA end ThreadName=" + Thread.currentThread().getName() +
                    " time=" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void methodB(){
        try{
            lock.lock();
            System.out.println("methodB begin ThreadName=" + Thread.currentThread().getName() +
                    " time=" + System.currentTimeMillis());
            Thread.sleep(1000 * 5);

            System.out.println("methodB end ThreadName=" + Thread.currentThread().getName() +
                    " time=" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

```

测试代码如下：

```
 public void testMethod() throws Exception {
        MyConditionMoreService service = new MyConditionMoreService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadA aa = new ThreadA(service);
        aa.setName("AA");
        aa.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        ThreadB bb = new ThreadB(service);
        bb.setName("BB");
        bb.start();

        Thread.sleep(1000 * 30);
    }
    
public class ThreadA extends Thread{

    private MyConditionMoreService service;

    public ThreadA(MyConditionMoreService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}

public class ThreadB extends Thread{

    private MyConditionMoreService service;

    public ThreadB(MyConditionMoreService service){
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.methodB();
    }
}
```

结果如下：

```
methodA begin ThreadName=A time=1485590913520
methodA end ThreadName=A time=1485590918522
methodA begin ThreadName=AA time=1485590918522
methodA end ThreadName=AA time=1485590923525
methodB begin ThreadName=B time=1485590923525
methodB end ThreadName=B time=1485590928528
methodB begin ThreadName=BB time=1485590928529
methodB end ThreadName=BB time=1485590933533
```

可以看出Lock类加锁确实是对象锁。针对同一个lock对象执行的`lock.lock`是获得对象监视器的线程才能执行同步代码 其他线程都要等待。
在这个例子中，加锁，和释放锁都是在try-finally。这样的好处是在任何异常发生的情况下，都能保障锁的释放。

## Lock类其他的功能
如果Lock类只有lock和unlock方法也太简单了，Lock类提供了丰富的加锁的方法和对加锁的情况判断。主要有

- 实现锁的公平
- 获取当前线程调用lock的次数，也就是获取当前线程锁定的个数
- 获取等待锁的线程数
- 查询指定的线程是否等待获取此锁定
- 查询是否有线程等待获取此锁定
- 查询当前线程是否持有锁定
- 判断一个锁是不是被线程持有
- 加锁时如果中断则不加锁，进入异常处理
- 尝试加锁，如果该锁未被其他线程持有的情况下成功

#### 实现公平锁
在实例化锁对象的时候，构造方法有2个，一个是无参构造方法，一个是传入一个boolean变量的构造方法。当传入值为true的时候，该锁为公平锁。默认不传参数是非公平锁。
> 公平锁：按照线程加锁的顺序来获取锁
> 非公平锁：随机竞争来得到锁
此外，JAVA还提供`isFair()`来判断一个锁是不是公平锁。

#### 获取当前线程锁定的个数
Java提供了`getHoldCount()`方法来获取当前线程的锁定个数。所谓锁定个数就是当前线程调用lock方法的次数。一般一个方法只会调用一个lock方法，但是有可能在同步代码中还有调用了别的方法，那个方法内部有同步代码。这样，`getHoldCount()`返回值就是大于1。

**下面的方法用来判断等待锁的情况**

#### 获取等待锁的线程数
Java提供了`getQueueLength()`方法来得到等待锁释放的线程的个数。
#### 查询指定的线程是否等待获取此锁定
Java提供了`hasQueuedThread(Thread thread)`查询该Thread是否等待该lock对象的释放。
#### 查询是否有线程等待获取此锁定
同样，Java提供了一个简单判断是否有线程在等待锁释放即`hasQueuedThreads()`。

**下面的方法用来判断持有锁的情况**

#### 查询当前线程是否持有锁定
Java不仅提供了判断是否有线程在等待锁释放的方法，还提供了是否当前线程持有锁即`isHeldByCurrentThread()`，判断当前线程是否有此锁定。
#### 判断一个锁是不是被线程持有
同样，Java提供了简单判断一个锁是不是被一个线程持有，即`isLocked()`

**下面的方法用来实现多种方式加锁**

####  加锁时如果中断则不加锁，进入异常处理
Lock类提供了多种选择的加锁方法，`lockInterruptibly()`也可以实现加锁，但是当线程被中断的时候，就会加锁失败，进行异常处理阶段。一般这种情况出现在该线程已经被打上interrupted的标记了。

#### 尝试加锁，如果该锁未被其他线程持有的情况下成功
Java提供了`tryLock()`方法来进行尝试加锁，只有该锁未被其他线程持有的基础上，才会成功加锁。

上面介绍了Lock类来实现代码的同步处理，下面介绍Condition类来实现wait/notify机制。

## Condition类
Condition是Java提供了来实现等待/通知的类，Condition类还提供比wait/notify更丰富的功能，Condition对象是由lock对象所创建的。但是同一个锁可以创建多个Condition的对象，即创建多个对象监视器。这样的好处就是可以指定唤醒线程。notify唤醒的线程是随机唤醒一个。
下面，看一个例子，显示简单的等待/通知

```
public class ConditionWaitNotifyService {

    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();


    public void await(){
        try{
            lock.lock();
            System.out.println("await的时间为 " + System.currentTimeMillis());
            condition.await();
            System.out.println("await结束的时间" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void signal(){
        try{
            lock.lock();
            System.out.println("sign的时间为" + System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
```

测试的代码如下：

```
        ConditionWaitNotifyService service = new ConditionWaitNotifyService();
        new Thread(service::await).start();
        Thread.sleep(1000 * 3);
        service.signal();
        Thread.sleep(1000);
```

结果如下：

```
await的时间为 1485610107421
sign的时间为1485610110423
await结束的时间1485610110423
```

condition对象通过`lock.newCondition()`来创建，用`condition.await（）`来实现让线程等待，是线程进入阻塞。用`condition.signal()`来实现唤醒线程。唤醒的线程是用同一个conditon对象调用`await()`方法而进入阻塞。并且和wait/notify一样，await（）和signal（）也是在同步代码区内执行。
此外看出await结束的语句是在获取通知之后才执行，确实实现了wait/notify的功能。下面这个例子是展示唤醒制定的线程。

```
		ConditionAllService service = new ConditionAllService();
        Thread a = new Thread(service::awaitA);
        a.setName("A");
        a.start();

        Thread b = new Thread(service::awaitB);
        b.setName("B");
        b.start();

        Thread.sleep(1000 * 3);

        service.signAAll();

        Thread.sleep(1000 * 4);
```

结果如下：

```
begin awaitA时间为 1485611065974ThreadName=A
begin awaitB时间为 1485611065975ThreadName=B
signAll的时间为1485611068979ThreadName=main
end awaitA时间为1485611068979ThreadName=A
```

该结果确实展示用同一个condition对象来实现等待通知。
对于等待/通知机制，简化而言，就是等待一个条件，当条件不满足时，就进入等待，等条件满足时，就通知等待的线程开始执行。为了实现这种功能，需要进行wait的代码部分与需要进行通知的代码部分必须放在同一个对象监视器里面。执行才能实现多个阻塞的线程同步执行代码，等待与通知的线程也是同步进行。对于wait/notify而言，对象监视器与等待条件结合在一起 即`synchronized（对象）`利用该对象去调用wait以及notify。但是对于Condition类，是对象监视器与条件分开，Lock类来实现对象监视器，condition对象来负责条件，去调用await以及signal。

## Condition类的其他功能
和wait类提供了一个最长等待时间，`awaitUntil（Date deadline）`在到达指定时间之后，线程会自动唤醒。但是无论是await或者awaitUntil，当线程中断时，进行阻塞的线程会产生中断异常。Java提供了一个`awaitUninterruptibly`的方法，使即使线程中断时，进行阻塞的线程也不会产生中断异常。

## 读写锁
Lock类除了提供了`ReentrantLock`的锁以外，还提供了`ReentrantReadWriteLock`的锁。读写锁分成两个锁，一个锁是读锁，一个锁是写锁。读锁与读锁之间是共享的，读锁与写锁之间是互斥的，写锁与写锁之间也是互斥的。
看下面的读读共享的例子：

```
public class ReadReadService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(){
        try{
            try{
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName() +
                " " + System.currentTimeMillis());
                Thread.sleep(1000 * 10);
            }finally {
                lock.readLock().unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
```

测试的代码和结果如下：

```
        ReadReadService service = new ReadReadService();
        Thread a = new Thread(service::read);
        a.setName("A");

        Thread b = new Thread(service::read);
        b.setName("B");

        a.start();
        b.start();
```

结果如下：

```
获得读锁A 1485614976979
获得读锁B 1485614976981
```

两个线程几乎同时执行同步代码。
下面的例子是写写互斥的例子

```
public class WriteWriteService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(){
        try{
            try{
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName() +
                        " " +System.currentTimeMillis());
                Thread.sleep(1000 * 10);
            }finally {
                lock.writeLock().unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
```

测试代码和结果如下：

```
        WriteWriteService service = new WriteWriteService();
        Thread a = new Thread(service::write);
        a.setName("A");
        Thread b = new Thread(service::write);
        b.setName("B");

        a.start();
        b.start();
        Thread.sleep(1000 * 30);
```

结果如下：

```
获得写锁A 1485615316519
获得写锁B 1485615326524
```

两个线程同步执行代码

读写互斥的例子：

```
    public class WriteReadService {

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
```

测试的代码如下：

```
        WriteReadService service = new WriteReadService();
        Thread a = new Thread(service::write);
        a.setName("A");
        a.start();
        Thread.sleep(1000);

        Thread b = new Thread(service::read);
        b.setName("B");
        b.start();

        Thread.sleep(1000 * 30);
```

结果如下：

```
获得写锁A 1485615633790
获得读锁B 1485615643792
```

两个线程读写之间也是同步执行代码。

## 总结
本文介绍了新的同步代码的方式Lock类以及新的等待/通知机制的实现Condition类。本文只是很简单的介绍了他们的概念和使用的方式。关于Condition以及读写锁还有更多的内容，将放在以后的博客中。