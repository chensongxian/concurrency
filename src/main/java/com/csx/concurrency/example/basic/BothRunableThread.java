package com.csx.concurrency.example.basic;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-20
 */
public class BothRunableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自thread");;
            }
        }.start();
    }
}
