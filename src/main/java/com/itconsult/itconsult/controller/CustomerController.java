package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.controller.form.CustomerFormModel;
import com.itconsult.itconsult.controller.form.CustomerRegisterFormModel;
import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.service.CustomerService;
import com.itconsult.itconsult.service.Exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @Deprecated
    @GetMapping("customers")
    public String showCustomers(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "customers";
    }

    @Deprecated
    @PostMapping("customers")
    public String addCustomer(@Valid CustomerFormModel form,
                              BindingResult result) {
        //if (result.hasErrors()) ... TODO: add error messages here
        customerService.addCustomer(
                form.getLastname(),
                form.getFirstname(),
                form.getPhoneNumber(),
                form.getStreet() ,
                form.getPostalCode() ,
                form.getCity() ,
                form.getCountry() ,
                form.getEmail() ,
                "geheimesPassword",
                false);

        return "redirect:/customers";
    }

    @GetMapping("register/customer")
    public String showRegisterForm(Model model){
        CustomerRegisterFormModel formModel = new CustomerRegisterFormModel();
        model.addAttribute("customer",formModel);
        return "customer_registration";
    }

    @PostMapping("register/customer")
    public String registerCustomer(
            @ModelAttribute("customer") @Valid CustomerRegisterFormModel formModel, BindingResult result,
            Model model){

        if(result.hasErrors()){
            return "customer_registration";
        }

        try {
            customerService.registerNewCustomer(formModel);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("emailError", "Es existiert bereits ein Account mit dieser Email-Adresse.");
            return "customer_registration";
        }

        return "success_register";
    }


    @GetMapping("details")
    public String showCustomerDetails(Authentication authentication, Model model){

        if (authentication.isAuthenticated()) {
            Customer customer = customerService.getCustomerByEmail(authentication.getName()).get();
            model.addAttribute("customer", customer);
        } else
            return "redirect:/login";

        return "customer_details";
    }

}









