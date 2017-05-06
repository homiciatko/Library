package com.homik.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Pawel on 2017-03-30.
 */

@Entity
@Table(name = "book")
public class Book extends BaseEntity{

    private String title;

    @NotNull
    @Size(min = 3, max = 255, message = "jestes dupa wolowa, dlugosc to od {min} do {max}")
    private String author;

    @Min(0)
    private Integer available;

    public Book() {

    }

    public Book(String title, String author, Integer quantity) {
        this(title, author);
        this.available = quantity;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = 1;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Integer getAvailable() {
        return available;
    }
    public void setAvailable(Integer available) {
        this.available = available;
    }

    public void decrementQuantiy() {
        available--;
    }
}
