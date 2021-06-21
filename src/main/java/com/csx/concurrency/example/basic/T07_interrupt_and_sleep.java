package com.csx.concurrency.example.basic;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-03
 */
public class T07_interrupt_and_sleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(5);
        t.interrupt();
    }
}
