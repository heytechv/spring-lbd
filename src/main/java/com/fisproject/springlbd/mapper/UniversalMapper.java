package com.fisproject.springlbd.mapper;

import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UniversalMapper {

    /** Sprint <-> SprintDto */

}
