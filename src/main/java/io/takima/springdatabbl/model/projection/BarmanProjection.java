package io.takima.springdatabbl.model.projection;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BarmanProjection {
    @EqualsAndHashCode.Include
    private String name;
    private List<String> cocktailNames;
}
