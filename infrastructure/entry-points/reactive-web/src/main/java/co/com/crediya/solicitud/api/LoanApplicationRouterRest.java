package co.com.crediya.solicitud.api;

import co.com.crediya.solicitud.api.config.LoanApplicationPath;
import co.com.crediya.solicitud.api.dto.ErrorResponse;
import co.com.crediya.solicitud.api.dto.LoanApplicationCreateRequestDTO;
import co.com.crediya.solicitud.api.dto.LoanApplicationCreateResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class LoanApplicationRouterRest {

    private final LoanApplicationPath loanApplicationPath;
    private final LoanApplicationHandler loanApplicationHandler;

    @Bean
    @RouterOperation(
            path = "/api/v1/application",
            produces = "application/json",
            method = RequestMethod.POST,
            beanClass = LoanApplicationHandler.class,
            beanMethod = "saveLoanApplication",
            operation = @Operation(
                    operationId = "createLoanApplication",
                    summary = "Crear solicitud de préstamo",
                    description = "Guarda una nueva solicitud de préstamo y retorna el resultado",
                    tags = {"Loan Application"},
                    requestBody = @RequestBody(
                            required = true,
                            description = "Datos de la solicitud",
                            content = @Content(schema = @Schema(implementation = LoanApplicationCreateRequestDTO.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Solicitud creada exitosamente",
                                    content = @Content(schema = @Schema(implementation = LoanApplicationCreateResponseDTO.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Error de validación",
                                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                            ),
                            @ApiResponse(
                                    responseCode = "500",
                                    description = "Error inesperado del servidor",
                                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> routerFunction(LoanApplicationHandler loanApplicationHandler) {
        return route(POST(loanApplicationPath.getLoanApplications()), this.loanApplicationHandler::saveLoanApplication);
    }
}
