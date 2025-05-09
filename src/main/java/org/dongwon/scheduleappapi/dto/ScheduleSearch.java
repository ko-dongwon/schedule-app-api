package org.dongwon.scheduleappapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleSearch {
    private Long authorId;
    private LocalDate updatedAt;
}
