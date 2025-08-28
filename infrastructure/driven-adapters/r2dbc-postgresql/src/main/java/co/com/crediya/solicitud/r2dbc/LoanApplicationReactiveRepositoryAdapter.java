package co.com.crediya.solicitud.r2dbc;

import co.com.crediya.solicitud.model.loanapplication.LoanApplication;
import co.com.crediya.solicitud.model.loanapplication.gateways.LoanApplicationRepository;
import co.com.crediya.solicitud.r2dbc.entity.LoanApplicationEntity;
import co.com.crediya.solicitud.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class LoanApplicationReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        LoanApplication,
        LoanApplicationEntity,
        UUID,
        LoanApplicationReactiveRepository
        > implements LoanApplicationRepository {

    private final TransactionalOperator transactionalOperator;

    public LoanApplicationReactiveRepositoryAdapter(LoanApplicationReactiveRepository repository, ObjectMapper mapper, TransactionalOperator transactionalOperator) {
        super(repository, mapper, d -> mapper.map(d, LoanApplication.class));
        this.transactionalOperator = transactionalOperator;
    }

    @Override
    public Mono<LoanApplication> save(LoanApplication loanApplication) {
        LoanApplicationEntity entity = mapper.map(loanApplication, LoanApplicationEntity.class);

        entity.setLoanTypeId(loanApplication.getLoanType().getId());
        entity.setStateId(loanApplication.getState().getId());

        return repository.save(entity)
                .map(savedEntity -> mapper.map(savedEntity, LoanApplication.class))
                .as(transactionalOperator::transactional);

    }

}
