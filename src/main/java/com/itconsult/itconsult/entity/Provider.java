package com.itconsult.itconsult.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "")
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

    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 128)
    private String password;
    private boolean enabled;
}
