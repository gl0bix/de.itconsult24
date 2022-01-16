package com.itconsult.itconsult.repository;

import com.itconsult.itconsult.entity.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

@RepositoryDefinition(domainClass = Provider.class, idClass = Long.class)
public interface ProviderRepository extends CrudRepository<Provider, Long> {
    Optional<Provider> findByEmail(String email);
}
