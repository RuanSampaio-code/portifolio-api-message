package br.com.apimessage.dto;

import java.time.LocalDateTime;

public record MessageResponseDTO(
        String id,
        String name,
        String email,
        String message,
        LocalDateTime createdAt
) {
}
