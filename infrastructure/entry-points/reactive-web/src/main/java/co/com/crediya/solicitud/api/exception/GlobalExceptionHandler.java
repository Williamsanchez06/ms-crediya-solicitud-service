package co.com.crediya.solicitud.api.exception;

import co.com.crediya.solicitud.model.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("[GlobalWebExceptionHandler] Error capturado", ex);

        HttpStatus status;
        String code;
        String message;

        switch (ex) {
            case ServerWebInputException inputEx -> {
                status = HttpStatus.BAD_REQUEST;
                code = "INVALID_REQUEST_BODY";
                message = "El cuerpo del request es inválido o está mal formado.";
            }
            case NotFoundException notFound -> {
                status = HttpStatus.NOT_FOUND;
                code = "NOT_FOUND";
                message = notFound.getMessage();
            }
            case BusinessException business -> {
                status = HttpStatus.BAD_REQUEST;
                code = "BUSINESS_ERROR";
                message = business.getMessage();
            }
            case ValidationException validation -> {
                status = HttpStatus.BAD_REQUEST;
                code = "VALIDATION_ERROR";
                message = validation.getMessage();
            }
            default -> {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                code = "INTERNAL_ERROR";
                message = "Ha ocurrido un error inesperado.";
            }
        }

        return GlobalErrorResponseBuilder
                .with(exchange)
                .status(status)
                .code(code)
                .message(message)
                .build();
    }
}
