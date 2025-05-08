package org.dongwon.scheduleappapi.schedule.repository;

import org.dongwon.scheduleappapi.entity.Schedule;

import java.util.Optional;

public interface ScheduleRepository {
    Long save(Schedule schedule);

    Optional<Schedule> findById(Long id);

    void update(Schedule schedule);

    void delete(Schedule schedule);
}
