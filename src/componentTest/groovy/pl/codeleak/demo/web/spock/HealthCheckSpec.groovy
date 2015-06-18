package pl.codeleak.demo.web.spock

import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pl.codeleak.support.ComponentTest
import spock.lang.Specification

@ComponentTest
class HealthCheckSpec extends Specification{

    private RESTClient client

    def setup() {
        client = new RESTClient("http://localhost:9001")
        client.auth.basic("demo", "123")
    }

    def "Should be healthy"() {
        when:
        def response = client.get(
                path: '/health',
                requestContentType: "application/json"
        )
        def json = response.responseData

        then:
        json.status == "UP"
        response.status == 200
    }

}
