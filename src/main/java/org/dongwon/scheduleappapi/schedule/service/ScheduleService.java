package org.dongwon.scheduleappapi.schedule.service;

import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.dto.ScheduleResponseDto;
import org.dongwon.scheduleappapi.dto.ScheduleSearch;

import java.util.List;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateDto dto);

    ScheduleResponseDto getSchedule(Long id);

    List<ScheduleResponseDto> getSchedules(ScheduleSearch search);
}
