package com.csx.concurrency.example.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author csx
 * @Package com.csx.concurrency.example.daemon
 * @Description: TODO
 * @date 2018/8/14 0014
 */
public class DaemonTest implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonTest());
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally 结束");
        }
    }
}
