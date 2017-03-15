package cn.byhieg.designpatterntutorial.singleton;

import java.io.Serializable;

/**
 * 该类线程不安全，是延迟加载的懒汉模式
 * Created by shiqifeng on 2017/3/14.
 * Mail byhieg@gmail.com
 */
public class SimpleSingleton implements Serializable {

    private static SimpleSingleton simpleSingleton;

    private SimpleSingleton(){

    }

    public static SimpleSingleton getInstance(){
        if (simpleSingleton == null) {
            simpleSingleton = new SimpleSingleton();
        }
        return simpleSingleton;
    }


}
