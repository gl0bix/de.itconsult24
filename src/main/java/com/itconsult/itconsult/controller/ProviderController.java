package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.controller.form.ProviderRegisterFormModel;
import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.service.Exceptions.UserAlreadyExistException;
import com.itconsult.itconsult.service.OrderService;
import com.itconsult.itconsult.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ProviderController {

    private final ProviderService providerService;
    private final OrderService orderService;

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

        providerService.getProviderByEmail(authentication.getName()).ifPresent(
                provider -> model.addAttribute("provider", provider)
        );

        return "account/provider_data";
    }

    @GetMapping("/data/provider/orders")
    public String showProviderOrders(Authentication authentication, Model model) {

        providerService.getProviderByEmail(authentication.getName()).ifPresent(
                provider -> {
                    final var orderList = orderService.getAllOrdersByProvider(provider.getId());
                    model.addAttribute("orderList", orderList);

                }
        );

        return "account/provider_order_data";
    }

    @GetMapping("/data/provider/orders/details/{orderID}")
    public String showCustomerOrdersDetail(@PathVariable("orderID") long orderID, Model model) {

        Order order = orderService.findOrder(orderID);
        model.addAttribute("order", order);


        return "account/provider_order_data_details";
    }
}
