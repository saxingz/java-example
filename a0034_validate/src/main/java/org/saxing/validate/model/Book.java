package org.saxing.validate.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Book {
    private long id;

    /**
     * 书名
     */
    @NotEmpty(message = "书名不能为空")
    private String bookName;
    /**
     * ISBN号
     */
    @NotNull(message = "ISBN号不能为空")
    private String bookIsbn;
    /**
     * 单价
     */
    @DecimalMin(value = "0.1")
    private double price;
    /**
     * 出版日期
     */
    private Date publicationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", price=" + price +
                ", publicationTime=" + publicationTime +
                '}';
    }
}

