package io.takima.springdatabbl.mapper;

import io.takima.springdatabbl.model.Barman;
import io.takima.springdatabbl.model.projection.IBarmanProjection;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BarmanMapper {

    Barman toBarman(IBarmanProjection barmanProjection);
}
