package org.dongwon.scheduleappapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class Author {
    private Long id;
    private String authorName;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Author(String authorName, String email, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.authorName = authorName;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static Author createAuthor(String authorName, String email) {
        return new Author(authorName, email, LocalDateTime.now(), LocalDateTime.now());
    }
}
