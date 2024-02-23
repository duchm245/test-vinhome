package com.example.test_translator.repo;

import com.example.test_translator.model.TransEngVie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<TransEngVie, Long> {
    Page<TransEngVie> findAll(Pageable pageable);
}
