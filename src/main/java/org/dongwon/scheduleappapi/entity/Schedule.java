package org.dongwon.scheduleappapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long authorId;

    private Schedule(String content, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Long authorId) {
        this.content = content;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
    }

    public static Schedule createSchedule(String content, String password, Long authorId) {
        return new Schedule(content, password, LocalDateTime.now(), LocalDateTime.now(), authorId);
    }
}
