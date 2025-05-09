package org.dongwon.scheduleappapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScheduleCreateDto {
    @Valid
    AuthorRequestDto author;

    @NotBlank(message = "값을 반드시 입력해주세요")
    @Size(max = 200, message = "할일 내용은 최대 200자 입니다.")
    private String content;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
