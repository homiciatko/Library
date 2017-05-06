package com.homik.service;

import com.homik.model.Book;
import com.homik.model.User;

/**
 * Created by Pawel on 2017-04-11.
 */
public interface RentService {

    public void createRent(User user, Book book);
}
