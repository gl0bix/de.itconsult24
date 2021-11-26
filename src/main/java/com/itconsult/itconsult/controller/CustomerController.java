package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.service.Deskservice;
import lombok.AllArgConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final DeskService deskService;

    @GetMapping("customers")
    public String showCustomers(Model model) {
        List<Customer> customers = deskService.getAllCustomers();
        model.addAttribute(customers);
        return "customers";
    }

    @GetMapping("secure")
    public String securedPage() {
        return "secure";
    }
}
