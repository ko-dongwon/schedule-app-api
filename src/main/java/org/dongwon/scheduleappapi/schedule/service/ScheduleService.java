package org.dongwon.scheduleappapi.schedule.service;

import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateDto dto);
}
