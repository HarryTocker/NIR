DELETE FROM law_part_punishments;

DELETE FROM law_parts;

DELETE FROM laws;

DROP TABLE law_part_punishments;

DROP TABLE law_parts;

DROP TABLE laws;

CREATE TABLE punishments(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(128) NOT NULL,
    number DOUBLE NOT NULL,
    type VARCHAR(64) NOT NULL
        CHECK type IN(
            'HOURS',
            'DAYS',
            'WEEKS',
            'MONTHS',
            'YEARS',
            'AMOUNT',
            'THOUSAND_RUBLES',
            'MILLION_RUBLES'
        )
);

CREATE TABLE laws(
    article VARCHAR(8) NOT NULL PRIMARY KEY,
    name VARCHAR(1024) NOT NULL,
    type VARCHAR(64) NOT NULL
        CHECK type IN(
            'INTENTIONAL',
            'NEGLIGENCE',
            'UNKNOWN'
        )
);

CREATE TABLE law_parts(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    law_article VARCHAR(8) NOT NULL,
    name VARCHAR(8192) NOT NULL,
    CONSTRAINT law_article_fk FOREIGN KEY (law_article) REFERENCES laws(article)
);

CREATE TABLE law_part_punishments(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    part_id BIGINT,
    parent_id BIGINT,
    type VARCHAR(128) NOT NULL
        CHECK type IN(
            'FINE',
            'RIGHT_DEPRIVATION',
            'COMPULSORY_WORKS',
            'CORRECTIONAL_LABOR',
            'MILITARY_SERVICE_RESTRICTION',
            'DETENTION_IN_DISCIPLINARY_UNIT',
            'RESTRICTION_OF_FREEDOM',
            'FORCED_LABOR',
            'ARREST',
            'DEPRIVATION_OF_LIBERTY',
            'LIFE_IMPRISONMENT'
        ),
    min_id BIGINT,
    max_id BIGINT,
    is_life_time BOOL NOT NULL,
    CONSTRAINT part_id_fk FOREIGN KEY (part_id) REFERENCES law_parts(id),
    CONSTRAINT parent_id_fk FOREIGN KEY (parent_id) REFERENCES law_part_punishments(id),
    CONSTRAINT min_punish_fk FOREIGN KEY (min_id) REFERENCES punishments(id),
    CONSTRAINT max_punish_fk FOREIGN KEY (max_id) REFERENCES punishments(id)
);


CREATE TABLE crime_categories(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    law_type VARCHAR(64) NOT NULL
        CHECK law_type IN(
            'INTENTIONAL',
            'NEGLIGENCE',
            'UNKNOWN'
        ),
    category_type VARCHAR(64) NOT NULL
        CHECK category_type IN(
            'MINOR',
            'MEDIUM',
            'SERIOUS',
            'PARTICULARLY_SERIOUS'
        ),
    comparison_type VARCHAR(64) NOT NULL
        CHECK comparison_type IN(
            'LESS',
            'BIGGER'
        ),
    punishment_id BIGINT NOT NULL,
    CONSTRAINT punishment_fk FOREIGN KEY (punishment_id) REFERENCES punishments(id)
);

CREATE TABLE users(
    username VARCHAR(64) NOT NULL PRIMARY KEY,
    password VARCHAR(512) NOT NULL
);

CREATE TABLE user_roles(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_username VARCHAR(64) NOT NULL,
    role VARCHAR(32) NOT NULL,
    CONSTRAINT user_fk FOREIGN KEY (user_username) REFERENCES users(username)
);

INSERT INTO users(username, password) VALUES ('admin', '$2a$10$EonT4k4lVwlvROXc5AuUZuPBqlKPTCN85Sxy/jk3s2CSZ690f6Msq');

INSERT INTO user_roles(id, user_username, role) VALUES (0, 'admin', 'ADMIN');