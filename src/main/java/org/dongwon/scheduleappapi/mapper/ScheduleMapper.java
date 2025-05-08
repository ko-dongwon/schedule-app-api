package org.dongwon.scheduleappapi.mapper;

import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.dto.ScheduleResponseDto;
import org.dongwon.scheduleappapi.entity.Author;
import org.dongwon.scheduleappapi.entity.Schedule;

public class ScheduleMapper {
    public static ScheduleResponseDto toDto(Schedule schedule, Author author) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setContent(schedule.getContent());
        dto.setAuthorName(author.getAuthorName());
        dto.setCreatedAt(schedule.getCreatedAt());
        dto.setUpdatedAt(schedule.getUpdatedAt());
        return dto;
    }

    public static Schedule toSchedule(ScheduleCreateDto dto, Author author) {
        return Schedule.createSchedule(dto.getContent(), dto.getPassword(), author.getId());
    }
}
