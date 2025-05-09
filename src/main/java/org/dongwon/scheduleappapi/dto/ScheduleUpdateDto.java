package org.dongwon.scheduleappapi.dto;

import lombok.Data;

@Data
public class ScheduleUpdateDto {
    private String content;
    private String authorName;
    private String password;
}
