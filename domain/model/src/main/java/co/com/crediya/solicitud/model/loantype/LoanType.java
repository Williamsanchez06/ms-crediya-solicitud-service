package co.com.crediya.solicitud.model.loantype;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanType {

    UUID id;
    String name;
    BigDecimal minAmount;
    BigDecimal maxAmount;
    BigDecimal integerRate;
    Boolean autoValidate;

}
