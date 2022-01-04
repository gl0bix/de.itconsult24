package com.itconsult.itconsult.service;

import com.itconsult.itconsult.types.OrderType;
import com.itconsult.itconsult.entity.Questionnaire;
import com.itconsult.itconsult.repository.QuestionnaireRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
@AllArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private OrderService orderService;

    /**
     * Frage:
     * Macht der @return hier Sinn?
     */
    public Questionnaire getEmptyQuestionaire() {
        return new Questionnaire();
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
