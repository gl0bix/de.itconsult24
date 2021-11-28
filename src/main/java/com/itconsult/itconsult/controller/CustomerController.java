package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.Service.CustomerService;
import com.itconsult.itconsult.controller.form.CustomerFormModel;
import com.itconsult.itconsult.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    /**
     * http://localhost:8080/notebooks
     */
    @GetMapping("customers")
    public String showCustomers(Model model) {
        List<Customer> customersList = customerService.getAllCustomers();
        model.addAttribute("customersList", customersList );
        return "customers";
    }
    @PostMapping("customers")
    public String addCustomer(@Valid CustomerFormModel form,
                              BindingResult result) {
        //if (result.hasErrors()) ...
        customerService.addCustomer(form.getfirstname(),
                form.getlastname());

        return "redirect:/customers";
    }


    @GetMapping("secure")
    public String securedPage() {

        return "secure";
    }
}









