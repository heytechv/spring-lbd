package com.fisproject.springlbd.service;

import com.fisproject.springlbd.dto.*;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.event.UserStoryCreatedEvent;
import com.fisproject.springlbd.mapper.SprintMapper;
import com.fisproject.springlbd.mapper.UserStoryMapper;
import com.fisproject.springlbd.repository.AttachmentRepository;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SprintServiceImpl implements SprintService {

    private SprintMapper sprintMapper;
    private UserStoryMapper userStoryMapper;
    private SprintRepository sprintRepository;
    private UserStoryService userStoryService;
    private AttachmentRepository attachmentRepository;

    private UserStoryRepository userStoryRepository;

    private final Logger log = LoggerFactory.getLogger(SprintServiceImpl.class);


    /**
     * Private Utilities
     * */
    private Sprint findById(Long id) {
        return sprintRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sprint with id="+id+" not found!"));
    }

    /**
     * Public
     * */
    @Override @Transactional public void add(SprintDto sprintDto) {
        if (sprintDto == null)
            throw new RuntimeException("Sprint cannot be null!");
        sprintRepository.save(sprintMapper.sprintDtoToSprint(sprintDto));
    }

    @Override public List<Sprint> getAll() {
        return (List<Sprint>) sprintRepository.findAll();
    }

    @Override public List<UserStory> getUserStoryListById(Long id) {
        Sprint sprint = findById(id);
        return new ArrayList<>(sprint.getUserStorySet());   // tutaj new Array bo inaczej LazyException
    }

    @Override public List<UserStory> getUserStoryListByName(String name) {
        Optional<Sprint> foundSprint = sprintRepository.findByName(name);
        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStorySet())).orElse(null);
    }

    @Override public Integer getStoryPointsById(Long id) {
        Integer points = sprintRepository.getStoryPointsById(id);
        return points != null ? points : 0;
    }

    @Override public Page<Sprint> findAllPageAndSortByDate(Integer page, Integer size) {
        // https://www.baeldung.com/spring-data-jpa-pagination-sorting
        return sprintRepository.findAll(
                PageRequest.of(page, size,
                        Sort.by("startDate")));
    }

    @Override @Transactional public void addUserStory(Long id, UserStoryDto userStoryDto) {
        if (userStoryDto == null)
            throw new RuntimeException("internal server error. Unable to create UserStory");
        // find Sprint by id
        Sprint sprint = findById(id);
        // save UserStory
        UserStory userStory = userStoryMapper.dtoToUserStory(userStoryDto);
        userStoryRepository.save(userStory);
        // add UserStory to Sprint (and save automatically @Transactional)
        sprint.addUserStory(userStory);
    }


    @Override @Transactional public void addSprintWithUserStoryZad16(String sprintName) {

        UserStory userStory = new UserStory();
        userStory.setName("user_story_zad16");
        userStory.setDescription("opis user story (zad 16)");
        userStory.setStoryPointsAmount(12);
        userStory.setStatus(UserStory.StatusType.IN_PROGRESS);
        userStoryRepository.save(userStory);

        Sprint sprint = new Sprint();
        sprint.setName(sprintName);
        sprint.setStartDate(Timestamp.valueOf("2022-07-07 00:00:00.0"));
        sprint.setEndDate(Timestamp.valueOf("2022-07-08 00:00:00.0"));
        sprint.setDescription("sprint na potrzeby zad 16 :)");
        sprint.setStatus(Sprint.StatusType.PENDING);
        sprint.addUserStory(userStory);
        sprintRepository.save(sprint);
    }


    /** ------------------------------------------------------------------------------------ **
    /** -- Day3 - web responses ------------------------------------------------------------ **
    /** ------------------------------------------------------------------------------------ **/


    /** (stworzone do Zad 2) */
    @Override public List<SprintDto> getSprints(boolean showUserStories) {
        List<Sprint> sprints = getAll();
        return sprints.stream().map(sprint -> {
            SprintDto sprintDto = sprintMapper.sprintToSprintDto(sprint);
            if (showUserStories)
                sprintDto.setUserStories(
                        sprint.getUserStorySet().stream().map(userStory ->
                                userStoryMapper.userStoryToDto(userStory)).collect(Collectors.toList())
                );
            return sprintDto;
        }).collect(Collectors.toList());
    }


    /** (stworzone do Zad 4) */
    @Override public Integer getStoryPointsAmount(Long sprintId) {
        Sprint sprint = findById(sprintId);

        int pointSum = 0;
        Set<UserStory> userStories = sprint.getUserStorySet();
        for (UserStory us : userStories) {
            if (us.getStoryPointsAmount() != null)
                pointSum += us.getStoryPointsAmount();
        }

        return pointSum;
    }

    /** (stworzone do Zad 5) */
    @Override public List<UserStoryDto> getUserStoryList(Long sprintId) {
        Sprint sprint = findById(sprintId);
//        return optionalSprint
//                .map(sprint -> {
//                    List<UserStoryUltimateDto> userStoryZad5Dtos = sprint.getUserStorySet()
//                            .stream().map(userStory -> userStoryService.convertEntityToZad5Dto(userStory)).collect(Collectors.toList());
//
//                    return new StandardResponse(HttpStatus.OK, userStoryZad5Dtos, "found.");
//                }).orElse(new StandardResponse(HttpStatus.BAD_REQUEST, null, "id not found"));

        return sprint.getUserStorySet()
                .stream().map(userStory -> userStoryService.convertEntityToZad5Dto(userStory)).collect(Collectors.toList());
    }

    /** (stworzone do Zad 9) */
    @Override @Transactional public void updateSprintStatus(Long id, Sprint.StatusType newStatus) {
        if (!Arrays.asList(Sprint.StatusType.values()).contains(newStatus))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        findById(id).setStatus(newStatus);
    }

    /** (stworzone do Zad 11) */
    @Override public ArrayList<SprintDto> getBetweenDate(Timestamp start_range, Timestamp end_range) {
        Optional<List<Sprint>> optionalSprints = sprintRepository.getSprintListBetweenDates(start_range, end_range);
        if (optionalSprints.isEmpty())
            throw new EntityNotFoundException("Sprints not found in this date range!");

        return new ArrayList<>(optionalSprints.get().stream().map(sprint ->
                sprintMapper.sprintToSprintDto(sprint)).collect(Collectors.toList()));
    }


    /** ------------------------------------------------------------------------------------ **
    /** -- EventListener ------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    @EventListener public void handleAddStoryEvent(UserStoryCreatedEvent event) {
        System.out.println("JEST FAJNIE");

//        Optional<UserStory> optionalUserStory = userStoryService.findById(event.getUserStoryId());
//        if (optionalUserStory.isEmpty()) {
//            log.error("Problem z evenetem!");
//            return;
//        }

//        List<Sprint> sprints = (List<Sprint>) sprintRepository.findAll(Sort.by("startDate"));
//        for (Sprint sprint : sprints) {
//            if (sprint.getStatus() == Sprint.StatusType.PENDING) {
//                addUserStory(sprint.getId(), optionalUserStory.get(), true);
//                log.info("Dodano do Sprinta o nazwie {} z id = {}", sprint.getName(), sprint.getId());
//                return;
//            }
//        }

        log.info("Nie znaleziono  Sprinta o statusie PENDING :/");

    }




}
