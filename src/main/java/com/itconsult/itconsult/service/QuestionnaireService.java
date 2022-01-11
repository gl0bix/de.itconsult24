package com.itconsult.itconsult.service;

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

    /**
     * Frage:
     * Macht der @return hier Sinn?
     */
    public Questionnaire getEmptyQuestionnaire() {
        return new Questionnaire();
    }

    public Optional<Questionnaire> getQuestionnaire(long id){
        return questionnaireRepository.findById(id);
    }

    public void createOrderFromQuestionnaire() {
        orderService.addOrder("title", new Date(), "Description");
    }

}
