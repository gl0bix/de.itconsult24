package com.itconsult.itconsult.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "itconsult_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    //relations
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer")
    private Set<Questionnaire> questionnaire;

    //  auth requirements

    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 128)
    private String password;
    private boolean enabled;


}
