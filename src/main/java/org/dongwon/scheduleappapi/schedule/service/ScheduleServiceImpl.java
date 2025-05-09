package org.dongwon.scheduleappapi.schedule.service;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.author.repository.AuthorService;
import org.dongwon.scheduleappapi.common.exception.PasswordMismatchException;
import org.dongwon.scheduleappapi.common.pagination.Page;
import org.dongwon.scheduleappapi.dto.*;
import org.dongwon.scheduleappapi.entity.Author;
import org.dongwon.scheduleappapi.entity.Schedule;
import org.dongwon.scheduleappapi.mapper.ScheduleMapper;
import org.dongwon.scheduleappapi.schedule.repository.ScheduleRepository;
import org.mindrot.jbcrypt.BCrypt;
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
        // author 엔티티 저장
        Long authorId = authorService.createAuthor(dto.getAuthor());
        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        dto.setPassword(hashedPassword);
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

    public Page<ScheduleResponseDto> getSchedules(ScheduleSearch search, int page, int size) {
        int offset = (page-1) * size;
        List<Schedule> list = scheduleRepository.findAll(search, offset, size);
        long totalCount = scheduleRepository.countSchedules(search);
        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : list) {
            Author author = authorService.getAuthor(schedule.getAuthorId());
            dtos.add(ScheduleMapper.toDto(schedule, author));
        }
        return Page.of(dtos,page,size,totalCount);
    }

    @Transactional
    public void updateSchedule(Long id, ScheduleUpdateDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Schedule(id:" + id + ")가 존재하지 않습니다."));

        checkPassword(dto.getPassword(), schedule.getPassword());

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
    @Transactional
    public void removeSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Schedule(id:" + id + ")가 존재하지 않습니다."));

        //패스워드 비교
        checkPassword(password, schedule.getPassword());

        scheduleRepository.deleteById(schedule.getId());
        authorService.removeAuthor(schedule.getAuthorId());
    }

    private void checkPassword(String inputPassword, String storedPassword) {
        if (!BCrypt.checkpw(inputPassword, storedPassword)) throw new PasswordMismatchException("비밀번호가 올바르지 않습니다.");
    }
}
