package com.itconsult.itconsult.service;

import com.itconsult.itconsult.repository.QuestionaireRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
@AllArgsConstructor
public class QuestionaireService {
    private final QuestionaireRepository questionaireRepository;


}
