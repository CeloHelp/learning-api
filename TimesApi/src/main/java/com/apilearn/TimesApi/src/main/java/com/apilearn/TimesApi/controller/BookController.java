package com.apilearn.TimesApi.controller;

import com.apilearn.TimesApi.dto.BookDTO;
import com.apilearn.TimesApi.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nyt/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bestsellers")
    public List<BookDTO> getBestSellers(@RequestParam String listName) {
        return bookService.getBestsellers(listName);
    }

    @GetMapping
    public List<BookDTO> getBooks(
            @RequestParam String listName,
            @RequestParam String date
    ) {
        return bookService.getBooksByListName(listName, date);
    }
}


