package com.csx.concurrency.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

/**
 * @author csx
 * @Package com.csx.concurrency.guava
 * @Description: TODO
 * @date 2018/8/3 0003
 */
public class ListenableFutureTest {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        asyncTest();
        System.out.println("结束");
    }


    public static void asyncTest() throws InterruptedException, ExecutionException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                throw new Exception("失败");
//                return count = count + 10;
            }
        });

        future.addListener(new Runnable() {
            /*
             * future任务完成后运行一些方法
             */
            @Override
            public void run() {
                System.out.println("future任务完成");
            }
        },executorService);

//        Futures.addCallback(future, new FutureCallback<Integer>() {
//            @Override
//            public void onSuccess(Integer integer) {
//                System.out.println(integer);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                System.out.println(throwable.getMessage());
//            }
//        });
        while (true){
            if(future.isDone()){
                System.out.println("阻塞结束");
                executorService.shutdown();
                break;
            }
        }
    }
}
