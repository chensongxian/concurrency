package com.csx.concurrency.example.syncContainer;

import com.csx.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-26
 */
@Slf4j
@ThreadSafe
public class VectorExample1 {
    /**请求总数**/
    public static int clientTotal = 5000;
    /**同时并发执行的线程数**/
    public static int threadTotal = 200;

    public static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",list.size());
    }

    private static void update(int i){
        list.add(i);
    }
}
