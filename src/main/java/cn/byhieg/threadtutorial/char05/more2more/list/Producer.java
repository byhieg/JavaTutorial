package cn.byhieg.threadtutorial.char05.more2more.list;

/**
 * Created by byhieg on 17/2/1.
 * Mail to byhieg@gmail.com
 */
public class Producer implements Runnable{

    private MyQueue queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        for (;;) {
            queue.push(System.currentTimeMillis());
        }
    }
}
