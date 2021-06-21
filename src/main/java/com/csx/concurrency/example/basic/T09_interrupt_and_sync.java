package com.csx.concurrency.example.basic;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-03
 */
public class T09_interrupt_and_sync {
    private static Object o = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            synchronized (o) {
            }
            System.out.println("t2 end!");
        });
        t2.start();
        t2.interrupt();
    }
}
