package io.takima.springdatabbl.model.projection;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class BarmanProjection {
    private String name;
    private String cocktailName;
}
