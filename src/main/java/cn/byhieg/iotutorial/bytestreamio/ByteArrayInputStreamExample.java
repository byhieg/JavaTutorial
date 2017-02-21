package cn.byhieg.iotutorial.bytestreamio;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * Created by byhieg on 17/2/21.
 * Mail to byhieg@gmail.com
 */
public class ByteArrayInputStreamExample {

    public String readFromArray(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        int tmp;
        while ((tmp = bais.read()) != -1) {
            sb.append(Integer.toHexString(tmp));
        }
        return sb.toString();
    }

    public void readMarkAndReset(byte[] bytes,int mark) {
        StringBuffer sb = new StringBuffer();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        bais.mark(mark);

        bais.skip(mark + 1);
        int tmp;
        while ((tmp = bais.read()) != -1) {
            sb.append(Integer.toHexString(tmp));
        }

        System.out.println("越过标记后的字符串");
        System.out.println(sb.toString());

        bais.reset();
        sb.setLength(0);

        int m;
        while ((m = bais.read()) != -1) {
            sb.append(Integer.toHexString(m));
        }

        System.out.println("重置之后的字符串");
        System.out.println(sb.toString());
    }



}
