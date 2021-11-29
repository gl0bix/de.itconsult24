package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.service.CustomerService;
import com.itconsult.itconsult.controller.form.CustomerFormModel;
import com.itconsult.itconsult.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

}









