package com.example.web.service;

import com.example.web.model.Person;
import com.example.web.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person getPerson(Integer id) {
        return personRepository.get(id);
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.getAll();
    }

    public void deletePerson(final Integer id) {
        personRepository.delete(id);
    }

    public Person savePerson(Person person) {
        person.setLastName(person.getLastName().toUpperCase());
        if (person.getId() == null) return personRepository.create(person);
        else return personRepository.update(person);
    }

}