package cn.byhieg.threadtutorial.concurrent.blocking;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.sound.midi.SoundbankResource;
import java.lang.management.LockInfo;
import java.util.concurrent.*;

/**
 * Created by byhieg on 17/5/3.
 * Mail to byhieg@gmail.com
 */
public class ArrayBlock {

    private BlockingQueue<String> blockingQueue;

    public ArrayBlock(int index) {
        switch (index) {
            case 0:
                blockingQueue = new ArrayBlockingQueue<String>(3);
                break;
            case 1:
                blockingQueue = new LinkedBlockingQueue<>();
                break;
            case 2:
                blockingQueue = new SynchronousQueue<>();
                break;
        }
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }
}
