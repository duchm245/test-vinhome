package com.example.test_translator.service.impl;

import com.example.test_translator.model.TransEngVie;
import com.example.test_translator.model.dto.MessageResponse;
import com.example.test_translator.model.dto.Paging;
import com.example.test_translator.repo.TranslationRepository;
import com.example.test_translator.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationServiceImpl implements TranslationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TranslationRepository translationRepository;
    @Override
    public MessageResponse importToPostgreSQL(String outputCsvPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(outputCsvPath))) {
            String line;
            List<TransEngVie> transEngVieList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                TransEngVie transEngVie = new TransEngVie();
                transEngVie.setEngId(data[0]);
                transEngVie.setEngText(data[1]);
                transEngVie.setAudioUrl(data[2]);
                transEngVie.setVieId(data[3]);
                transEngVie.setVieText(data[4]);
                transEngVieList.add(transEngVie);
            }
            saveList(transEngVieList);
            return new MessageResponse("Import file Csv successfully!", 200, null);
        } catch (IOException e) {
            e.printStackTrace();
            return new MessageResponse("Import file Csv fail!", 400, null);
        }
    }

    public boolean saveList(List<TransEngVie> list) {
        int batchSize = 5000;
        String sql = "INSERT INTO TRANS_ENG_VIE" +
                "(ENG_ID, ENG_TEXT, AUDIO_URL, VIE_ID,VIE_TEXT)" +
                "VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(
                sql,
                list,
                batchSize,
                (ps, obj) -> {
                    ps.setString(1, obj.getEngId());
                    ps.setString(2, obj.getEngText());
                    ps.setString(3, obj.getAudioUrl());
                    ps.setString(4, obj.getVieId());
                    ps.setString(5, obj.getVieText());
                }
        );
        return true;
    }

    @Override
    public Paging<TransEngVie> getTranslations(Pageable pageable) {
        Page<TransEngVie> page;
        page = translationRepository.findAll(pageable);

        Paging<TransEngVie> paging = new Paging<>();
        paging.setItems(page.getContent());
        paging.setPageSize(pageable.getPageSize());
        paging.setPageNum(pageable.getPageNumber());
        paging.setTotalCount(page.getTotalElements());
        return paging;
    }

}
