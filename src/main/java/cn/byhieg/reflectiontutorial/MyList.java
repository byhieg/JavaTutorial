package cn.byhieg.reflectiontutorial;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by byhieg on 17/1/9.
 * Mail to byhieg@gmail.com
 */
public class MyList<T> {

    public List<T> lists;
    public List<String> stringLists ;


    public List<String> getStringLists() {
        return stringLists;
    }

    public void setStringLists(List<String> stringLists) {
        this.stringLists = stringLists;
    }

    public void add(T t){
        lists.add(t);
    }

    public T get(int index){
        return lists.get(index);
    }
}
