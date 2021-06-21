package com.csx.concurrency.example.falseSharing;

import lombok.extern.java.Log;
import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-04
 */
public class T01_CacheLinePadding {
    private static long COUNT = 10_0000_0000L;

    public static class DataPadding {
        //填充 6个long类型字段 8*4 = 48 个字节
        private long p1, p2, p3, p4, p5, p6, p7;
//        private long p9,p10,p11,p12,p13,p14,p15;
    }

    private static class T {
//        private long p1,p2,p3,p4,p5,p6,p7;
//        @Contended
        public volatile long x = 0L;
        private long p9,p10,p11,p12,p13,p14;
    }

    public static T[] arr = new T[4];

    static {
        arr[0] = new T();
        arr[1] = new T();
        arr[2] = new T();
        arr[3] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);

        Thread t1 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });

        Thread t3 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[2].x = i;
            }
            latch.countDown();
        });

        Thread t4 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[3].x = i;
            }
            latch.countDown();
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        latch.await();
        System.out.println((System.nanoTime() - start)/100_0000);

    }
}
