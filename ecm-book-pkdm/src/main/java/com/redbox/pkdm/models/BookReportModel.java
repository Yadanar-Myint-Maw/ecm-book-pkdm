package com.redbox.pkdm.models;

import com.redbox.pkdm.entities.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookReportModel {

    @Id
    private String id;

    @ManyToOne
    private Book book;

    long count;

    double price;

    public BookReportModel(){

    }

    public BookReportModel(Book book, long count, double price) {
        this.book = book;
        this.count = count;
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
