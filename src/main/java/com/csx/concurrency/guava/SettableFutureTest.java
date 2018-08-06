package com.csx.concurrency.guava;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author csx
 * @Package com.csx.concurrency.guava
 * @Description: TODO
 * @date 2018/8/6 0006
 */
public class SettableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SettableFuture<String> settableFuture = SettableFuture.create();
        settableFuture.set("success");
        settableFuture.setException(new RuntimeException("xx"));
        String s = settableFuture.get();
        System.out.println(s);
    }
}
