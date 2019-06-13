package org.saxing.a.ConsistentHash;

public class ConsistentHash<T> {




    /**
     * Hash算法对象，用于自定义hash算法
     */
    public interface HashFunc{
        public Long hash(Object key);
    }
}
