package com.springmastery.Spring_Mastery.services;


import com.springmastery.Spring_Mastery.Model.Book;
import com.springmastery.Spring_Mastery.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book)
    {
        if(null!=book)
        {
            bookRepository.save(book);

        }
        return book;
    }

    public List getAllBooks()
    {
        return bookRepository.findAll();
    }
}
