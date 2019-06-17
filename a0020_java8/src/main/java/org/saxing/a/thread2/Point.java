package org.saxing.a.thread2;

import java.util.concurrent.locks.StampedLock;

class Point {
    private int x, y;
    final StampedLock sl =
            new StampedLock();
    // 计算到原点的距离
    int distanceFromOrigin() {
        // 乐观读
        long stamp =
                sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        // 判断执行读操作期间，
        // 是否存在写操作，如果存在，
        // 则 sl.validate 返回 false
        if (!sl.validate(stamp)){
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                // 释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt(
                curX * curX + curY * curY);
    }
}

/**
 * stampedlock 读模版
 * final StampedLock sl =
 *   new StampedLock();
 *
 * // 乐观读
 * long stamp =
 *   sl.tryOptimisticRead();
 * // 读入方法局部变量
 * ......
 * // 校验 stamp
 * if (!sl.validate(stamp)){
 *   // 升级为悲观读锁
 *   stamp = sl.readLock();
 *   try {
 *     // 读入方法局部变量
 *     .....
 *   } finally {
 *     // 释放悲观读锁
 *     sl.unlockRead(stamp);
 *   }
 * }
 * // 使用方法局部变量执行业务操作
 * ......
 *
 *
 * StampedLock 写模版
 * private double x, y;
 * final StampedLock sl = new StampedLock();
 * // 存在问题的方法
 * void moveIfAtOrigin(double newX, double newY){
 *  long stamp = sl.readLock();
 *  try {
 *   while(x == 0.0 && y == 0.0){
 *     long ws = sl.tryConvertToWriteLock(stamp);
 *     if (ws != 0L) {
 *       x = newX;
 *       y = newY;
 *       break;
 *     } else {
 *       sl.unlockRead(stamp);
 *       stamp = sl.writeLock();
 *     }
 *   }
 *  } finally {
 *   sl.unlock(stamp);
 * }
 */

