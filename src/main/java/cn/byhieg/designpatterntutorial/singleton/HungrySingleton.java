package cn.byhieg.designpatterntutorial.singleton;

/**
 * Created by shiqifeng on 2017/3/14.
 * Mail byhieg@gmail.com
 */
public class HungrySingleton {
    private static final HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton(){

    }

//    public static HungrySingleton getSingleton(){
//        return singleton;
//    }
}
