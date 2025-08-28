package co.com.crediya.solicitud.r2dbc;

import co.com.crediya.solicitud.model.loantype.LoanType;
import co.com.crediya.solicitud.model.loantype.gateways.LoanTypeRepository;
import co.com.crediya.solicitud.r2dbc.entity.LoanTypeEntity;
import co.com.crediya.solicitud.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class LoanTypeReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        LoanType,
        LoanTypeEntity,
        UUID,
        LoanTypeReactiveRepository
        > implements LoanTypeRepository {

    private final TransactionalOperator transactionalOperator;

    public LoanTypeReactiveRepositoryAdapter(LoanTypeReactiveRepository repository, ObjectMapper mapper, TransactionalOperator transactionalOperator) {
        super(repository, mapper, d -> mapper.map(d, LoanType.class));
        this.transactionalOperator = transactionalOperator;
    }

    @Override
    public Mono<LoanType> findById(UUID id) {
        return super.findById(id);
    }


}
