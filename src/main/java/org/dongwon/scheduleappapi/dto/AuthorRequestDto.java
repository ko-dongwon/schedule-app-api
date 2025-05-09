package org.dongwon.scheduleappapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorRequestDto {
    @NotBlank(message = "값을 반드시 입력해주세요")
    @NotNull(message = "author도 입력하세요")
    private String authorName;
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}
