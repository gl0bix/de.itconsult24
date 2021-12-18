package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.controller.form.CustomerRegisterFormModel;
import com.itconsult.itconsult.service.CustomerService;
import com.itconsult.itconsult.controller.form.CustomerFormModel;
import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.service.Exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor

public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("customers")
    public String showCustomers(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "customers";
    }
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

    @GetMapping("customer/register")
    public String showRegisterForm(Model model){
        CustomerRegisterFormModel formModel = new CustomerRegisterFormModel();
        model.addAttribute("customer",formModel);
        return "customer_registration";
    }

    @PostMapping("customer/register")
    public ModelAndView registerCustomer(
            @ModelAttribute("customer") @Valid CustomerRegisterFormModel formModel,
            HttpServletRequest request, Errors errors) {

        ModelAndView mav = new ModelAndView();

        try {
            Customer customer = customerService.registerNewCustomer(formModel);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for this name/Email already exists.");
        }

        return new ModelAndView("success_register", "customer", formModel);
    }



}









