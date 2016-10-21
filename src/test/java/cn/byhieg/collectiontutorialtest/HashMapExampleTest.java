package cn.byhieg.collectiontutorialtest;

import cn.byhieg.collectiontutorial.maptutorial.HashMapExample;
import junit.framework.TestCase;

import java.util.Map;

/**
 * Created by byhieg on 17/2/25.
 * Mail to byhieg@gmail.com
 */
public class HashMapExampleTest extends TestCase {
    HashMapExample example = new HashMapExample();
    public void testInsertMap() throws Exception {
        Map<String,String> maps = example.insertMap();
        example.getAllKeyAndValue(maps);
    }

    public void testGetValue() throws Exception {

    }


}