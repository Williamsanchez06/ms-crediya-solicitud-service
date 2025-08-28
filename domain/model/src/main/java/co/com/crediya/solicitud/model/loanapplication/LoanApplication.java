package co.com.crediya.solicitud.model.loanapplication;
import co.com.crediya.solicitud.model.loantype.LoanType;
import co.com.crediya.solicitud.model.state.State;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanApplication {

    UUID id;
    BigDecimal amount;
    Integer term;
    String email;
    State state;
    LoanType loanType;

}
