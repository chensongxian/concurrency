package com.csx.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-27
 */
@Slf4j
public class SemaphoreExample4 {
    private final static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for(int i=0; i<threadCount;i++){
            final int threadNum=i;
            exec.execute(()->{
                try {
                    //尝试获取一个许可
                    if(semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        //释放一个许可
                        semaphore.release();
                    }
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
