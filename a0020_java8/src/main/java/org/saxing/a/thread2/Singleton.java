package org.saxing.a.thread2;

/**
 *
 * 这看上去一切都很完美，无懈可击，但实际上这个 getInstance() 方法并不完美。问题出在哪里呢？出在 new 操作上，我们以为的 new 操作应该是：
 *
 * 分配一块内存 M；
 * 在内存 M 上初始化 Singleton 对象；
 * 然后 M 的地址赋值给 instance 变量。
 * 但是实际上优化后的执行路径却是这样的：
 *
 * 分配一块内存 M；
 * 将 M 的地址赋值给 instance 变量；
 * 最后在内存 M 上初始化 Singleton 对象。
 *
 * 对于双重锁的问题，我觉得任大鹏分析的蛮有道理，线程A进入第二个判空条件，进行初始化时，发生了时间片切换，即使没有释放锁，线程B刚要进入第一个判空条件时，发现条件不成立，直接返回instance引用，不用去获取锁。如果对instance进行volatile语义声明，就可以禁止指令重排序，避免该情况发生。
 * 对于有些同学对CPU缓存和内存的疑问，CPU缓存不存在于内存中的，它是一块比内存更小、读写速度更快的芯片，至于什么时候把数据从缓存写到内存，没有固定的时间，同样地，对于有volatile语义声明的变量，线程A执行完后会强制将值刷新到内存中，线程B进行相关操作时会强制重新把内存中的内容写入到自己的缓存，这就涉及到了volatile的写入屏障问题，当然也就是所谓happen-before问题。
 */
public class Singleton {
    static Singleton instance;
    static Singleton getInstance(){
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}

