package org.saxing.a.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ResourceLock {

    private static final Logger log = LoggerFactory.getLogger(ResourceLock.class);

    // 初始化ConcurrentHashMap锁载体
    private static final ConcurrentHashMap<String, AtomicInteger> lockMap = new ConcurrentHashMap<String, AtomicInteger>();

    public static AtomicInteger getAtomicInteger(String key) {
        if (lockMap.get(key) == null) {// 当实体ID锁资源为空,初始化锁
            lockMap.putIfAbsent(key, new AtomicInteger(0));// 初始化一个竞争数为0的原子资源
        }
        int count = lockMap.get(key).incrementAndGet();// 线程得到该资源,原子性+1
        log.debug("资源ID为:" + key + ",争抢线程数:" + count);
        return lockMap.get(key);// 返回该ID资源锁
    }

    public static void giveUpAtomicInteger(String key) {
        if (lockMap.get(key) != null) {// 当实体ID资源不为空,才可以操作锁,防止抛出空指针异常
            int source = lockMap.get(key).decrementAndGet();// 线程释放该资源,原子性-1
            if (source <= 0) {// 当资源没有线程竞争的时候，就删除掉该锁,防止内存溢出
                lockMap.remove(key);
                log.debug("资源ID为:" + key + "移除成功");
            }
            log.debug("资源ID为:" + key + ",争抢线程数:" + source);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            test();
        }
    }

    public static void test(){
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    String ID = finalI + "a";
                    AtomicInteger resource = ResourceLock.getAtomicInteger(ID);
                    synchronized (resource) {// 防止多个线程同时操作数据同一条实体数据
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.debug(ID);
                    }
                    ResourceLock.giveUpAtomicInteger(ID);
                }
            }.start();
        }
    }

}
