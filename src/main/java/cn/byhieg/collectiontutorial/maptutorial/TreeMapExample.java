package cn.byhieg.collectiontutorial.maptutorial;


import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by shiqifeng on 2017/2/24.
 * Mail byhieg@gmail.com
 */
public class TreeMapExample {

    public TreeMap<String,String> insertMap(){
        TreeMap<String, String> maps = new TreeMap<>();

        for (int i = 0 ; i < 10;i++) {
            maps.put(i + "", 10 * i + "");
        }

        return maps;
    }

    public void getSmallestEntry(TreeMap<String, String> maps){
        Map.Entry<String,String> entry =  maps.firstEntry();
        System.out.println("最小的Entry如下");
        System.out.print("key = " + entry.getKey());
        System.out.println(" value = " + entry.getValue());
    }

    public void getLargestEntry(TreeMap<String,String> maps){
        Map.Entry<String,String> entry =  maps.lastEntry();
        System.out.println("最大的Entry如下");
        System.out.print("key = " + entry.getKey());
        System.out.println(" value = " + entry.getValue());
    }


    public void getHigherEntry(TreeMap<String,String> maps,String key){
        Map.Entry<String,String> entry = maps.higherEntry(key);
        System.out.println("后一个的Entry如下");
        System.out.print("key = " + entry.getKey());
        System.out.println(" value = " + entry.getValue());
    }

    public void getLowestEntry(TreeMap<String, String> maps, String key) {
        Map.Entry<String,String> entry = maps.lowerEntry(key);
        System.out.println("前一个的Entry如下");
        System.out.print("key = " + entry.getKey());
        System.out.println(" value = " + entry.getValue());
    }

    public void getRangeMap(TreeMap<String, String> maps, String firstKey, String lastKey) {
        SortedMap<String,String> subMaps =  maps.subMap(firstKey, lastKey);
        Iterator iterator = subMaps.entrySet().iterator();
        System.out.println("子Map如下");
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            System.out.print("key = " + entry.getKey());
            System.out.println(" value = " + entry.getValue());
        }
    }

    public void getHeadMap(TreeMap<String, String> maps, String firstKey,boolean isInclusive) {
        SortedMap<String,String> subMaps =  maps.headMap(firstKey, isInclusive);
        Iterator iterator = subMaps.entrySet().iterator();
        System.out.println(firstKey + "以前" + "所有的Map如下");
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            System.out.print("key = " + entry.getKey());
            System.out.println(" value = " + entry.getValue());
        }
    }

    public void getTailMap(TreeMap<String, String> maps, String firstKey,boolean isInclusive) {
        SortedMap<String,String> subMaps =  maps.headMap(firstKey, isInclusive);
        Iterator iterator = subMaps.entrySet().iterator();
        System.out.println(firstKey + "以后" + "所有的Map如下");
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            System.out.print("key = " + entry.getKey());
            System.out.println(" value = " + entry.getValue());
        }
    }
}
