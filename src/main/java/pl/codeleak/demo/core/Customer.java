package pl.codeleak.demo.core;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname, lastname;

    @Column
    private EmailAddress emailAddress;

}