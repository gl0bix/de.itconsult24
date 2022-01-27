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
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private List<Provider> providerList;
    private final ProviderService providerService;
    private final CustomerService customerService;


    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllOrdersByCustomer(long id) {
        return orderRepository.findAllByCustomer(customerService.getCustomerById(id).get());
    }

    public List<Order> getAllOrdersByProvider(long id) {
        return orderRepository.findAllByProvider(providerService.getProvider(id).get());
    }

    public Order findOrder(long id) {

        return orderRepository.findOrderById(id);
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

    public void createOrderFromQuestionnaire(Questionnaire questionnaire) {
        Order order = Order.builder()
                .title(questionnaire.getOrderType().getValue() + ": " + questionnaire.getUrgency())
                .description(questionnaire.getProblemDescription() + descriptionToString(questionnaire))
                .date(questionnaire.getDate())
                .orderStatus(OrderStatus.OPEN)
                .orderType(questionnaire.getOrderType())
                .customer(questionnaire.getCustomer())
                .build();

        order.setMatchingProviders(extractProviderIds(providerService.getProviderByOrderType(order.getOrderType())));

        orderRepository.save(order);

    }

    public List<Provider> searchProvider(Order order) {
        return providerList.stream().filter(provider -> provider.getOrderType().equals(order.getOrderType())).collect(Collectors.toList());
    }

    public boolean orderComplete(long orderId) {

        if (getOrder(orderId).isPresent()) {
            Order order = getOrder(orderId).get();
            return (order.getOrderType() != null && order.getOrderStatus() != null
                    && order.getDate() != null && order.getTitle() != null && order.getDescription() != null
                    && order.getCustomer() != null);
        }
        return false;
    }

    public void commissionOrder(long orderId, long providerId) {
        if (getOrder(orderId).isPresent() && providerService.getProvider(providerId).isPresent()) {
            var order = getOrder(orderId).get();
            order.setProvider(providerService.getProvider(providerId).get());
            order.setOrderStatus(OrderStatus.IN_PROGRESS);
            orderRepository.save(order);
        }
    }

    public void setStatusToFulfilled(long orderId) {
        getOrder(orderId).ifPresent(order -> {
            changeOrderStatus(OrderStatus.FULFILLED, order);
            orderRepository.save(order);
        });
    }

    public void setStatusToDiscarded(long orderId) {
        getOrder(orderId).ifPresent(order -> {
            changeOrderStatus(OrderStatus.DISCARDED, order);
            orderRepository.save(order);
        });
    }

    private List<Long> extractProviderIds(List<Provider> providers) {
        return providers.stream().map(Provider::getId).collect(Collectors.toList());
    }

    private void changeOrderStatus(OrderStatus status, Order order) {
        if (order != null) {
            order.setOrderStatus(status);
        }
    }

    //descriptions implemented in createOrderFromQuestionnaire()
    private String descriptionToString(Questionnaire questionnaire) {
        StringBuilder desc = new StringBuilder();

        if (Strings.isNotEmpty(questionnaire.getTypeOfAttack()))
            desc.append("\nArt der Attacke: ").append(questionnaire.getTypeOfAttack());

        if (Strings.isNotEmpty(questionnaire.getTypeOfMeasure()))
            desc.append("\nArt der Maßnahme: ").append(questionnaire.getTypeOfMeasure());

        if (Strings.isNotEmpty(questionnaire.getTypeOfDevices()))
            desc.append("\nGeräteart: ").append(questionnaire.getTypeOfDevices());

        if (Strings.isNotEmpty(questionnaire.getTypeOfSoftware()))
            desc.append("\nSoftwaretyp: ").append(questionnaire.getTypeOfSoftware());

        if (Strings.isNotEmpty(questionnaire.getTypeOfCloud()))
            desc.append("\nTyp der Cloud: ").append(questionnaire.getTypeOfCloud());

        if (Strings.isNotEmpty(questionnaire.getNetwork()))
            desc.append("\nNetzwerk: ").append(questionnaire.getNetwork());

        if (Strings.isNotEmpty(questionnaire.getNetworkDetails()))
            desc.append("\nNetzwerk Details: ").append(questionnaire.getNetworkDetails());

        if (Strings.isNotEmpty(questionnaire.getProjectStatus()))
            desc.append("\nProjekt Status: ").append(questionnaire.getProjectStatus());

        if (Strings.isNotEmpty(questionnaire.getTypeOfProject()))
            desc.append("\nProjektart: ").append(questionnaire.getTypeOfProject());

        if (Strings.isNotEmpty(questionnaire.getSystemadmin()))
            desc.append("\nSystemadmin: ").append(questionnaire.getSystemadmin());

        return desc.toString();
    }
}
