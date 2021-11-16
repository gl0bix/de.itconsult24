package com.itconsult.itconsult.repository;

import com.itconsult.itconsult.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Customer.class, idClass = Long.class)
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
