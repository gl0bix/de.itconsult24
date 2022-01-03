package com.itconsult.itconsult.service;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.entity.Provider;
import com.itconsult.itconsult.entity.Questionnaire;
import com.itconsult.itconsult.enums.OrderStatus;
import com.itconsult.itconsult.enums.OrderType;
import com.itconsult.itconsult.repository.OrderRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.Validate;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
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
    private ProviderService providerService;
    private CustomerService customerService;
    private boolean complete;


    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllOrdersByCustomer(long id) {
        return orderRepository.findAllByCustomer(customerService.getCustomerById(id).get());
    }

    public List<Order> getAllOrdersByProvider(long id) {
        return orderRepository.findAllByProvider(providerService.getProvider(id).get());
    }

    public Optional<Order> getOrder(long id) {

        return orderRepository.findById(id);
    }

    public Order addOrder(String title, Date date, String description, OrderStatus orderStatus, OrderType orderType, Customer customer) {
        return orderRepository.save(Order.builder()
                .title(title)
                .date(date)
                .description(description)
                .orderStatus(orderStatus)
                .orderType(orderType)
                .customer(customer)
                .build());
    }

    public Order createOrderFromQuestionnaire(Questionnaire questionnaire) {
        return orderRepository.save(Order.builder()
                .title(questionnaire.getOrderType().name() + questionnaire.getUrgency() + questionnaire.getDate())
                .description(questionnaire.getProblemDescription())
                .orderStatus(OrderStatus.OPEN)
                .orderType(questionnaire.getOrderType())
                .customer(questionnaire.getCustomer())
                .build());
    }


    public void searchProvider() {
        providerList.stream().filter(provider -> provider.getOrderType().equals(order.getOrderType())).collect(Collectors.toList());
    }

    public boolean orderComplete() {
        return (order.getOrderType() != null && order.getOrderStatus() != null
                && order.getDate() != null && order.getTitle() != null && order.getDescription() != null
                && order.getCustomer() != null);
    }

    public void commissionOrder(long orderId, String providerEmail){
        if (providerEmail != null){
            if (getOrder(orderId).isPresent() && providerService.getProviderByEmail(providerEmail).isPresent()){
                getOrder(orderId).get().setProvider(providerService.getProviderByEmail(providerEmail).get());
            }
        }
    }



    /**
     * TODO
     * CustomerService aufrufen, falls neue Order
     * propose Order
     *
     * OrderService Boolean, ob Order vollständig (CHECKED)
     * OrderService: commissionOrder(order id) (CHECKED)
     */
}
