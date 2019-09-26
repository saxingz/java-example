package org.saxing.validate.model;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.saxing.validate.annotation.ListNotHasNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class User {

    private int id;

    @NotNull(message = "名字不能为空")
    private String name;

    @Length(max = 50, min = 10, message = "地址必须在10~20个字符之间")
    private String address;

    @Min(value = 18, message = "年龄必须 > 18 岁")
    private int age;

    /**
     * 所拥有的书籍列表
     */
    @NotEmpty(message = "所拥有书籍不能为空")
    @ListNotHasNull(message = "List 中不能含有null元素")
    @Valid
    private List<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
}

