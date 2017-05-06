package com.homik.service.impl;

import com.homik.dao.BookDao;
import com.homik.dao.RentDao;
import com.homik.model.Book;
import com.homik.model.Rent;
import com.homik.model.User;
import com.homik.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pawel on 2017-04-11.
 */

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    RentDao rentDao;

    @Autowired
    BookDao bookDao;

    @Override
    @Transactional
    public void createRent(User user, Book book) {

        Rent rent = new Rent(user, book);

        rentDao.save(rent);
        book.decrementQuantiy();
        bookDao.save(book);
    }
}
