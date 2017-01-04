package cn.byhieg.threadtutorial.char02;

/**
 * Created by shiqifeng on 2017/1/4.
 * Mail byhieg@gmail.com
 */
public class OutClass {

    public static class InnerClass1 {
        public void method1(InnerClass2 class2) {
            String threadName = Thread.currentThread().getName();
            synchronized (class2) {
                System.out.println(threadName + "进入InnerClass1类中的method1方法");
                for (int i = 1; i < 10; i++) {
                    System.out.println(" i=" + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName + "离开InnerClass1类中的method1方法");
            }
        }

        synchronized public void method2() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "进入InnerClass1类中的method2方法");
            for (int j = 0; j <= 10; j++) {
                System.out.println(" j=" + j);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            System.out.println(threadName +" 离开InnerClass1类中的method2方法");
        }
    }

    public static class InnerClass2 {
        synchronized public void method1() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "进入InnerClass2类中的method1方法");
            for (int k = 1; k < 10; k++) {
                System.out.println(" k=" + k);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(threadName + " 离开InnerClass2类中的method1方法");
        }
    }
}
