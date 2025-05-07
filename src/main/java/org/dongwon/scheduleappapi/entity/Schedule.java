package org.dongwon.scheduleappapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String content;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long authorId;

    private Schedule(String content, String password, LocalDateTime createdDate, LocalDateTime updatedDate, Long authorId) {
        this.content = content;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.authorId = authorId;
    }

    public static Schedule createSchedule(String content, String password, Long authorId) {
        return new Schedule(content, password, LocalDateTime.now(), LocalDateTime.now(), authorId);
    }
}
