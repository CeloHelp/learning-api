package com.apilearn.TimesApi.controller;

import com.apilearn.TimesApi.dto.BookDTO;
import com.apilearn.TimesApi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nyt/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

   //@GetMapping("/bestsellers")
   //public List<BookDTO> getBestSellers(@RequestParam String listName) {
   //    return bookService.getBestsellers(listName);
   //}



    @GetMapping("/by-list")
    public List<BookDTO.BookInfo> getBooks(
            @RequestParam String listName,
            @RequestParam String date
    ) {
        List<BookDTO.BookInfo> books = bookService.getBooksByListName(listName, date);
        return books;
    }
@GetMapping("/test")
    public String testEndpoint() {
        return "API Working";
}


}



