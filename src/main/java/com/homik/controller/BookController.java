package com.homik.controller;

import com.homik.dao.BookDao;
import com.homik.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Pawel on 2017-02-16.
 */

@Controller
public class BookController {

    @Autowired
    BookDao bookDao;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooksPage(Model model) {

        model.addAttribute("bookList", bookDao.findAll());

        return "books";
    }

    @RequestMapping(value = "/book-create", method = RequestMethod.GET)
    public String getBookForm(@ModelAttribute Book book) {

        book = new Book();

        return "book-create";
    }

    @RequestMapping(value = "/book-create", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute @Valid Book book, BindingResult result) {

        if (result.hasErrors()) {
            return "/book-create";
        }

        bookDao.save(book);

        return "redirect:/books";
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
    public String getBookEditPage(@PathVariable Long id, Model model){

        model.addAttribute("book", bookDao.findOne(id));

        return "book-create";
    }

    @RequestMapping(value = "/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookDao.delete(id);

        return "redirect:/books";
    }
}
