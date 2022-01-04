package com.itconsult.itconsult.service;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.entity.Questionnaire;
import com.itconsult.itconsult.repository.CustomerRepository;
import com.itconsult.itconsult.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private Questionnaire questionnaire;
    private Customer customer;

    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllOrdersByCustomer(){
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> getOrder(long id){
        return orderRepository.findById(id);
    }

    public Order addOrder(String title, Date date, String description){
        return orderRepository.save(Order.builder()
                .title(title)
                .date(date)
                .description(description)
                .build());
    }
}
