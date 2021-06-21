package com.csx.concurrency.example.basic;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-03
 */
public class T06_interrupt_and_Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for(;;) {
                if (Thread.interrupted()) {
                    System.out.println("thread is interrupted;");
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();
        // 思考一下，如果在这里写，输出的是哪个线程的中断状态,是当前线程即主线程
        System.out.println("main: " + t.interrupted());
    }
}
