package co.com.crediya.solicitud.r2dbc;

import co.com.crediya.solicitud.r2dbc.entity.StateEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StateReactiveRepository extends ReactiveCrudRepository<StateEntity, UUID>, ReactiveQueryByExampleExecutor<StateEntity> {

    Mono<StateEntity> findByName(String name);

}