package co.com.crediya.solicitud.model.state.gateways;

import co.com.crediya.solicitud.model.state.State;
import reactor.core.publisher.Mono;

public interface StateRepository {

    Mono<State> findByName(String name);

}
