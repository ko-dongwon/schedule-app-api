package org.dongwon.scheduleappapi.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.dto.ScheduleResponseDto;
import org.dongwon.scheduleappapi.dto.ScheduleSearch;
import org.dongwon.scheduleappapi.dto.ScheduleUpdateDto;
import org.dongwon.scheduleappapi.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleId) {
        ScheduleResponseDto dto = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(ScheduleSearch search) {
        List<ScheduleResponseDto> dtos = scheduleService.getSchedules(search);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long scheduleId,
                                               @RequestBody ScheduleUpdateDto dto) {
        scheduleService.updateSchedule(scheduleId, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
