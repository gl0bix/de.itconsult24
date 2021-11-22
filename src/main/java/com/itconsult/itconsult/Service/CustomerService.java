package com.itconsult.itconsult.Service;

import com.itconsult.itconsult.entity.Customer;
import com.itconsult.itconsult.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(long id){
        return customerRepository.findById(id);
    }

    public void addCustomer(){
        /*
        Frage an Daniel: Müsste Methode nicht eigentlich Typ Customer anstatt void sein?
        Und müsste man dann nicht mit Builder die Attribute wie Firstname, Lastname... hier übergeben
         */
    }

}
