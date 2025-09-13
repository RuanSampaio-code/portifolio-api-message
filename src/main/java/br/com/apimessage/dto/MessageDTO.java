package br.com.apimessage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record MessageDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String message

) {
}
