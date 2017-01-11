package cn.byhieg.threadtutorial.char03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiqifeng on 2017/1/10.
 * Mail byhieg@gmail.com
 */
public class MyList {
    public static List list = new ArrayList();

    public static void add(){
        list.add("anyString");
    }

    public static int size(){
        return list.size();
    }
}
