package co.com.crediya.solicitud.api.doc;


import co.com.crediya.solicitud.api.dto.ErrorResponse;
import co.com.crediya.solicitud.api.dto.LoanApplicationCreateRequestDTO;
import co.com.crediya.solicitud.api.dto.LoanApplicationCreateResponseDTO;
import lombok.experimental.UtilityClass;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;

@UtilityClass
public class LoanApplicationDOC {

    private final String CREATED_CODE = String.valueOf(HttpStatus.OK.value());
    private final String BAD_REQUEST = HttpStatus.BAD_REQUEST.getReasonPhrase();
    private final String BAD_REQUEST_CODE = String.valueOf(HttpStatus.BAD_REQUEST.value());
    private final String INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    private final String INTERNAL_ERROR_CODE = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

    public Builder createLoanApplication(Builder builder) {
        return builder
                .operationId("createLoanApplication")
                .summary("Crear solicitud de préstamo")
                .description("Guarda una nueva solicitud de préstamo y retorna el resultado")
                .tag("Loan Application")
                .requestBody(requestBodyBuilder()
                        .required(true)
                        .description("Datos de la solicitud")
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(LoanApplicationCreateRequestDTO.class))))
                .response(responseBuilder()
                        .responseCode(CREATED_CODE)
                        .description("Solicitud creada exitosamente")
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(LoanApplicationCreateResponseDTO.class))))
                .response(responseBuilder()
                        .responseCode(BAD_REQUEST_CODE)
                        .description(BAD_REQUEST)
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(ErrorResponse.class))))
                .response(responseBuilder()
                        .responseCode(INTERNAL_ERROR_CODE)
                        .description(INTERNAL_ERROR)
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(ErrorResponse.class))));
    }

}