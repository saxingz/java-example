package org.saxing.abstract_document;


import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author saxing  2018/8/30 19:11
 */
public interface Document {

    /**
     * Puts the value related to the key
     *
     * @param key
     * @param value
     * @return
     */
    Void put(String key, Object value);

    /**
     * Gets the value for the key
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * Gets the stream of child documents
     *
     * @param key
     * @param constructor
     * @param <T>
     * @return
     */
    <T> Stream<T> ChildRen(String key, Function<Map<String, Object>, T> constructor);

}
