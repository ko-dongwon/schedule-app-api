package org.dongwon.scheduleappapi.schedule.service;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.author.repository.AuthorService;
import org.dongwon.scheduleappapi.common.exception.PasswordMismatchException;
import org.dongwon.scheduleappapi.dto.ScheduleCreateDto;
import org.dongwon.scheduleappapi.dto.ScheduleResponseDto;
import org.dongwon.scheduleappapi.dto.ScheduleSearch;
import org.dongwon.scheduleappapi.dto.ScheduleUpdateDto;
import org.dongwon.scheduleappapi.entity.Author;
import org.dongwon.scheduleappapi.entity.Schedule;
import org.dongwon.scheduleappapi.mapper.ScheduleMapper;
import org.dongwon.scheduleappapi.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Schedule(id:" + id + ")가 존재하지 않습니다."));

        Author author = authorService.getAuthor(schedule.getAuthorId());

        return ScheduleMapper.toDto(schedule, author);
    }

    public List<ScheduleResponseDto> getSchedules(ScheduleSearch search) {
        List<Schedule> list = scheduleRepository.findAll(search);
        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : list) {
            Author author = authorService.getAuthor(schedule.getAuthorId());
            dtos.add(ScheduleMapper.toDto(schedule, author));
        }
        return dtos;
    }

    @Transactional
    public void updateSchedule(Long id, ScheduleUpdateDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Schedule(id:" + id + ")가 존재하지 않습니다."));

        if(!checkPassword(dto.getPassword(), schedule.getPassword())) throw new PasswordMismatchException("비밀번호가 올바르지 않습니다.");

        // 일정 할일 수정
        if (Objects.nonNull(dto.getContent())) {
            schedule.updateContent(dto.getContent());
            scheduleRepository.update(schedule);
        }

        // 작성자 수정
        if (Objects.nonNull(dto.getAuthorName())) {
            Author author = authorService.getAuthor(schedule.getId());
            author.updateName(dto.getAuthorName());
            authorService.updateAuthorName(schedule.getAuthorId(), dto.getAuthorName());
        }
    }

    private boolean checkPassword(String inputPassword, String storedPassword) {
        return storedPassword.equals(inputPassword);
    }
}
