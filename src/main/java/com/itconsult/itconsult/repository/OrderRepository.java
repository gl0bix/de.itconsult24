package com.itconsult.itconsult.repository;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.entity.Order;
import com.itconsult.itconsult.entity.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


@RepositoryDefinition(domainClass = Order.class, idClass = Long.class)
public interface OrderRepository extends CrudRepository<Order, Long> {
        List<Order> findAllByCustomer(Customer customer);
        List<Order> findAllByProvider(Provider provider);
}
