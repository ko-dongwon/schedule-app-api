package org.dongwon.scheduleappapi.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Long> createSchedule(@Valid @RequestBody ScheduleCreateDto dto) {
        Long scheduleId = scheduleService.createSchedule(dto);
        return ResponseEntity.created(URI.create("/schedules/" + scheduleId)).build();
    }
}
