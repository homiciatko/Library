package com.homik.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Pawel on 2017-04-09.
 */

@Entity
@Table(name = "rent")
public class Rent extends BaseEntity{

    public enum Status{
        IN_PROGRESS, FINISHED
    }

    @Column(name = "create_date", nullable = false) //generuje kolumne z taka wlasciwoscia
    @NotNull  //dziala w momencie walidacji
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public Rent() {

    }

    public Rent(User user, Book book) {
        this.createDate = new Date();
        this.user = user;
        this.book = book;
        this.status = Status.IN_PROGRESS;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
