package com.csx.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-25
 */
@Slf4j
public class DateFormatExample3 {
    private static DateTimeFormatter dateTimeFormatter= DateTimeFormat.forPattern("yyyyMMdd");
    /**请求总数*/
    public static int clientTotal=5000;
    /**同时并发执行的线程数*/
    public static int threadTotal=200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore =  new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i < clientTotal ; i++){
            final int count=i;
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
    }

    private static void update(int i){
        try {
            log.info("{},{}",i,DateTime.parse("20180208",dateTimeFormatter).toDate());
        }catch (Exception e){
            log.error("parse exception",e);
        }
    }
}
