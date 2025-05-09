package org.dongwon.scheduleappapi.schedule.service;

import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.dto.ScheduleResponseDto;
import org.dongwon.scheduleappapi.dto.ScheduleSearch;
import org.dongwon.scheduleappapi.dto.ScheduleUpdateDto;

import java.util.List;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateDto dto);

    ScheduleResponseDto getSchedule(Long id);

    List<ScheduleResponseDto> getSchedules(ScheduleSearch search);

    void updateSchedule(Long id, ScheduleUpdateDto dto);
}
