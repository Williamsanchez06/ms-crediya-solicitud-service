package co.com.crediya.solicitud.api.exception;

import co.com.crediya.solicitud.api.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


public class GlobalErrorResponseBuilder {

    private final ServerWebExchange exchange;
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String code = "INTERNAL_ERROR";
    private String message = "Ha ocurrido un error inesperado.";

    private GlobalErrorResponseBuilder(ServerWebExchange exchange) {
        this.exchange = exchange;
    }

    public static GlobalErrorResponseBuilder with(ServerWebExchange exchange) {
        return new GlobalErrorResponseBuilder(exchange);
    }

    public GlobalErrorResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public GlobalErrorResponseBuilder code(String code) {
        this.code = code;
        return this;
    }

    public GlobalErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public Mono<Void> build() {
        ErrorResponse error = new ErrorResponse(code, message);
        byte[] bytes = ("{\"code\":\"" + error.code() + "\",\"message\":\"" + error.message() + "\"}")
                .getBytes(StandardCharsets.UTF_8);

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }

}
