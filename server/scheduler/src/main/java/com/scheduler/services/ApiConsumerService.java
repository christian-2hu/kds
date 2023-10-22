package com.scheduler.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ApiConsumerService {
    private String bearerToken;

    public <T> T postAppFormUrlEncodedAuth(String uri, MultiValueMap<String, String> formData, Class<T> elementClass) {
        return webClient()
            .post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(elementClass)
            .block();
    }

    public <T> T getContent(String uri, Class<T> elementClass) {
        return webClient()
            .get()
            .uri(uri)
            .headers(header -> header.setBearerAuth(this.bearerToken))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(elementClass)
            .block();
    }

    public <T> T[] getContents(String uri, Class<T[]> elementClass) {
        return webClient()
            .get()
            .uri(uri)
            .headers(header -> header.setBearerAuth(this.bearerToken))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(elementClass)
            .block();
    }

    public <T, U> T[] postContent(String uri, U body, Class<T[]> elementClass) {
        return webClient()
            .post()
            .uri(uri)
            .headers(header -> header.setBearerAuth(this.bearerToken))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(body))
            .retrieve()
            .bodyToMono(elementClass)
            .block();
    }


    public void setBearerToken(String token) {
        this.bearerToken = token;
    }
    
    private WebClient webClient() {
        return WebClient
                .builder()
                .build();
    }

}
