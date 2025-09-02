package co.com.crediya.solicitud.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record LoanApplicationCreateResponseDTO(

        @JsonProperty("id")
        UUID id,
        @JsonProperty("state")
        String state,
        @JsonProperty("loan_type_id")
        UUID loanTypeId,
        @JsonProperty("amount")
        BigDecimal amount,
        @JsonProperty("term")
        Integer term,
        @JsonProperty("email")
        String email

) {}
