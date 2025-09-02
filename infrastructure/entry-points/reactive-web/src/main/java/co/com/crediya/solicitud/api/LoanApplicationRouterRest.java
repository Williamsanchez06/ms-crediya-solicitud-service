package co.com.crediya.solicitud.api;

import co.com.crediya.solicitud.api.config.LoanApplicationPath;
import co.com.crediya.solicitud.api.doc.LoanApplicationDOC;
import lombok.RequiredArgsConstructor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
@RequiredArgsConstructor
public class LoanApplicationRouterRest {

    private final LoanApplicationPath loanApplicationPath;
    private final LoanApplicationHandler loanApplicationHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(LoanApplicationHandler handler) {

        return route()
                .POST(loanApplicationPath.getLoanApplications(), loanApplicationHandler::saveLoanApplication, LoanApplicationDOC::createLoanApplication)
                .build();

    }

}
