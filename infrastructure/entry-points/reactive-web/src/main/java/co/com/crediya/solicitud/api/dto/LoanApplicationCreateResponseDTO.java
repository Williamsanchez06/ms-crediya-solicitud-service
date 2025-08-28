package co.com.crediya.solicitud.api.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record LoanApplicationCreateResponseDTO(
        UUID id,
        String state,
        UUID loanTypeId,
        BigDecimal amount,
        Integer term,
        String email,
        String applicantId
) {}
