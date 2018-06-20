package com.csx.concurrency.example.singleton;

import com.csx.concurrency.annoations.NotRecommend;
import com.csx.concurrency.annoations.ThreadSafe;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 懒汉模式
 * @author: csx
 * @Date: 2018-06-20
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3(){

    }

    /**单例对象*/
    private static SingletonExample3 instance=null;

    /**
     * 静态工厂方法
     * @return
     */
    public static synchronized SingletonExample3 getInstance(){
        if(instance==null){
            instance=new SingletonExample3();
        }
        return instance;
    }
}
