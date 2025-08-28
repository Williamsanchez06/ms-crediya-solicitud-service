package co.com.crediya.solicitud.api;

import co.com.crediya.solicitud.api.dto.LoanApplicationCreateRequestDTO;
import co.com.crediya.solicitud.api.exception.ValidationException;
import co.com.crediya.solicitud.api.mapper.LoanApplicationMapper;
import co.com.crediya.solicitud.api.validation.RequestValidator;
import co.com.crediya.solicitud.usecase.loanapplication.LoanApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoanApplicationHandler {

    private final RequestValidator requestValidator;
    private final LoanApplicationUseCase loanApplicationUseCase;

    public Mono<ServerResponse> saveLoanApplication(ServerRequest request) {

        return request.bodyToMono(LoanApplicationCreateRequestDTO.class)
                .flatMap(dto -> {
                    var errors = requestValidator.validate(dto);
                    return errors.isEmpty()
                            ? Mono.just(dto)
                            : Mono.error(new ValidationException(errors.toString()));
                })
                .map(LoanApplicationMapper::toDomain)
                .flatMap(loanApplicationUseCase::saveLoanApplication)
                .map(LoanApplicationMapper::toResponseDTO)
                .flatMap(resp -> ServerResponse
                        .created(request.uri()) // o Location "/loan-applications/{id}"
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

}
