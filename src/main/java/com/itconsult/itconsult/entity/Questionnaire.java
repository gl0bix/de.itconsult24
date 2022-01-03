package com.itconsult.itconsult.entity;

import com.itconsult.itconsult.entity.QuestionaireEnums.OrderType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String urgency;
    private String duration;
    private String CompanyDescription;
    private String ProblemDescription;
    private OrderType orderType;

}
