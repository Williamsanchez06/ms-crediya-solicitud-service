package co.com.crediya.solicitud.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "loan_applications", schema = "loan_application")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanApplicationEntity {

    @Id
    @Column("application_id")
    private UUID id;

    @Column("amount")
    private String amount;

    @Column("term")
    private Integer term;

    @Column("email")
    private String email;

    @Column("status")
    private String status;

    @Column("state_id")
    private UUID stateId;

    @Column("loan_type_id")
    private UUID loanTypeId;

}
