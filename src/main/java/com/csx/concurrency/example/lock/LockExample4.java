package com.csx.concurrency.example.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-27
 */
public class LockExample4 {
    class Point {
        private double x,y;
        private final StampedLock sl = new StampedLock();

        public void move(double deltaX,double deltaY){
            long stamp = sl.writeLock();
            try {
                x+=deltaX;
                y+=deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        // 乐观读锁案例
        public double distanceFromOrigin(){
            // 获取一个乐观读锁
            long stamp = sl.tryOptimisticRead();
            // 将两个字段写入本地局部变量
            double currentX = x,currentY = y;
            // 检查发出乐观锁后同时是否有其他写锁发生
            if(!sl.validate(stamp)){
                // 如果没有，再次获得一个读悲观锁
                stamp = sl.readLock();
                try {
                    // 将两个字段读入本地局部变量
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX*currentX+currentY*currentY);
        }

        // 悲观读锁案例
        public void moveIfAtOrigin(double newX,double newY){
            long stamp = sl.readLock();
            try {
                // 循环，检查当前状态是否符合
                while (x == 0.0 && y == 0.0){
                    // 将读锁转换为写锁成功
                    long ws = sl.tryConvertToWriteLock(stamp);
                    // 这是确认转为写锁是否成功
                    if(ws !=0L){
                        //如果成功 替换票据
                        stamp = ws;
                        x = newX;
                        y = newY;
                        break;
                    }else{ // 如果不能成功转换成写锁
                        // 显式释放锁
                        sl.unlockRead(stamp);
                        // 显式直接进行写锁 然后再通过循环再试
                        stamp = sl.writeLock();
                    }
                }
            } finally {
                // 释放读锁或者写锁
                sl.unlock(stamp);
            }
        }
    }
}
