package com.csx.concurrency.example.thread_end;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-04
 */
public class T01_Stop {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("go on");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        TimeUnit.SECONDS.sleep(5);
        t.stop();
    }
}
