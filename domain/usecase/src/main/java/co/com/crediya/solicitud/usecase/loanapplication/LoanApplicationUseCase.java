package co.com.crediya.solicitud.usecase.loanapplication;

import co.com.crediya.solicitud.model.exception.BusinessException;
import co.com.crediya.solicitud.model.loanapplication.LoanApplication;
import co.com.crediya.solicitud.model.loanapplication.gateways.LoanApplicationRepository;
import co.com.crediya.solicitud.model.loantype.gateways.LoanTypeRepository;
import co.com.crediya.solicitud.model.state.StateName;
import co.com.crediya.solicitud.model.state.gateways.StateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoanApplicationUseCase {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final StateRepository stateRepository;

    public Mono<LoanApplication> saveLoanApplication(LoanApplication loanProcess) {
        return loanTypeRepository.findById(loanProcess.getLoanType().getId())
                .switchIfEmpty(Mono.error(new BusinessException("No existe el tipo de credito seleccionado")))
                .flatMap(loanType -> {
                    String initialState = String.valueOf(loanType.getAutoValidate() ? StateName.APROBADO : StateName.PENDIENTE);
                    return stateRepository.findByName(initialState)
                            .switchIfEmpty(Mono.error(new BusinessException("No existe el estado: " + initialState)))
                            .flatMap(state -> {
                                loanProcess.setState(state);
                                return loanApplicationRepository.save(loanProcess);
                            });

                });
    }

}
