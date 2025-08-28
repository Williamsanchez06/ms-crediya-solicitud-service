package co.com.crediya.solicitud.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "states", schema = "loan_application")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StateEntity {

    @Id
    @Column("state_id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

}
