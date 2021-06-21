package com.csx.concurrency.example.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-03
 */
public class T11_interrupt_and_lockInterruptibly {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted");
                    System.out.println(Thread.currentThread().isInterrupted());
                } finally {
                    lock.unlock();;
                }
            System.out.println("t1 end!");

        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }
}
