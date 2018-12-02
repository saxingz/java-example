package org.saxing.dao;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * main
 *
 * @author saxing 2018/12/2 21:44
 */
//@SpringBootApplication
public class Aa001521DaoApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001521DaoApplication.class, args);
//    }

    private static final String DB_URL = "jdbc:h2:~/dao";
    private static Logger log = Logger.getLogger(Aa001521DaoApplication.class);

    public static void main(String[] args) throws Exception {
        final CustomerDao inMemoryDao = new InMemoryCustomerDao();
        performOperationsUsing(inMemoryDao);

        final DataSource dataSource = createDataSource();
        createSchema(dataSource);
        final CustomerDao dbDao = new DbCustomerDao(dataSource);
        performOperationsUsing(dbDao);
        deleteSchema(dataSource);
    }

    private static void deleteSchema(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(CustomerSchemaSql.DELETE_SCHEMA_SQL);
        }

    }

    private static void createSchema(DataSource dataSource) throws SQLException{
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(CustomerSchemaSql.CREATE_SCHEMA_SQL);
        }
    }

    private static DataSource createDataSource(){
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(DB_URL);
        return dataSource;
    }

    private static void performOperationsUsing(final CustomerDao customerDao) throws Exception{
        addCustomers(customerDao);
        log.info("customerDao.getAllCustomers(): ");
        try (Stream<Customer> customerStream = customerDao.getAll()){
            customerStream.forEach(customer -> log.info(customer));
        }
        log.info("customerDao.getCustomerById(2): " + customerDao.getById(2));
        final Customer customer = new Customer(4, "Dan", "Danson");
        customerDao.add(customer);
        log.info("customerDao.getAllCustomers(): " + customerDao.getAll());
        customer.setFirstName("Daniel");
        customer.setLastName("Danielson");
        customerDao.update(customer);
        log.info("customerDao.getAllCustomers(): ");
        try (Stream<Customer> customerStream = customerDao.getAll()) {
            customerStream.forEach((cust) -> log.info(cust));
        }
        customerDao.delete(customer);
        log.info("customerDao.getAllCustomers(): " + customerDao.getAll());
    }

    private static void addCustomers(CustomerDao customerDao) throws Exception{
        for (Customer customer : generateSampleCustomers()) {
            customerDao.add(customer);
        }
    }

    public static List<Customer> generateSampleCustomers() {
        final Customer customer1 = new Customer(1, "Adam", "Adamson");
        final Customer customer2 = new Customer(2, "Bob", "Bobson");
        final Customer customer3 = new Customer(3, "Carl", "Carlson");
        final List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        return customers;
    }
}
