package com.csx.concurrency.example.singleton;

import com.csx.concurrency.annoations.NotThreadSafe;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 懒汉模式，双重同步锁单例模式
 * @author: csx
 * @Date: 2018-06-20
 */
@NotThreadSafe
public class SingletonExample4 {
    /**
     * 私有构造函数
     */
    private SingletonExample4(){

    }

    private static SingletonExample4 instance=null;
    // 一般顺序
    //1、memory=allocate() 分配对象的内存空间
    //2、ctorInstance() 初始化对象
    //3、instance = memory 设置instance指向刚分配的内存

    //jvm和cpu优化, 发生指令重拍
    //1、memory=allocate() 分配对象的内存空间
    //2、instance = memory 设置instance指向刚分配的内存
    //3、ctorInstance() 初始化对象
    public static SingletonExample4 getInstance(){
        if(instance==null){
            synchronized (SingletonExample4.class){
                if(instance==null){
                    instance=new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
