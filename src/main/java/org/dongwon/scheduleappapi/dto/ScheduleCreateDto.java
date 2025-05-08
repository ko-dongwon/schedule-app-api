package org.dongwon.scheduleappapi.dto;

import lombok.Data;

@Data
public class ScheduleCreateDto {
    AuthorRequestDto author;
    private String content;
    private String password;
}
