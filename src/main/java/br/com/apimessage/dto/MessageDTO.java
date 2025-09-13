package br.com.apimessage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record MessageDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String message,
        LocalDateTime createdAt

) {
}
