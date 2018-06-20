package com.csx.concurrency.example.singleton;

import com.csx.concurrency.annoations.ThreadSafe;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 恶汉模式，单例实例在类装载的时候进行创建
 * @author: csx
 * @Date: 2018-06-20
 */
@ThreadSafe
public class SingletonExample2 {
    /**
     * 私有构造函数
     */
    private SingletonExample2(){

    }

    /**单例对象*/
    private static SingletonExample2 instance=new SingletonExample2();

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
