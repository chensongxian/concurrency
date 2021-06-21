package com.csx.concurrency.example.thread_end;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-04
 */
public class T03_Interrupt_and_NormalThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            long i = 0L;
            while (Thread.interrupted()) {
                i++;
            }
        });

        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
