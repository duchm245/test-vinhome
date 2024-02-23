CREATE TABLE TRANS_ENG_VIE
(
    id        SERIAL PRIMARY KEY,
    eng_id    VARCHAR(255),
    eng_text  VARCHAR(700),
    audio_url VARCHAR(255),
    vie_id    VARCHAR(255),
    vie_text  VARCHAR(700)
);

-- drop table trans_eng_vie;
--
-- delete from trans_eng_vie;
--
-- ALTER TABLE TRANS_ENG_VIE
--     ALTER COLUMN ENG_TEXT TYPE VARCHAR(700);
--
-- ALTER TABLE TRANS_ENG_VIE
--     ALTER COLUMN vie_text TYPE VARCHAR(700);
