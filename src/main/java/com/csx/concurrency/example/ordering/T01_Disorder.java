package com.csx.concurrency.example.ordering;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-06
 */
public class T01_Disorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(2);

            Thread one  = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;

                    latch.countDown();
                }
            });

            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;

                    latch.countDown();
                }
            });
            one.start();
            other.start();
            latch.await();
            String result  = "第" + i + "次 （" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            }
        }
    }
}
