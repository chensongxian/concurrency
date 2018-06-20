package com.csx.concurrency.example.singleton;

import com.csx.concurrency.annoations.NotThreadSafe;
import com.csx.concurrency.annoations.ThreadSafe;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 懒汉模式，双重同步锁单例模式
 * @author: csx
 * @Date: 2018-06-20
 */
@ThreadSafe
public class SingletonExample5 {
    private SingletonExample5(){

    }

    /**
     * 单例对象 volatile + 双重检查机制->禁止指令重排
     */
    private volatile static SingletonExample5 instance=null;
    // 一般顺序
    //1、memory=allocate() 分配对象的内存空间
    //2、ctorInstance() 初始化对象
    //3、instance = memory 设置instance指向刚分配的内存

    public static SingletonExample5 getInstance(){
        if(instance==null){
            synchronized (SingletonExample5.class){
                if(instance==null){
                    instance=new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
