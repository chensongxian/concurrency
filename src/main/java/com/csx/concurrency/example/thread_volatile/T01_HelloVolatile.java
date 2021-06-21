package com.csx.concurrency.example.thread_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-04
 */
public class T01_HelloVolatile {
    private static boolean running = true;

    private static void m() {
        System.out.println("m start");
        while (running) {
            // 内部有 synchronized 可保证可见性
            System.out.println("hello ");
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(T01_HelloVolatile::m, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        running = false;
    }
}
