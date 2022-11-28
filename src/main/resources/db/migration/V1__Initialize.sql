CREATE TABLE laws (
    article VARCHAR(8) NOT NULL PRIMARY KEY,
    name VARCHAR(1024) NOT NULL
);

CREATE TABLE law_parts(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    law_article VARCHAR(8) NOT NULL,
    name VARCHAR(4092) NOT NULL,
    CONSTRAINT law_article_fk FOREIGN KEY (law_article) REFERENCES laws(article)
);

CREATE TABLE law_part_punishments(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    part_id BIGINT NOT NULL,
    type VARCHAR(512) NOT NULL,
    min_time VARCHAR(2048),
    max_time VARCHAR(2048),
    date_type VARCHAR(128),
    is_life_time BOOL NOT NULL,
    CONSTRAINT part_id_fk FOREIGN KEY (part_id) REFERENCES law_parts(id)
);