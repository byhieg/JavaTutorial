package cn.byhieg.designpatterntutorial.singleton;

/**
 * Created by shiqifeng on 2017/3/14.
 * Mail byhieg@gmail.com
 */
public class StaticSingleton {

    private StaticSingleton(){
    }


    public static final StaticSingleton getInstance(){
        return Holder.singleton;
    }

    private static class Holder{
        private static final StaticSingleton singleton = new StaticSingleton();
    }
}
