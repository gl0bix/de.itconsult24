package com.itconsult.itconsult.repository;

import com.itconsult.itconsult.entity.Questionaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Questionaire.class, idClass = Long.class)
public interface QuestionaireRepository extends CrudRepository<Questionaire, Long> {
}
