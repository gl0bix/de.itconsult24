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
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("register/provider")
    public String showRegisterForm(Model model) {
        ProviderRegisterFormModel formModel = new ProviderRegisterFormModel();
        model.addAttribute("provider", formModel);
        return "register/provider_registration";
    }

    @PostMapping("register/provider")
    public String registerProvider(
            @ModelAttribute("provider") @Valid ProviderRegisterFormModel formModel, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "register/provider_registration";
        }

        try {
            providerService.registerNewProvider(formModel);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("emailError", "Es existiert bereits ein Account mit dieser Email-Adresse.");
            return "register/provider_registration";
        }

        return "register/success_register";
    }


    @GetMapping("data/provider")
    public String showProviderData(Authentication authentication, Model model) {

        Optional<Provider> provider = providerService.getProviderByEmail(authentication.getName());

        if (authentication.isAuthenticated() && provider.isPresent()) {
            model.addAttribute("provider", provider.get());
        } else
            return "redirect:/login";

        return "account/provider_data";
    }

}
