package com.itconsult.itconsult.controller;


import com.itconsult.itconsult.controller.form.QuestionnaireFormModel;
import com.itconsult.itconsult.service.QuestionnaireService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/questionnaire")
    public String showQuestionnaireForm(Model model) {

        model.addAttribute("questionnaire", new QuestionnaireFormModel());
        return "questionnaire";
    }

    @PostMapping("/questionnaire")
    public String getFilledQuestionnaire(@ModelAttribute("questionnaire") @Valid QuestionnaireFormModel questionnaire,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return "questionnaire";
        }

        System.out.println(questionnaire);

        //TODO: call questionnaireService to handle questionnaire

        return "redirect:account_customer";
    }
}
