package com.itconsult.itconsult.service;


import com.itconsult.itconsult.controller.form.QuestionnaireFormModel;
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
    private final OrderService orderService;

    public Questionnaire getEmptyQuestionnaire() {
        return new Questionnaire();
    }

    public Optional<Questionnaire> getQuestionnaire(long id) {
        return questionnaireRepository.findById(id);
    }

    public void createOrderFromQuestionnaire(QuestionnaireFormModel form) {
        final var questionnaire = questionnaireRepository.save(Questionnaire.builder()
                .orderType(form.getOrderType())
                .urgency(form.getUrgency())
                .duration(form.getDuration())
                .companyDescription(form.getCompanyDescription())
                .problemDescription(form.getProblemDescription())
                .typeOfMeasure(form.getTypeOfMeasure())
                .typeOfAttack(form.getTypeOfAttack())
                .typeOfDevices(form.getTypeOfDevices())
                .typeOfSoftware(form.getTypeOfSoftware())
                .typeOfCloud(form.getTypeOfCloud())
                .network(form.getNetwork())
                .networkDetails(form.getNetworkDetails())
                .projectStatus(form.getProjectStatus())
                .typeOfProject(form.getTypeOfProject())
                .systemadmin(form.getSystemAdmin())
                .customer(form.getCustomer())
                .date(new Date())
                .build());

        orderService.createOrderFromQuestionnaire(questionnaire);
    }
}
