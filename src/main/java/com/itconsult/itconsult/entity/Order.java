package com.itconsult.itconsult.entity;

import com.itconsult.itconsult.enums.OrderStatus;
import com.itconsult.itconsult.enums.OrderType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date date;
    private String description;
    private OrderStatus orderStatus;
    private OrderType orderType;

    //relations
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

}
