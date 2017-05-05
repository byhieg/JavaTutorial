package cn.byhieg.threadtutorial.concurrent.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by byhieg on 17/5/3.
 * Mail to byhieg@gmail.com
 */
public class ArrayBlock {

    private BlockingQueue<String> blockingQueue;

    public ArrayBlock(){
        blockingQueue = new ArrayBlockingQueue<String>(1024);
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }
}
