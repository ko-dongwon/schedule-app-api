package org.dongwon.scheduleappapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class Author {
    private Long id;
    private String authorName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Author(String authorName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, authorName, email, createdAt, updatedAt);
    }

    private Author(Long id, String authorName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.authorName = authorName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Author createAuthor(String authorName, String email) {
        return new Author(authorName, email, LocalDateTime.now(), LocalDateTime.now());
    }

    public static Author createAuthor(Long id, String authorName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Author(id, authorName, email, createdAt, updatedAt);
    }
}
