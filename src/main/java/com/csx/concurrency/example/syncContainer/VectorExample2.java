package com.csx.concurrency.example.syncContainer;

import com.csx.concurrency.annoations.NotThreadSafe;
import com.csx.concurrency.annoations.ThreadSafe;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-26
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {

    public static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        while (true){
            for(int i = 0;i<10;i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
              @Override
              public void run(){
                  for(int i=0;i< vector.size();i++){
                      vector.remove(i);
                  }
              }
            };


            Thread thread2 = new Thread(){
                @Override
                public void run(){
                    for(int i=0;i< vector.size();i++){
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }

}
