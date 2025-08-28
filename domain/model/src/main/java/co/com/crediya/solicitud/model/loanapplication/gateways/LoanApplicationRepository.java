package co.com.crediya.solicitud.model.loanapplication.gateways;

import co.com.crediya.solicitud.model.loanapplication.LoanApplication;
import reactor.core.publisher.Mono;

public interface LoanApplicationRepository {

    Mono<LoanApplication> save(LoanApplication loanApplication);

}
