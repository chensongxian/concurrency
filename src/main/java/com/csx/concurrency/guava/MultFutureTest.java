package com.csx.concurrency.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author csx
 * @Package com.csx.concurrency.guava
 * @Description: TODO
 * @date 2018/8/6 0006
 */
public class MultFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        List<ListenableFuture<Boolean>> futureList = Lists.newArrayList();

        ConcurrentMap<String,Integer> map = new ConcurrentHashMap<>();
        ListenableFuture<Boolean> future1 = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                map.put("10",10);
                return true;
            }
        });
        futureList.add(future1);

        ListenableFuture<Boolean> future2 = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                map.put("20",20);
                return true;
            }
        });

        futureList.add(future2);


        ListenableFuture<List<Boolean>> successfulAsList = Futures.successfulAsList(futureList);

        System.out.println(map);
        System.out.println(successfulAsList.get().size());
    }
}
