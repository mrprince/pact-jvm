package com.example.personprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonResource {

    private List<Person> personList = new ArrayList<>();

    public boolean addPerson(Person person) {
        return personList.add(person);
    }

    @GetMapping("/api/person/{id}")
    public Person getPerson(@PathVariable int id) {
        return personList.get(0);
    }
}
