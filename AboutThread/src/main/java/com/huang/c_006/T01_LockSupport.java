package com.huang.c_006;

import java.util.concurrent.locks.LockSupport;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @User:Mingaho
 * @Date:2021/09/17
 * @Time:17:34
 */
public class T01_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2);//叫醒t2
                LockSupport.park();//t1阻塞
            }
        });

        t2 = new Thread(() -> {
            for (char c : aC){
                LockSupport.park();//t2阻塞
                System.out.print(c);
                LockSupport.unpark(t1);//叫醒t1
            }
        });
        t1.start();
        t2.start();
    }
}
