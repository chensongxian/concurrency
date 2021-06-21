package com.csx.concurrency.example.basic;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-03
 */
public class T05_interrupt_and_isInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for(;;) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("thread is interrupted;");
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();
    }
}
