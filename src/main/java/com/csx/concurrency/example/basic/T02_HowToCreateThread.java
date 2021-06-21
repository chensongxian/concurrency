package com.csx.concurrency.example.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description: TODO
 * @author: lucachen
 * 请你告诉我启动线程的三种方式
 * 1：Thread 2: Runnable 3:Executors.newCachedThrad
 * 也可以说是5 种 加上lambda、callable
 *
 * @Date: 2021-06-02
 */
public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "success";
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }).start();

        Thread t = new Thread(new FutureTask<String>(new MyCall()));
        t.start();

        Executors.newFixedThreadPool(5).submit(new MyCall());
    }
}
