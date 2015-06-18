package pl.codeleak.demo.web.spock

import org.apache.commons.lang.builder.EqualsBuilder
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import pl.codeleak.demo.core.Customer
import pl.codeleak.demo.core.RandomCustomer
import pl.codeleak.support.ComponentTest
import spock.lang.Specification

@ComponentTest
class CustomerControllerSpec extends Specification {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123")

    def "should save and get customer"() {
        given:
        Customer expectedCustomer = RandomCustomer.builder().id(null).build()
        URI uri = restTemplate.postForLocation("http://localhost:9000/customer", expectedCustomer);

        when:
        ResponseEntity<Customer> entity = restTemplate.getForEntity(uri, Customer.class);

        then:
        entity.getBody().firstname == expectedCustomer.firstname
        entity.getBody().lastname == expectedCustomer.lastname
        entity.getBody().emailAddress == expectedCustomer.emailAddress
        EqualsBuilder.reflectionEquals(entity.getBody(), expectedCustomer, "id")
    }

    def "should save and get multiple customer"() {
        given:
        Customer expectedCustomer1 = RandomCustomer.builder().id(null).build()
        URI uri1 = restTemplate.postForLocation("http://localhost:9000/customer", expectedCustomer1);
        Customer expectedCustomer2 = RandomCustomer.builder().id(null).build()
        URI uri2 = restTemplate.postForLocation("http://localhost:9000/customer", expectedCustomer2);
        Customer expectedCustomer3 = RandomCustomer.builder().id(null).build()
        URI uri3 = restTemplate.postForLocation("http://localhost:9000/customer", expectedCustomer3);

        when:
        ResponseEntity<Customer[]> entity = restTemplate.getForEntity("http://localhost:9000/customer", Customer[].class);

        then:
        Customer[] customers = entity.getBody()
        customers.find { EqualsBuilder.reflectionEquals(it, expectedCustomer1, "id") }
        customers.find { EqualsBuilder.reflectionEquals(it, expectedCustomer2, "id") }
        customers.find { EqualsBuilder.reflectionEquals(it, expectedCustomer3, "id") }
        customers.length == 3
    }
}
