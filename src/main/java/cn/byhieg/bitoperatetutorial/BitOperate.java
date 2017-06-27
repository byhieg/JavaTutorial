package cn.byhieg.bitoperatetutorial;

/**
 * Created by byhieg on 2017/6/27.
 * Mail to byhieg@gmail.com
 */
public class BitOperate {


    public String getRightestOne(int n){
        int res = n & (~n + 1);
        return Integer.toBinaryString(res);
    }
}
