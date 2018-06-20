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
public class SingletonExample6 {
    /**
     * 私有构造函数
     */
    private SingletonExample6(){

    }

    /**单例对象*/
    private static SingletonExample6 instance=null;

    /**
     * 静态构造块的代码顺序会影响代码执行
     */
    static {
        instance=new SingletonExample6();
    }



    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
