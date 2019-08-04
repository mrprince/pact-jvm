package com.example.personprovider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Provider("person-provider")
@PactBroker(host = "10.122.24.124", port = "80")
@RunWith(SpringRestPactRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonResourceTest {

    @Autowired
    private PersonResource personResource;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State("Person 0 exists")
    public void verifyPersonExists() {
        personResource.addPerson(new Person(0,"Michael Johansen", 50));
    }
}