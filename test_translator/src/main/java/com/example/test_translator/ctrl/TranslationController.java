package com.example.test_translator.ctrl;

import com.example.test_translator.model.TransEngVie;
import com.example.test_translator.model.dto.MessageResponse;
import com.example.test_translator.model.dto.Paging;
import com.example.test_translator.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TranslationController {
    @Autowired
    private TranslationService translationService;

    /**
     * This API to import translation csv file to PostgreSQL (https://www.elephantsql.com/)
     * @return ResponseEntity<MessageResponse>
     */
    @GetMapping("/import")
    public ResponseEntity<MessageResponse> generateTranslationCsv() {
        String outputCsvPath = "src/main/resources/output.csv";
        return ResponseEntity.ok(translationService.importToPostgreSQL(outputCsvPath));
    }

    /**
     * This API service to show translation data with paging of 10 records
     * @param pageNumber
     * @param pageSize
     * @return ResponseEntity<Paging < TransEngVie>>
     */
    @GetMapping(value = "/translations")
    public ResponseEntity<Paging<TransEngVie>> getTranslations(
            @RequestParam(required = false, defaultValue = "0", name = "page_number") int pageNumber,
            @RequestParam(required = false, defaultValue = "10", name = "page_size") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(translationService.getTranslations(pageable));
    }
}
