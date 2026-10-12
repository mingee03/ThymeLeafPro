package org.example.assign06.dto;

public record StudentResponse(
        Long id,
        String name,
        Integer age,
        String email,
        String major,
        String studentId) {
}