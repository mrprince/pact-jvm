package com.example.personconsumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = "person-provider.base-url:http://localhost:8888")
public class PersonServiceClientTest {

    private static final String CONSUMER = "person-consumer";
    private static final String PROVIDER = "person-provider";

    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2(PROVIDER, "localhost", 8888, this);

    @Autowired
    private PersonServiceClient personServiceClient;

    @Pact(consumer = CONSUMER)
    public RequestResponsePact pactUserExists(PactDslWithProvider builder) {
        return builder.given(
                "Person 0 exists")
                .uponReceiving("A request to /users/0")
                .path("/api/person/0")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody()
                        .numberType("id", 0)
                        .stringType("name", "Michael Johansen")
                        .numberType("age", 50)
                ).toPact();
    }

    @PactVerification(fragment = "pactUserExists")
    @Test
    public void userExists() {
        Person person = personServiceClient.getPerson(0);
        assertNotNull(person);
        assertEquals(0, person.getId());
        assertEquals("Michael Johansen", person.getName());
        assertEquals(50, person.getAge());
    }
}