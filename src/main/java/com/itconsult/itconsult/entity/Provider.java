package com.itconsult.itconsult.entity;

import com.itconsult.itconsult.enums.OrderType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private OrderType orderType;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    @OneToMany
    private List<Order> order;
}