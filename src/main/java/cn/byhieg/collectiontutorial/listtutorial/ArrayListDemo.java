package cn.byhieg.collectiontutorial.listtutorial;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by byhieg on 17/2/7.
 * Mail to byhieg@gmail.com
 */
public class ArrayListDemo {

    private ArrayList<Integer> listA = new ArrayList<>();
    private ArrayList<Double> listB = new ArrayList<>(5);
    private ArrayList<Integer> listC;

    public ArrayList<Integer> getListA() {
        return listA;
    }

    public ArrayList<Double> getListB() {
        return listB;
    }

    public ArrayList<Integer> getListC() {
        return listC;
    }

    public void setListC(Collection<? extends Integer> collection) {
        listC = new ArrayList<>(collection);
    }
}
