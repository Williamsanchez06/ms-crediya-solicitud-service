package co.com.crediya.solicitud.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record LoanApplicationCreateRequestDTO(
        @NotNull @Positive BigDecimal amount,
        @NotNull @Positive Integer term,
        @NotBlank @Email String email,
        @NotNull UUID loanTypeId
) {}
