package com.itconsult.itconsult.entity;

import com.itconsult.itconsult.service.OrderService;
import com.itconsult.itconsult.types.OrderType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private OrderType orderType;
    private String urgency;
    private String duration;

    @Column(length = 1000)
    @Size(max = 1000)
    private String companyDescription;
    @Column(length = 1000)
    @Size(max = 1000)
    private String problemDescription;



}
