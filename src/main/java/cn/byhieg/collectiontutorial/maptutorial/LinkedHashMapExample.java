package cn.byhieg.collectiontutorial.maptutorial;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by shiqifeng on 2017/2/24.
 * Mail byhieg@gmail.com
 */
public class LinkedHashMapExample {

    public LinkedHashMap<String,String> insertMap(){
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();
        for (int i = 0 ; i < 10;i++) {
            maps.put(i + "", 10 * i + "");
        }

        return maps;
    }


    public void printMaps(LinkedHashMap<? extends String, ? extends String> maps) {
        Iterator iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            System.out.print("key = " + entry.getKey());
            System.out.println(" value = " + entry.getValue());
        }
    }
}
