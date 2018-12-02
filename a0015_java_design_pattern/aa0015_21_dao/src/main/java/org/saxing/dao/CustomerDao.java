package org.saxing.dao;

import java.util.Optional;
import java.util.stream.Stream;

/**
 *  * In an application the Data Access Object (DAO) is a part of Data access layer. It is an object
 *  * that provides an interface to some type of persistence mechanism. By mapping application calls
 *  * to the persistence layer, DAO provides some specific data operations without exposing details
 *  * of the database. This isolation supports the Single responsibility principle. It separates what
 *  * data accesses the application needs, in terms of domain-specific objects and data types
 *  * (the public interface of the DAO), from how these needs can be satisfied with a specific DBMS,
 *  * database schema, etc.
 *  *
 *  * <p>Any change in the way data is stored and retrieved will not change the client code as the
 *  * client will be using interface and need not worry about exact source.
 *
 *  @author saxing 2018/12/2 20:32
 */
public interface CustomerDao {

    /**
     * @return all the customers as a stream. The stream may be lazily or eagerly evaluated based
     *       on the implementation. The stream must be closed after use.
     *
     */
    Stream<Customer> getAll() throws Exception;

    /**
     *
     * @param id
     * @return an optional with customer if a customer with unique identifier <code>id</code> exists, empty optional otherwise.
     * @throws Exception
     */
    Optional<Customer> getById(int id) throws Exception;

    /**
     *
     * @param customer
     * @return
     * @throws Exception
     */
    boolean add(Customer customer) throws Exception;

    /**
     *
     * @param customer
     * @return
     * @throws Exception
     */
    boolean update(Customer customer) throws Exception;

    /**
     *
     * @param customer
     * @return
     * @throws Exception
     */
    boolean delete(Customer customer) throws Exception;
}
