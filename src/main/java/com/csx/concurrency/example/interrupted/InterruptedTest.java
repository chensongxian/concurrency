package com.csx.concurrency.example.interrupted;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.concurrent.TimeUnit;

/**
 * @author csx
 * @Package com.csx.concurrency.example.interrupted
 * @Description: TODO
 * @date 2018/8/14 0014
 */
public class InterruptedTest implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptedTest());
        thread.start();
        TimeUnit.MILLISECONDS.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){
            try {
                test();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
            }
        }
    }

    public void test() throws InterruptedException {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("test");
        } catch (InterruptedException e) {
            throw e;
        }
    }
}
