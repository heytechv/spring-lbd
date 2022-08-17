package com.fisproject.springlbd.mapper;

import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserStoryMapper {

    @Mapping(target = "id", source = "id")  // we want to map id (which is disabled? by default)
    UserStoryDto userStoryToDto(UserStory userStory);
    UserStory dtoToUserStory(UserStoryDto userStoryDto);

}
