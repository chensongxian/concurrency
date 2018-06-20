package com.csx.concurrency.example.atomic;

import com.csx.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-17
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {
    private static AtomicReference<Integer> count=new AtomicReference<>(0);

    public static void main(String[] args) {
        //2
        count.compareAndSet(0,2);
        //no
        count.compareAndSet(0,1);
        //no
        count.compareAndSet(1,3);
        //4
        count.compareAndSet(2,4);
        //no
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());
    }
}
