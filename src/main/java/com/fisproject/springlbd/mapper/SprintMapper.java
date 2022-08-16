package com.fisproject.springlbd.mapper;

import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.entity.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SprintMapper {
    SprintDto sprintToSprintDto(Sprint sprint);
    Sprint sprintDtoToSprint(SprintDto sprintDto);

}
