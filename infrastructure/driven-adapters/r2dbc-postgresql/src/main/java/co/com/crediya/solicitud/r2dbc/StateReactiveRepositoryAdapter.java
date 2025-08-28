package co.com.crediya.solicitud.r2dbc;

import co.com.crediya.solicitud.model.state.State;
import co.com.crediya.solicitud.model.state.gateways.StateRepository;
import co.com.crediya.solicitud.r2dbc.entity.StateEntity;
import co.com.crediya.solicitud.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class StateReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        State,
        StateEntity,
        UUID,
        StateReactiveRepository
        > implements  StateRepository {

    private final TransactionalOperator transactionalOperator;

    public StateReactiveRepositoryAdapter(StateReactiveRepository repository, ObjectMapper mapper, TransactionalOperator transactionalOperator) {
        super(repository, mapper, d -> mapper.map(d, State.class));
        this.transactionalOperator = transactionalOperator;
    }

    @Override
    public Mono<State> findByName(String name) {
        return repository.findByName(name)
                .map(entity -> mapper.map(entity, State.class));
    }


}