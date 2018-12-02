package org.saxing.dao;

/**
 *
 * Customer Schema SQL Class
 *
 * @author saxing 2018/12/2 21:00
 */
public final class CustomerSchemaSql {

    public CustomerSchemaSql() {
    }

    public static final String CREATE_SCHEMA_SQL = "CREATE TABLE CUSTOMERS (ID NUMBER, FNAME VARCHAR(100), "
            + "LNAME VARCHAR(100))";

    public static final String DELETE_SCHEMA_SQL = "DROP TABLE CUSTOMERS";
}
