package org.dongwon.scheduleappapi.schedule.service;

import org.dongwon.scheduleappapi.dto.*;

import java.util.List;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateDto dto);

    ScheduleResponseDto getSchedule(Long id);

    List<ScheduleResponseDto> getSchedules(ScheduleSearch search);

    void updateSchedule(Long id, ScheduleUpdateDto dto);

    void removeSchedule(Long id, String password);
}
