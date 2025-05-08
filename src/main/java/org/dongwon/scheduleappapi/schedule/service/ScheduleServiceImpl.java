package org.dongwon.scheduleappapi.schedule.service;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.author.repository.AuthorService;
import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.entity.Schedule;
import org.dongwon.scheduleappapi.mapper.ScheduleMapper;
import org.dongwon.scheduleappapi.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements  ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final AuthorService authorService;

    // author와 schedule의 저장을 하나의 트랜잭션으로 묶음
    @Transactional
    public Long createSchedule(ScheduleCreateDto dto) {
        if(Objects.isNull(dto)) throw new IllegalArgumentException("DTO는 null일 수 없습니다.");

        // author 엔티티 저장
        Long authorId = authorService.createAuthor(dto.getAuthor());

        // schedule 객체 생성
        Schedule schedule = ScheduleMapper.toSchedule(dto, authorId);
        // 저장 후 식별자 반환
        return scheduleRepository.save(schedule);
    }
}
