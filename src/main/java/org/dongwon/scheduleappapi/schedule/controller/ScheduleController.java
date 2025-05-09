package org.dongwon.scheduleappapi.schedule.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.common.pagination.Page;
import org.dongwon.scheduleappapi.dto.*;
import org.dongwon.scheduleappapi.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Validated
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
    public ResponseEntity<Page<ScheduleResponseDto>> getSchedules(ScheduleSearch search,
                                                                  @RequestParam(defaultValue = "1") @Min(1) int page,
                                                                  @RequestParam(defaultValue = "10") @Min(1) int size) {
        Page<ScheduleResponseDto> returnPage = scheduleService.getSchedules(search, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(returnPage);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long scheduleId,
                                               @Valid @RequestBody ScheduleUpdateDto dto) {
        scheduleService.updateSchedule(scheduleId, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> removeSchedule(@PathVariable Long scheduleId,
                                               @Valid @RequestBody PasswordRequest password) {
        scheduleService.removeSchedule(scheduleId, password.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
