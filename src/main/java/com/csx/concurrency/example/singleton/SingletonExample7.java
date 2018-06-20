package com.csx.concurrency.example.singleton;

import com.csx.concurrency.annoations.Recommend;
import com.csx.concurrency.annoations.ThreadSafe;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.dc.pr.PRError;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 枚举模式，最安全
 * @author: csx
 * @Date: 2018-06-20
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //jvm保证这个方法只调用一次
        Singleton(){
            singleton=new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
