package com.itconsult.itconsult.repository;


import com.itconsult.itconsult.entity.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = Provider.class, idClass = Long.class)
public interface ProviderRepository extends CrudRepository<Provider, Long> {

    Optional<Provider> findByEmail(String email);
    List<Provider> findAllByOrderType();

    /*
    @Query("SELECT id from Provider")
    List<Long> findAllIdByOrderType();
    */
}

