package com.itconsult.itconsult.controller;


import com.itconsult.itconsult.controller.form.QuestionnaireFormModel;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                                      BindingResult result, Authentication authentication,
                                      RedirectAttributes redirectAttributes) {

        System.out.println(questionnaireFormModel);

        if (result.hasErrors()) {
            return "questionnaire";
        }

        //fetch customer from context
        customerService.getCustomerByEmail(authentication.getName()).ifPresent(questionnaireFormModel::setCustomer);

        questionnaireService.createOrderFromQuestionnaire(questionnaireFormModel);

        redirectAttributes.addFlashAttribute("success", "Der Fragebogen wurde abgeschickt. \n" +
                "Unter 'Aktive Aufträge' können Sie einen passenden Provider auswählen");

        return "redirect:/account";
    }
}
