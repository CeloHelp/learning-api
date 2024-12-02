package com.apilearn.TimesApi.services;
import com.apilearn.TimesApi.dto.BookDTO;
import com.apilearn.TimesApi.dto.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    private final RestTemplate restTemplate;
    private final String API_KEY = "your-api-key";
    private final String BASE_URL = "https://api.nytimes.com/svc/books/v3";


    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookDTO> getBestsellers(String listName) {
        String url = BASE_URL + "/lists/current/" + listName + "?api-key=" + API_KEY;
        BookResponse response = restTemplate.getForObject(url, BookResponse.class);

        return response.results().books().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BookDTO convertToDTO(BookResponse.Book book) {
        return new BookDTO(
                book.title(),
                book.author(),
                book.description(),
                book.publisher(),
                book.bookImage(),
                book.amazonProductUrl()
        );


    }

    public List<BookDTO> getBooksByListName(String listName, String date) {

        // URL Base
        String url = String.format("%s?list=%s&published_date=%s&api-key=%s",
                BASE_URL,
                listName,
                date,
                API_KEY);
        try {
            BookResponse response = restTemplate.getForObject(url, BookResponse.class);

            if (response != null && response.results() != null && !response.results().books().isEmpty()) {
                return response.results().books().stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                throw new RuntimeException("Nenhum livro encontrado com os parâmetros fornecidos");

            }
        } catch (Exception e) {}
        return null;
    }
}

