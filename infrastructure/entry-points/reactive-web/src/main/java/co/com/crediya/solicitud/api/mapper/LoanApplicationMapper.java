package co.com.crediya.solicitud.api.mapper;

import co.com.crediya.solicitud.api.dto.LoanApplicationCreateRequestDTO;
import co.com.crediya.solicitud.api.dto.LoanApplicationCreateResponseDTO;
import co.com.crediya.solicitud.model.loanapplication.LoanApplication;
import co.com.crediya.solicitud.model.loantype.LoanType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoanApplicationMapper {

    public static LoanApplication toDomain(LoanApplicationCreateRequestDTO dto) {
        LoanApplication loan = new LoanApplication();
        loan.setAmount(dto.amount());
        loan.setTerm(dto.term());
        loan.setEmail(dto.email());

        LoanType loanType = new LoanType();
        loanType.setId(dto.loanTypeId());
        loan.setLoanType(loanType);

        return loan;
    }

    public static LoanApplicationCreateResponseDTO toResponseDTO(LoanApplication loan) {
        return LoanApplicationCreateResponseDTO.builder()
                .id(loan.getId())
                .state(loan.getState() != null ? String.valueOf(loan.getState().getName()) : null)
                .loanTypeId(loan.getLoanType() != null ? loan.getLoanType().getId() : null)
                .amount(loan.getAmount())
                .term(loan.getTerm())
                .email(loan.getEmail())
                .build();
    }

}
