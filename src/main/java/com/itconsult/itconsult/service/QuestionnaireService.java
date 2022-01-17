package com.itconsult.itconsult.service;


import com.itconsult.itconsult.types.OrderType;
import com.itconsult.itconsult.entity.Questionnaire;
import com.itconsult.itconsult.repository.QuestionnaireRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private OrderService orderService;

    public Questionnaire getEmptyQuestionnaire() {
        return new Questionnaire();
    }

    public Optional<Questionnaire> getQuestionnaire(long id){
        return questionnaireRepository.findById(id);
    }

    public Questionnaire createOrderFromQuestionaire(String urgency, String duration, String CompanyDescription,
                                                     String ProblemDescription, OrderType orderType) {
        return questionnaireRepository.save(Questionnaire.builder()
                .urgency(urgency)
                .duration(duration)
                .CompanyDescription(CompanyDescription)
                .ProblemDescription(ProblemDescription)
                .orderType(orderType)
                .build());

    }
}
