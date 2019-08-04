package com.example.personconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonServiceClient {

    private final RestTemplate restTemplate;

    public PersonServiceClient(@Value("${person-provider.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public Person getPerson(int id) {
        return restTemplate.getForObject("/api/person/" + id, Person.class);
    }

}
