package com.apilearn.TimesApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


public record BookResponse(
        Results results
){
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Results(
            List<Book> books
    ){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Book(
            String title,
            String author,
            String description,
            String publisher,
            @JsonProperty("book_image") String bookImage,
            @JsonProperty("amazon_product_url") String amazonProductUrl
    ){}
}








