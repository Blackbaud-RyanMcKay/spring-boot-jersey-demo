package pl.codeleak.demo.hateoas

import org.apache.commons.lang.builder.EqualsBuilder
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import pl.codeleak.demo.core.Customer
import pl.codeleak.demo.core.EmailAddress
import pl.codeleak.support.ComponentTest
import spock.lang.Specification

@ComponentTest
class CustomerControllerSpec extends Specification {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

    def "should return customer"() {
        given:
        Customer expectedCustomer = Customer.builder()
                .firstname("Dave")
                .lastname("Matthews")
                .emailAddress(new EmailAddress("dave@dmband.com"))
                .build()

        when:
        ResponseEntity<Customer> entity =
                restTemplate.getForEntity("http://localhost:9000/customer/1", Customer.class);

        then:
        entity.getBody().firstname == expectedCustomer.firstname
        entity.getBody().lastname == expectedCustomer.lastname
        entity.getBody().emailAddress == expectedCustomer.emailAddress
        EqualsBuilder.reflectionEquals(entity.getBody(), expectedCustomer, "id")
    }
}
