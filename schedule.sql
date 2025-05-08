CREATE TABLE authors(
    author_id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    author_name VARCHAR(20) NOT NULL ,
    email VARCHAR(100) NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE schedules (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    content VARCHAR(200) NOT NULL ,
    password VARCHAR(20) NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    updated_at TIMESTAMP NOT NULL ,
    author_id BIGINT NOT NULL ,
    CONSTRAINT fk_schedules_authors FOREIGN KEY (author_id) REFERENCES authors(author_id)
);


