package com.example.test_translator.service;

import com.example.test_translator.model.TransEngVie;
import com.example.test_translator.model.dto.MessageResponse;
import com.example.test_translator.model.dto.Paging;
import org.springframework.data.domain.Pageable;

public interface TranslationService {
    MessageResponse importToPostgreSQL(String outputCsvPath);
    Paging<TransEngVie> getTranslations(Pageable pageable);
}
