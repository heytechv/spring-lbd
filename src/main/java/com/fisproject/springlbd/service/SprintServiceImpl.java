package com.fisproject.springlbd.service;

import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.*;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.event.UserStoryCreatedEvent;
import com.fisproject.springlbd.repository.AttachmentRepository;
import com.fisproject.springlbd.repository.SprintRepository;
import com.fisproject.springlbd.repository.UserStoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SprintServiceImpl implements SprintService {

    @Autowired private SprintRepository sprintRepository;
    @Autowired private UserStoryService userStoryService;
    @Autowired private AttachmentRepository attachmentRepository;

    @Autowired private UserStoryRepository userStoryRepository;

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
    // @Transactional needs UNCHECKED exception for rollback!
    @Override @Transactional                                                                                            // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring - @Transactional pozwala na rollback po jakimkolwiek runtime exception
    public void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException {
        Sprint sprint = Sprint.builder()
                .name(name)
                .startDate(start_date)
                .endDate(end_date)
                .status(status)
                .build();
        if (description != null) sprint.setDescription(description);
        sprintRepository.save(sprint);

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Missing required 'name' field!");
        if (start_date == null || end_date == null || start_date.after(end_date))
            throw new IllegalArgumentException("Missing required 'start_date', 'end_date' fields!");
        if (start_date.after(end_date))
            throw new IllegalArgumentException("'end_date' must be greater than 'start_date'!");
        if (!Arrays.asList(Sprint.StatusType.values()).contains(status))
            throw new IllegalArgumentException("Status type not found!");
    }
;
    @Override public List<UserStory> getUserStoryListById(Long id) {
        Sprint sprint = findById(id);
        return new ArrayList<>(sprint.getUserStorySet());   // tutaj new Array bo inaczej LazyException
    }

    @Override public List<Sprint> findAll() {
        return (List<Sprint>) sprintRepository.findAll();
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

    @Override public void save(Sprint sprint) {
        sprintRepository.save(sprint);
    }

    @Override @Transactional public void addUserStory(Long id, UserStoryDto userStoryDto) {
        if (userStoryDto == null)
            throw new RuntimeException("internal server error. Unable to create UserStory");

        Sprint sprint = findById(id);
        // todo mapper
        UserStory userStory = UserStory.builder()
                        .name(userStoryDto.getName())
                        .description(userStoryDto.getDescription())
                        .storyPointsAmount(userStoryDto.getStoryPointsAmount())
                        .status(userStoryDto.getStatus())
                        .build();
        userStoryRepository.save(userStory);

        sprint.addUserStory(userStory);
//        sprintRepository.save(optionalSprint.get());

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
    @Override public StandardResponse getSprints(boolean showUserStories) {
        List<Sprint> sprints = findAll();

        return new StandardResponse(HttpStatus.OK, sprints.stream().map(sprint -> {
            SprintUltimateDto sprintDto = convertEntityToDto(sprint);
            if (showUserStories)
                sprintDto.setUserStories(
                        sprint.getUserStorySet().stream().map(userStory ->
                                userStoryService.convertEntityToZad2Dto(userStory)).collect(Collectors.toList())
                );
//            else
//                sprintDto.setU(new ArrayList<>());

            return sprintDto;
        }).collect(Collectors.toList()), "found");
    }


    /** (stworzone do Zad 4) */
    @Override public Integer getStoryPointsAmount(Long sprintId) {
        Sprint sprint = findById(sprintId);

        int pointSum = 0;
        Set<UserStory> userStories = sprint.getUserStorySet();
        for (UserStory us : userStories) {
            if (us.getStoryPointsAmount() == null) continue;
            pointSum += us.getStoryPointsAmount();
        }


        return pointSum;
    }

    /** (stworzone do Zad 5) */
    @Override public List<UserStoryDto> getUserStories(Long sprintId) {
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
    @Override public void updateSprintStatus(Long id, Sprint.StatusType newStatus) {
        if (!Arrays.asList(Sprint.StatusType.values()).contains(newStatus))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//            return new StandardResponse(HttpStatus.BAD_REQUEST, null, newStatus+" status not found!");

//        return findById(sprintId).map(sprint -> {
//                    sprint.setStatus(newStatus);
//                    save(sprint);
//                    return new StandardResponse(HttpStatus.OK, "OK", "updated");
//                }).orElse(new StandardResponse(HttpStatus.BAD_REQUEST, "", "id not found!"));

        findById(id).setStatus(newStatus);
    }

    /** (stworzone do Zad 11) */
    @Override public StandardResponse findBetweenDate(Timestamp start_range, Timestamp end_range) {

        Optional<List<Sprint>> optionalSprints = sprintRepository.getSprintListBetweenDates(start_range, end_range);

        if (optionalSprints.isEmpty())
            return new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR, "", "error!");

        ArrayList<SprintUltimateDto> arrayList = new ArrayList<>(optionalSprints.get().stream().map(this::convertEntityToZad10Dto).collect(Collectors.toList()));

//        return new StandardResponse(HttpStatus.OK, arrayList, "ok");
        return new StandardResponse(HttpStatus.OK, arrayList, "ok");
    }


    /** ------------------------------------------------------------------------------------ **
    /** -- EventListener ------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    @EventListener public void handleAddStoryEvent(UserStoryCreatedEvent event) {
        System.out.println("JEST FAJNIE");

        Optional<UserStory> optionalUserStory = userStoryService.findById(event.getUserStoryId());
        if (optionalUserStory.isEmpty()) {
            log.error("Problem z evenetem!");
            return;
        }

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



    /** ------------------------------------------------------------------------------------ **
    /** -- Mapper -------------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    @Override public SprintUltimateDto convertEntityToDto(Sprint sprint) {

        SprintUltimateDto sprintUltimateDto = new SprintUltimateDto();
        sprintUltimateDto.setId(sprint.getId());
        sprintUltimateDto.setName(sprint.getName());
        sprintUltimateDto.setDescription(sprint.getDescription());
        sprintUltimateDto.setStatus(sprint.getStatus());

        return sprintUltimateDto;

//        return new SprintDto(sprint.getId(), sprint.getName(), sprint.getDescription(), sprint.getStatus());
    }

    @Override public SprintUltimateDto convertEntityToZad10Dto(Sprint sprint) {

        SprintUltimateDto sprintUltimateDto = new SprintUltimateDto();
        sprintUltimateDto.setId(sprint.getId());
        sprintUltimateDto.setName(sprint.getName());
        sprintUltimateDto.setStartDate(sprint.getStartDate());
        sprintUltimateDto.setEndDate(sprint.getEndDate());
        sprintUltimateDto.setStatus(sprint.getStatus());

        return sprintUltimateDto;


//        return new SprintZad11Dto(sprint.getId(), sprint.getName(), sprint.getStartDate(), sprint.getEndDate(), sprint.getStatus());
    }


}
