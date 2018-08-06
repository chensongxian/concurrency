package com.csx.concurrency.guava;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;

/**
 * @author csx
 * @Package com.csx.concurrency.guava
 * @Description: TODO
 * @date 2018/8/3 0003
 */
public class ApplicationTest implements AsyncFunction<Long,String>{
    private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();
    private ListeningExecutorService listeningExecutorService;
    //这里简单的模拟一个service
    private Map<Long,String> service = new HashMap<Long, String>(){
        {
            put(1L,"retrieved");
        }
    };

    @Override
    public ListenableFuture<String> apply(final Long input) throws
            Exception {
        if (map.containsKey(input)) {
            SettableFuture<String> listenableFuture = SettableFuture.
                    create();
            listenableFuture.set(map.get(input));
            return listenableFuture;
        } else {
            return listeningExecutorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    //service中通过input获取retrieved
                    String retrieved = service.get(input);
                    map.putIfAbsent(input, retrieved);
                    return retrieved;
                }
            });
        }
    }
}
