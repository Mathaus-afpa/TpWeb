package com.example.web.repository;
import com.example.web.CustomProperties;
import com.example.web.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Component
public class PersonRepository {

    @Autowired
    private CustomProperties properties;
    public Person get(Integer id) {
        String baseUrl = properties.getUrl();
        String getURL = baseUrl + "/person/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> response = restTemplate.exchange(
                getURL,
                HttpMethod.GET,
                null,
                Person.class
        );
//        log.debug("PersonRepository.get()");
        return response.getBody();
    }
    public Iterable<Person> getAll(){
        String baseUrl = properties.getUrl();
        String getURL = baseUrl + "/persons";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Person>> response = restTemplate.exchange(
                getURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
//        log.debug("PersonRepository.getAll()");
        return response.getBody();
    }
    public Person create(Person person) {
        String baseUrl = properties.getUrl();
        String getURL = baseUrl + "/person";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange(
                getURL,
                HttpMethod.POST,
                request,
                Person.class
        );
//        log.debug("PersonRepository.create()");
        return response.getBody();
    }
    public Person update(Person person) {
        String baseUrl = properties.getUrl();
        String getURL = baseUrl + "/person/" + person.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange(
                getURL,
                HttpMethod.PUT,
                request,
                Person.class
        );
//        log.debug("PersonRepository.update()");
        return response.getBody();
    }
    public void delete(Integer id) {
        String baseUrl = properties.getUrl();
        String getURL = baseUrl + "/person/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                getURL,
                HttpMethod.DELETE,
                null,
                Void.class
        );
//        log.debug("PersonRepository.delete()");
    }
}