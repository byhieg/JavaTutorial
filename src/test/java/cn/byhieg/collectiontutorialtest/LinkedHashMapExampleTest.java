package cn.byhieg.collectiontutorialtest;

import cn.byhieg.collectiontutorial.maptutorial.LinkedHashMapExample;
import junit.framework.TestCase;

import java.util.LinkedHashMap;

/**
 * Created by byhieg on 17/2/25.
 * Mail to byhieg@gmail.com
 */
public class LinkedHashMapExampleTest extends TestCase {
    LinkedHashMapExample example = new LinkedHashMapExample();
    public void testInsertMap() throws Exception {
        LinkedHashMap<String,String> map = example.insertMap();
        example.printMaps(map);
    }

}