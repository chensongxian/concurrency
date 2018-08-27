package com.csx.concurrency.example.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author csx
 * @Package com.csx.concurrency.example.lock
 * @Description: TODO
 * @date 2018/8/23 0023
 */
//@Slf4j
public class MutexLock implements Lock {

    public static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -1463649538380129698L;

        /**
         * 当前线程是否独占这个锁
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
//            getExclusiveOwnerThread() == Thread.currentThread();
            return getState() == 1;
        }

        /**
         * 获取锁 0:unlocked; 1:locked
         * @param acquires
         * @return
         */
        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            System.out.println("尝试获取到同步状态的线程是："+Thread.currentThread().getName());
            if(compareAndSetState(0,1)) {
                System.out.println("当前获取到同步状态的线程是："+Thread.currentThread().getName());
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         * @param releases
         * @return
         */
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            if(getState()==0){
                throw new IllegalMonitorStateException("锁未被线程占用");
            }
            System.out.println("释放锁："+Thread.currentThread().getName());
            // 置为null表示锁未被任何线程占用
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个Condition,类似Lock实现中的Condition;await()&&signal()&&sigalAll()
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    /**
     * Sync 其实就是个AQS(继承关系),这个Sync对象为使用者屏蔽锁的实现
     * 使用者只需要通过组合使用这个sync来实现锁的使用
     */
    public final Sync sync = new Sync();

    /**
     * 获取不到锁会加入同步队列中
     */
    @Override
    public void lock() {
        //AQS独占锁获取锁的模板方法
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // AQS独占式可响应中断 获取锁的模板方法
        sync.acquireInterruptibly(1);
    }

    /**
     * 获取成功返回true,获取失败返回false,不进入同步队列
     * @return
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 当前线程释放独占锁
     * @return
     */
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    /**
     * FIFO队列中是否有等待获取锁的线程
     * @return
     */
    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }


    public static void main(String[] args) throws InterruptedException {
        MutexLock mutexLock = new MutexLock();
        // ---------------------------------Task one:
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    mutexLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquired successfully!");
                        TimeUnit.SECONDS.sleep(30);
                        System.out.println(Thread.currentThread().getName() + " done!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mutexLock.unlock();
                    }
                    break;
                }
            }
        }, "Task one").start();
//        TimeUnit.SECONDS.sleep(1000);
        // --------------------------------- Task two:
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    mutexLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquired successfully!");
                        TimeUnit.SECONDS.sleep(50);
                        System.out.println(Thread.currentThread().getName() + " done!");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mutexLock.unlock();
                    }
                    break;
                }
            }
        }, "Task two").start();
//        TimeUnit.MILLISECONDS.sleep(1000);
        // --------------------------------- Task three:
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    mutexLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquired successfully!");
                        TimeUnit.SECONDS.sleep(100);
                        System.out.println(Thread.currentThread().getName() + " done!");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mutexLock.unlock();
                    }
                    break;
                }
            }
        }, "Task three").start();
    }


}
