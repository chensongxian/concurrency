package com.csx.concurrency.example.threadLocal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-21
 */
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder=new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
