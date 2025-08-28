package co.com.crediya.solicitud.model.state;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class State {

    UUID id;
    StateName name;
    String description;

}
