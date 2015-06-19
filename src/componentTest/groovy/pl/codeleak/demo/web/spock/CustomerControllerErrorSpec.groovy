package pl.codeleak.demo.web.spock

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import pl.codeleak.support.ComponentTest
import spock.lang.Specification

@ComponentTest
class CustomerControllerErrorSpec extends Specification {

    private RESTClient client

    def setup() {
        client = new RESTClient("http://localhost:9000")
        client.auth.basic("demo", "123")
    }

    def "Should complain when posting invalid customer"() {
        when:
        def response = client.post(
                path: '/customer',
                requestContentType: "application/json",
                body: [
                        firstname   : "",
                        lastname    : "Doe",
                        emailAddress:
                                [value: "john@dummy.com"]
                ]
        )

        then:
        HttpResponseException ex = thrown()
        ex.statusCode == 400
        ex.response.getData().getText() =~ /may not be empty.*firstname/
    }

}
