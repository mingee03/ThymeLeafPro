package org.example.assign06.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record StudentRequest(
        @NotBlank(message = "이름을 입력하시오") String name,
        @Min(value = 0, message = "연령 입력 오류") Integer age,
        @NotBlank(message = "Email 입력 오류") String email,
        @NotBlank(message = "전공 입력 오류") String major,
        @NotBlank(message = "학번 입력 오류") String studentId) {
}
