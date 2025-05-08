package org.dongwon.scheduleappapi.schedule.service;

import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.dto.ScheduleResponseDto;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateDto dto);

    ScheduleResponseDto getSchedule(Long id);
}
