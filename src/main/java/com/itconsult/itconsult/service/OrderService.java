package com.itconsult.itconsult.service;

import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.entity.Questionnaire;
import com.itconsult.itconsult.enums.OrderStatus;

import com.itconsult.itconsult.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private Questionnaire questionnaire;
    private Order order;
    private Provider provider;
    private List<Provider> providerList;
    private OrderService orderService;
    private boolean complete;


    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllOrdersByCustomer() {
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> getOrder(long id) {
        return orderRepository.findById(id);
    }

    public Order addOrder(String title, Date date, String description, OrderStatus orderStatus) {
        return orderRepository.save(Order.builder()
                .title(title)
                .date(date)
                .description(description)
                .orderStatus(orderStatus)
                .build());
    }

    public Order createOrderFromQuestionnaire(Questionnaire questionnaire) {
        return orderRepository.save(Order.builder()
                .title(questionnaire.getOrderType().name() + questionnaire.getUrgency() + questionnaire.getDate())
                .description(questionnaire.getProblemDescription())
                .orderStatus(OrderStatus.OPEN)
                .build());
    }


    public void searchProvider() {
        providerList.stream().filter(provider -> provider.getOrderType().equals(order.getOrderType())).collect(Collectors.toList());
    }

    /**
     * TODO
     * CustomerService aufrufen, falls neue Order
     * OrderService Boolean, ob Order vollständig
     * OrderService: commisionOrder(order id)
     * propose Order
     */
}
