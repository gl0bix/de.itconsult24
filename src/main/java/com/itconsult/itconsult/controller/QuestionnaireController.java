package com.itconsult.itconsult.controller;


import com.itconsult.itconsult.controller.form.QuestionnaireFormModel;
import com.itconsult.itconsult.security.CustomerDetails;
import com.itconsult.itconsult.service.CustomerService;
import com.itconsult.itconsult.service.QuestionnaireService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;
    private final CustomerService customerService;


    @GetMapping("/questionnaire")
    public String showQuestionnaireForm(Model model) {

        model.addAttribute("questionnaire", new QuestionnaireFormModel());
        return "questionnaire";
    }

    @PostMapping("/questionnaire")
    public String submitQuestionnaire(@ModelAttribute("questionnaire") @Valid QuestionnaireFormModel questionnaireFormModel,
                                      BindingResult result, Authentication authentication) {

        System.out.println(questionnaireFormModel);

        if (result.hasErrors()) {
            return "questionnaire";
        }

        //fetch customer from context
        final var customerDetails = (CustomerDetails) authentication.getPrincipal();
        final var customer = customerService.getCustomerByEmail(customerDetails.getUsername()).get();

        questionnaireFormModel.setCustomer(customer);

        System.out.println(questionnaireFormModel);

        questionnaireService.createOrderFromQuestionnaire(questionnaireFormModel);

        return "redirect:account_customer";
    }
}
