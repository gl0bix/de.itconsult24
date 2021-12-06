package com.itconsult.itconsult.service;

import com.itconsult.itconsult.repository.QuestionnaireRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
@AllArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;


}
