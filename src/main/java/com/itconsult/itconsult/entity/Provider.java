package com.itconsult.itconsult.entity;

import com.itconsult.itconsult.enums.OrderType;
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
    @Enumerated(EnumType.ORDINAL)
    private OrderType orderType;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 128)
    private String password;
    private boolean enabled;

    //relations
    @OneToMany(mappedBy = "provider")
    private Set<Order> orders;
}
