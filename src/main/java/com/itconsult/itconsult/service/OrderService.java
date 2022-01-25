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
    private final QuestionnaireService questionnaireService;


    public List<Order> getAllOrders() {
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
        //TODO: add additional dat from questionnaire
        //TODO-CHECKED
        Order order = orderRepository.save(Order.builder()
                .title(questionnaire.getOrderType().name() + questionnaire.getUrgency() + questionnaire.getDate())
                .description(questionnaire.getProblemDescription() + descriptionToString(questionnaire))
                .date(questionnaire.getDate())
                .orderStatus(OrderStatus.OPEN)
                .orderType(questionnaire.getOrderType())
                .customer(questionnaire.getCustomer())
                .build());

        return order;
        //TODO: hier Aufruf um passende Provider nach OrderType zu finden und als Id-Liste in Order zu speichern
    }


    public void searchProvider(Order order) { //TODO: return value missing/ get providerList from Service
        providerList.stream().filter(provider -> provider.getOrderType().equals(order.getOrderType())).collect(Collectors.toList());
    }


    //TODO: @Parameter orderId, pull Order from repo
    //TODO-CHECKED
    public boolean orderComplete(long orderId) {

        if (getOrder(orderId).isPresent()) {
            Order order = getOrder(orderId).get();
            return (order.getOrderType() != null && order.getOrderStatus() != null
                    && order.getDate() != null && order.getTitle() != null && order.getDescription() != null
                    && order.getCustomer() != null);
        }
        return false;
    }

    public void commissionOrder(long id, String providerEmail) {

        //TODO save Order to database (repository.save())
        // TODO-CHECK
        if (providerEmail != null) {
            if (getOrder(id).isPresent() && providerService.getProviderByEmail(providerEmail).isPresent()) {
                getOrder(id).get().setProvider(providerService.getProviderByEmail(providerEmail).get());
                changeOrderStatus(OrderStatus.IN_PROGRESS, getOrder(id).get()); //TODO: Use changeOrderStatus => CHECK
                orderRepository.save(getOrder(id).get());
            }
        }
    }

    //TODO-CHECKED: Create Method SetStatusToFulfilled
    public void setStatusToFulfilled(long orderId) {
        if (getOrder(orderId).isPresent()) {
            changeOrderStatus(OrderStatus.FULFILLED, getOrder(orderId).get());
        }
    }

    //TODO-CHECKED: Create Method SetStatusToDiscarded
    public void setStatusToDiscarded(long orderId) {
        if (getOrder(orderId).isPresent()) {
            changeOrderStatus(OrderStatus.DISCARDED, getOrder(orderId).get());
        }
    }

    //TODO-CHECKED: changeOrderStatus()
    private void changeOrderStatus(OrderStatus status, Order order) {
        if (order != null) {
            order.setOrderStatus(status);
        }
    }


    //descriptions implemented in createOrderFromQuestionnaire()
    private String descriptionToString(Questionnaire questionnaire) {
        StringBuffer desc = new StringBuffer();

        if (questionnaire.getTypeOfAttack() != null)
            desc.append("\nArt der Attacke: " + questionnaire.getTypeOfAttack());

        if (questionnaire.getTypeOfMeasure() != null)
            desc.append("\nArt der Maßnahme: " + questionnaire.getTypeOfMeasure());

        if (questionnaire.getTypeOfDevices() != null)
            desc.append("\nGeräteart: " + questionnaire.getTypeOfDevices());

        if (questionnaire.getTypeOfSoftware() != null)
            desc.append("\nSoftwaretyp: " + questionnaire.getTypeOfSoftware());

        if (questionnaire.getTypeOfCloud() != null)
            desc.append("\nTyp der Cloud: " + questionnaire.getTypeOfCloud());

        if (questionnaire.getNetwork() != null)
            desc.append("\nNetzwerk: " + questionnaire.getNetwork());

        if (questionnaire.getNetworkDetails() != null)
            desc.append("\nNetzwerk Details: " + questionnaire.getNetworkDetails());

        if (questionnaire.getProjectStatus() != null)
            desc.append("\nProjekt Status: " + questionnaire.getProjectStatus());

        if (questionnaire.getTypeOfProject() != null)
            desc.append("\nProjektart: " + questionnaire.getTypeOfProject());

        if (questionnaire.getSystemadmin() != null)
            desc.append("\nSystemadmin: " + questionnaire.getSystemadmin());

        return desc.toString();
    }
}
