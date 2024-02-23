package com.example.test_translator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TRANS_ENG_VIE")
public class TransEngVie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ENG_ID")
    private String engId;

    @Column(name = "ENG_TEXT", length = 700)
    private String engText;

    @Column(name = "AUDIO_URL")
    private String audioUrl;

    @Column(name = "VIE_ID")
    private String vieId;

    @Column(name = "VIE_TEXT", length = 700)
    private String vieText;
}
