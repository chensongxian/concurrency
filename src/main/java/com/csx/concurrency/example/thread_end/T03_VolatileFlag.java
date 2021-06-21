package com.csx.concurrency.example.thread_end;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-04
 */
public class T03_VolatileFlag {
    private static volatile  boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            long i = 0L;
            while (running) {
                i++;
            }
        });

        t.start();
        TimeUnit.SECONDS.sleep(1);
        running = false;
    }
}
