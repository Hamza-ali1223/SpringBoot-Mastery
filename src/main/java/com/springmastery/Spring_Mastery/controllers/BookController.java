package com.springmastery.Spring_Mastery.controllers;


import com.springmastery.Spring_Mastery.Model.Book;
import com.springmastery.Spring_Mastery.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }


    @PostMapping
    public Book save(@RequestBody Book book)
    {

        return bookService.createBook(book);
    }
}
