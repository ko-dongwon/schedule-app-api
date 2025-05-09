package org.dongwon.scheduleappapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordRequest {
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
