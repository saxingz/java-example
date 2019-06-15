package org.saxing.a.reflect1;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String userName;
    private String password;
    List<String> books=new ArrayList<String>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public User() {
        super();
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString(){
        return this.id+" "+this.userName+" "+this.password+" ";
    }

}