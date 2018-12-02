package org.saxing.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This implementation is useful as temporary database or for testing.
 *
 * @author saxing 2018/12/2 21:36
 */
public class InMemoryCustomerDao implements CustomerDao  {

    private Map<Integer, Customer> idToCustomer = new HashMap<>();

    @Override
    public Stream<Customer> getAll() throws Exception {
        return idToCustomer.values().stream();
    }

    @Override
    public Optional<Customer> getById(final int id) throws Exception {
        return Optional.ofNullable(idToCustomer.get(id));
    }

    @Override
    public boolean add(final Customer customer) throws Exception {
        if (getById(customer.getId()).isPresent()){
            return false;
        }
        idToCustomer.put(customer.getId(), customer);
        return false;
    }

    @Override
    public boolean update(final Customer customer) throws Exception {
        return idToCustomer.replace(customer.getId(), customer) != null;
    }

    @Override
    public boolean delete(final Customer customer) throws Exception {
        return idToCustomer.remove(customer.getId()) != null;
    }
}
