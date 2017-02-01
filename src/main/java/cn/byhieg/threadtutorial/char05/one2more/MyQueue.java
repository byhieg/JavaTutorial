package cn.byhieg.threadtutorial.char05.one2more;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byhieg on 17/2/1.
 * Mail to byhieg@gmail.com
 */
public class MyQueue {

    private List lists = new ArrayList();

    synchronized public void push(Object element){
        try {
            while(lists.size() == 1) {
                this.wait();
            }
            lists.add(element);
            this.notifyAll();
            System.out.println("push = " + lists.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    synchronized public Object pop(){
        Object value = new Object();
        try {
            while(lists.size() == 0) {
                System.out.println("pop操作中的" + Thread.currentThread().getName() + "呈现wait状态");
                this.wait();
            }

            value = "" + lists.get(0);
            lists.remove(0);
            this.notifyAll();
            System.out.println("pop=" + lists.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}
