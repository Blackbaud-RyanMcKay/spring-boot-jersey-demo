package pl.codeleak.demo.hateoas

import liquibase.Liquibase
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.integration.spring.SpringLiquibase
import liquibase.resource.ClassLoaderResourceAccessor
import org.apache.commons.lang.builder.EqualsBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import pl.codeleak.demo.core.Customer
import pl.codeleak.demo.core.EmailAddress
import pl.codeleak.support.ComponentTest
import spock.lang.Specification

import java.sql.Connection

@ComponentTest
class CustomerControllerSpec extends Specification {

//    @Autowired
//    private SpringLiquibase springLiquibase
    private RestTemplate restTemplate = new TestRestTemplate("demo", "123")

    def setup() {
//        Connection c = springLiquibase.getDataSource().getConnection();
//        Liquibase liquibase = springLiquibase.createLiquibase(c);
//        liquibase.dropAll()
//        liquibase.update((String)null)
    }

    def "should return customer"() {
        given:
        Customer expectedCustomer = Customer.builder()
                .firstname("Dave")
                .lastname("Matthews")
                .emailAddress(new EmailAddress("dave@dmband.com"))
                .build()
        URI uri = restTemplate.postForLocation("http://localhost:9000/customer", expectedCustomer);

        when:
        ResponseEntity<Customer> entity =
                restTemplate.getForEntity(uri, Customer.class);

        then:
        entity.getBody().firstname == expectedCustomer.firstname
        entity.getBody().lastname == expectedCustomer.lastname
        entity.getBody().emailAddress == expectedCustomer.emailAddress
        EqualsBuilder.reflectionEquals(entity.getBody(), expectedCustomer, "id")
    }
}
