package com.csx.concurrency.guava;


import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author csx
 * @Package com.csx.concurrency.guava
 * @Description: TODO
 * @date 2018/8/6 0006
 */
public class TransformAsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<Integer> count = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 10;
            }
        });

//        ListenableFuture<Integer> transformAsync2 = Futures.transformAsync(count, new AsyncFunction<Integer, Integer>() {
//            @Override
//            public ListenableFuture<Integer> apply(Integer integer) throws Exception {
//                Integer sumCount = integer + 40;
//                ListenableFuture<Integer> sum = executorService.submit(new Callable<Integer>() {
//                    @Override
//                    public Integer call() throws Exception {
//                        System.out.println(sumCount);
//                        return sumCount;
//                    }
//                });
//                return sum;
//            }
//        });
//
//
//        ListenableFuture<Integer> transformAsync1 = Futures.transformAsync(transformAsync2, (AsyncFunction<Integer, Integer>) integer -> {
//            Integer sumCount = integer - 10;
//            ListenableFuture<Integer> sum = executorService.submit(new Callable<Integer>() {
//                @Override
//                public Integer call() throws Exception {
//                    System.out.println(sumCount);
//                    return sumCount;
//                }
//            });
//            return sum;
//        });

//        Integer integer = transformAsync1.get();
//        System.out.println("异步转换："+integer);
    }
}
