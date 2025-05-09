package org.dongwon.scheduleappapi.schedule.service;

import org.dongwon.scheduleappapi.common.pagination.Page;
import org.dongwon.scheduleappapi.dto.*;

import java.util.List;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateDto dto);

    ScheduleResponseDto getSchedule(Long id);

    Page<ScheduleResponseDto> getSchedules(ScheduleSearch search, int page, int size);

    void updateSchedule(Long id, ScheduleUpdateDto dto);

    void removeSchedule(Long id, String password);
}
