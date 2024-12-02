package com.apilearn.TimesApi.httpclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NYTHttpClient {
    private final RestTemplate restTemplate;

    public NYTHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T get(String url, Class<T> responseType) {
        try{
            return restTemplate.getForObject(url, responseType);
        } catch (Exception e){
            throw new RuntimeException("Erro ao acessar a URL:" + url +". Mensagem:" + e.getMessage(), e);
        }
    }
}
