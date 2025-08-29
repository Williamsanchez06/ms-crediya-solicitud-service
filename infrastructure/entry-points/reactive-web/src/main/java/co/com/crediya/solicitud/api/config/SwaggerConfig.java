package co.com.crediya.solicitud.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final LoanApplicationPath loanApplicationPath;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CrediYa Solicitud API")
                        .version("1.0.0")
                        .description("Documentación de los endpoints del microservicio de solicitud de crédito"));
    }

    @Bean
    public GroupedOpenApi loanApplicationApi() {
        return GroupedOpenApi.builder()
                .group("loanApplications")
                .pathsToMatch(loanApplicationPath.getLoanApplications() + "/**")
                .build();
    }

}
