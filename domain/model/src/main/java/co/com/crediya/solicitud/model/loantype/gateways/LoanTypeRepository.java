package co.com.crediya.solicitud.model.loantype.gateways;

import co.com.crediya.solicitud.model.loantype.LoanType;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LoanTypeRepository {

    Mono<LoanType> findById(UUID id);

}
