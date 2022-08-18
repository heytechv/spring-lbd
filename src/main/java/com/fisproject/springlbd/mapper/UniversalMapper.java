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
    SprintDto sprintToDto(Sprint sprint);

    @Named("sprintDtoToSprint")
    Sprint sprintDtoToSprint(SprintDto sprintDto);

    @Named("sprintDtoListToList")
    @IterableMapping(qualifiedByName = "sprintDtoToSprint")
    List<Sprint> sprintDtoListToList(List<SprintDto> sprintDtoList);

    /** UserStory <-> UserStoryDto */
    @Named("userStoryDtoToUserStory")
    UserStory userStoryDtoToUserStory(UserStoryDto userStoryDto);

    @Named("userStoryListToListDto")
    @IterableMapping(qualifiedByName = "userStoryToDto")
    List<UserStoryDto> userStoryListToListDto(List<UserStory> userStoryList);

    @Named("userStoryDtoListToList")
    @IterableMapping(qualifiedByName = "userStoryDtoToUserStory")
    List<UserStory> userStoryDtoListToList(List<UserStoryDto> userStoryDtoList);

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
