package org.dongwon.scheduleappapi.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class ScheduleResponseDto {
    private Long id;
    private String content;
    private String authorName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
