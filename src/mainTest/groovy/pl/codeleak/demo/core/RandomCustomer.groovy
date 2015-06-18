package pl.codeleak.demo.core

import com.blackbaud.testsupport.RandomGenerator;

public class RandomCustomer {
    public static Customer.CustomerBuilder builder(){
        String firstname = RandomGenerator.getDataFactory().getFirstName()
        String lastname = RandomGenerator.getDataFactory().getLastName()
        return Customer.builder()
                .id(RandomGenerator.id())
                .firstname(firstname)
                .lastname(lastname)
                .emailAddress(new EmailAddress(firstname+"."+lastname+"@test.com"));
    }
}
