package org.dongwon.scheduleappapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long authorId;

    private Schedule(String content, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Long authorId) {
        this(null, content, password, createdAt, updatedAt, authorId);
    }

    private Schedule(Long id, String content, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Long authorId) {
        this.id = id;
        this.content = content;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
    }
    /*
    * DB에 저장하기 전, 새로운 Schedule 객체를 생성
    * createdAt과 updatedAt은 호출 시점의 현재 시간으로 설정되며, id는 할당되지 않습니다.
    * */
    public static Schedule createSchedule(String content, String password, Long authorId) {
        return new Schedule(content, password, LocalDateTime.now(), LocalDateTime.now(), authorId);
    }
    /*
    * DB에서 조회된 데이터로 Schedule 객체를 재구성
    * */
    public static Schedule createSchedule(Long id, String content, String password, Long authorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Schedule(id, content, password, createdAt, updatedAt, authorId);
    }
}
