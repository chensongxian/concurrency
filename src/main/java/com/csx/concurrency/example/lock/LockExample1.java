package com.csx.concurrency.example.lock;

import com.csx.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-27
 */
@Slf4j
@ThreadSafe
public class LockExample1 {
    /**同时并发执行的线程数*/
    private static int threadTotal=200;
    /**请求总数*/
    private static int clientTotal=5000;

    private static int count=0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("count:{}",count);

    }

    public synchronized static void add(){
        count++;
    }
}
