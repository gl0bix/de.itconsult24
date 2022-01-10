package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.controller.form.ProviderRegisterFormModel;
import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.service.Exceptions.UserAlreadyExistException;
import com.itconsult.itconsult.service.ProviderService;
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
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("register/provider")
    public String showRegisterForm(Model model) {
        ProviderRegisterFormModel formModel = new ProviderRegisterFormModel();
        model.addAttribute("customer", formModel);
        return "provider_registration";
    }

    @PostMapping("register/provider")
    public String registerProvider(
            @ModelAttribute("customer") @Valid ProviderRegisterFormModel formModel, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "provider_registration";
        }

        try {
            providerService.registerNewProvider(formModel);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("emailError", "Es existiert bereits ein Account mit dieser Email-Adresse.");
            return "provider_registration";
        }

        return "success_register";
    }


    @GetMapping("details")
    public String showProviderDetails(Authentication authentication, Model model) {

        if (authentication.isAuthenticated()) {
            Provider provider = providerService.getProviderByEmail(authentication.getName()).get();
            model.addAttribute("provider", provider);
        } else
            return "redirect:/login";

        return "provider_details";
    }

}
