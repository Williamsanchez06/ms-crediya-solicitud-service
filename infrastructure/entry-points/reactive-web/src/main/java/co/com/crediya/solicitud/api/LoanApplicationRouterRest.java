package co.com.crediya.solicitud.api;

import co.com.crediya.solicitud.api.config.LoanApplicationPath;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class LoanApplicationRouterRest {

    private final LoanApplicationPath loanApplicationPath;
    private final LoanApplicationHandler loanApplicationHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(LoanApplicationHandler loanApplicationHandler) {
        return route(POST(loanApplicationPath.getLoanApplications()), this.loanApplicationHandler::saveLoanApplication);
    }
}
