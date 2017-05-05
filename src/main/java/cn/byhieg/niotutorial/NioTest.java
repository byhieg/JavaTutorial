package cn.byhieg.niotutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiqifeng on 2017/4/10.
 * Mail byhieg@gmail.com
 */
public class NioTest {

    public static void main(String[] args) throws IOException {
        for (int i = 2; i <= 100; i++) {
            if(i == 2 || i == 5 || i == 7 || i == 3){
                System.out.print(i + " ");
                continue;
            }
            if (i % 2 == 0 || i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                continue;
            }

            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 2 ; i <= 100;i++){
            if (isPrime(i)){
                System.out.print(i + " ");
            }
        }
    }


    public static boolean isPrime(int n){
        if(n < 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n);i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;

    }


}
