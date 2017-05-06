package com.homik.dao;

import com.homik.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Pawel on 2017-03-30.
 */

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    Book findByTitleAndAuthor(String title, String author);
}
