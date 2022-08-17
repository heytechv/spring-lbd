package com.fisproject.springlbd.mapper;

import com.fisproject.springlbd.dto.AttachmentDto;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UniversalMapper {

    /** Sprint <-> SprintDto */
    @Mapping(target = "id", source = "id")  // we want to map id (which is disabled? by default)
    SprintDto sprintToSprintDto(Sprint sprint);
    Sprint sprintDtoToSprint(SprintDto sprintDto);

    /** UserStory <-> UserStoryDto */
    @Named("dtoToUserStory")
    UserStory dtoToUserStory(UserStoryDto userStoryDto);

    @Named("listToListDto")
    @IterableMapping(qualifiedByName = "userStoryToDto")
    List<UserStoryDto> listToListDto(List<UserStory> userStoryList);

    @Named("userStoryToDtoWithoutAttachmentSet")
    @Mapping(target = "id", source = "id")  // we want to map id (which is disabled? by default)
    UserStoryDto userStoryToDtoWithoutAttachmentSet(UserStory userStory);

    @Named("userStoryToDto")
    default UserStoryDto userStoryToDto(UserStory userStory) {
        UserStoryDto userStoryDto = userStoryToDtoWithoutAttachmentSet(userStory);
        userStoryDto.setAttachmentDtoSet(attachmentSetToSetDto(userStory.getAttachmentSet()));
        return userStoryDto;
    }

    /** Attachment <-> AttachmentDto */
    @Named("attachmentToDto")
    AttachmentDto attachmentToDto(Attachment attachment);
    @Named("attachmentListToListDto")
    Set<AttachmentDto> attachmentSetToSetDto(Set<Attachment> attachmentSet);

}
