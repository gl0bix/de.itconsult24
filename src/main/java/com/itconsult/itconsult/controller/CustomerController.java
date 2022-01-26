package com.itconsult.itconsult.controller;

import com.itconsult.itconsult.controller.form.CustomerFormModel;
import com.itconsult.itconsult.controller.form.CustomerRegisterFormModel;
import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.service.CustomerService;
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
import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProviderService providerService;


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
        return "register/customer_registration";
    }

    @PostMapping("register/customer")
    public String registerCustomer(
            @ModelAttribute("customer") @Valid CustomerRegisterFormModel formModel, BindingResult result,
            Model model){

        if(result.hasErrors()){
            return "register/customer_registration";
        }

        try {
            customerService.registerNewCustomer(formModel);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("emailError", "Es existiert bereits ein Account mit dieser Email-Adresse.");
            return "register/customer_registration";
        }

        return "register/success_register";
    }


    @GetMapping("data/customer")
    public String showCustomerData(Authentication authentication, Model model) {

        Optional<Customer> customer = customerService.getCustomerByEmail(authentication.getName());

        if (authentication.isAuthenticated() && customer.isPresent()) {
            model.addAttribute("customer", customer.get());
        } else
            return "redirect:/login";

        return "account/customer_data";
    }

    @GetMapping("/data/customer/orders")
    public String showCustomerOrders(Authentication authentication, Model model) {

        Optional<Customer> customer = customerService.getCustomerByEmail(authentication.getName());

        if (authentication.isAuthenticated() && customer.isPresent()) {
            model.addAttribute("customer", customer.get());
        } else
            return "redirect:/login";


        List<Order> orderList = orderService.getAllOrders();
        model.addAttribute("orderList", orderList);


        return "account/customer_order_data";
    }

    @GetMapping("/data/customer/orders/details/{orderID}")
    public String showCustomerOrdersDetail(@PathVariable("orderID") long orderID, Authentication authentication, Model model) {

        Optional<Customer> customer = customerService.getCustomerByEmail(authentication.getName());

        if (authentication.isAuthenticated() && customer.isPresent()) {
            model.addAttribute("customer", customer.get());
        } else
            return "redirect:/login";

        Order order = orderService.findOrder(orderID);
        model.addAttribute("order", order);

        List<Provider> provider = providerService.getAllProviders();
        model.addAttribute("provider", provider);


        return "account/customer_order_data_details";
    }


}









