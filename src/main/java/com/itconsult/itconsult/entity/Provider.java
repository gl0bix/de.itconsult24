package com.itconsult.itconsult.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String competence;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    @OneToMany(mappedBy = "provider")
    private Set<Order> orders;
}
