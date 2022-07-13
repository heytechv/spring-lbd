package com.fisproject.springlbd.service;

import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.SprintZad11Dto;
import com.fisproject.springlbd.dto.UserStoryZad5Dto;
import com.fisproject.springlbd.entity.Attachment;
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


    private final Logger LOG = LoggerFactory.getLogger(SprintServiceImpl.class);

    // @Transactional needs UNCHECKED exception for rollback!
    @Override @Transactional                                                                                            // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring - @Transactional pozwala na rollback po jakimkolwiek runtime exception
    public void addSprint(String name, Timestamp start_date, Timestamp end_date, String description, Sprint.StatusType status) throws IllegalArgumentException {

        Sprint sprint = new Sprint();
        sprint.setName(name);
        sprint.setStartDate(start_date);
        sprint.setEndDate(end_date);
        sprint.setStatus(status);
        if (description != null) sprint.setDescription(description);

        sprintRepository.save(sprint);

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("[addSprint] Missing required 'name' field!");
        if (start_date == null || end_date == null || start_date.after(end_date))
            throw new IllegalArgumentException("[addSprint] Missing required 'start_date', 'end_date' fields!");
        if (start_date.after(end_date))
            throw new IllegalArgumentException("[addSprint] 'end_date' must be greater than 'start_date'!");
        if (!Arrays.asList(Sprint.StatusType.values()).contains(status))
            throw new IllegalArgumentException("[addSprint] Status type not found!");
    }

    @Override public List<UserStory> getUserStoryListById(Long id) {
        Optional<Sprint> foundSprint = sprintRepository.findById(id);
        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStories())).orElse(null);    // tutaj new Array bo inaczej LazyException
    }

    @Override public List<Sprint> findAll() {
        return (List<Sprint>) sprintRepository.findAll();
    }
    @Override public Optional<Sprint> findById(Long id) { return sprintRepository.findById(id); };

    @Override public List<UserStory> getUserStoryListByName(String name) {
        Optional<Sprint> foundSprint = sprintRepository.findByName(name);
        return foundSprint.map(sprint -> new ArrayList<>(sprint.getUserStories())).orElse(null);
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

    @Override public boolean addUserStory(Long id, UserStory userStory, boolean shouldSaveUserStory) {
        // userStory musi byc przedtem stworzone i zapisane!

        Optional<Sprint> optionalSprint = findById(id);
        if (optionalSprint.isEmpty()) return false;

        if (shouldSaveUserStory) userStoryRepository.save(userStory);

        optionalSprint.ifPresent(sprint -> {
            sprint.addUserStory(userStory);
            sprintRepository.save(sprint);
        });

        return true;
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
            SprintDto sprintDto = convertEntityToDto(sprint);
            if (showUserStories)
                sprintDto.setUserStoryDtos(
                        sprint.getUserStories().stream().map(userStory -> userStoryService.convertEntityToZad2Dto(userStory)).collect(Collectors.toList())
                );
            else
                sprintDto.setUserStoryDtos(new ArrayList<>());

            return sprintDto;
        }).collect(Collectors.toList()), "found");
    }


    /** (stworzone do Zad 4) */
    @Override public StandardResponse getStoryPointsAmount(Long sprintId) {
        Optional<Sprint> optionalSprint = findById(sprintId);

        if (optionalSprint.isEmpty())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return new StandardResponse(HttpStatus.BAD_REQUEST, null, "ID not found!");

        int pointSum = 0;
        Optional<Set<UserStory>> optionalUserStories = optionalSprint.map(Sprint::getUserStories);
        if (optionalUserStories.isPresent()) {
            for (UserStory us : optionalUserStories.get()) {
                if (us.getStoryPointsAmount() == null) continue;
                pointSum += us.getStoryPointsAmount();
            }
        }

        return new StandardResponse(HttpStatus.OK, pointSum, "ID found.");
    }

    /** (stworzone do Zad 5) */
    @Override public StandardResponse getUserStories(Long sprintId) {
        Optional<Sprint> optionalSprint = findById(sprintId);
        return optionalSprint
                .map(sprint -> {
                    List<UserStoryZad5Dto> userStoryZad5Dtos = sprint.getUserStories()
                            .stream().map(userStory -> userStoryService.convertEntityToZad5Dto(userStory)).collect(Collectors.toList());

                    return new StandardResponse(
                            HttpStatus.OK,
                            userStoryZad5Dtos,
                            "Found.");
                }).orElse(new StandardResponse(HttpStatus.BAD_REQUEST, null, "ID not found"));
    }

    /** (stworzone do Zad 9) */
    @Override public StandardResponse updateSprintStatus(Long sprintId, Sprint.StatusType newStatus) {
        if (!Arrays.asList(Sprint.StatusType.values()).contains(newStatus))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return new StandardResponse(HttpStatus.BAD_REQUEST, null, newStatus+" status not found!");

        return findById(sprintId).map(sprint -> {
                    sprint.setStatus(newStatus);
                    save(sprint);
                    return new StandardResponse(HttpStatus.OK, "OK", "Updated!");
                }).orElse(new StandardResponse(HttpStatus.BAD_REQUEST, "", "id not found!"));
    }

    /** (stworzone do Zad 11) */
    @Override public StandardResponse findBetweenDate(Timestamp start_range, Timestamp end_range) {

        Optional<List<Sprint>> optionalSprints = sprintRepository.getSprintListBetweenDates(start_range, end_range);

        if (optionalSprints.isEmpty())
            return new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR, "", "error!");

        ArrayList<SprintZad11Dto> arrayList = new ArrayList<>(optionalSprints.get().stream().map(this::convertEntityToZad10Dto).collect(Collectors.toList()));

        return new StandardResponse(
                HttpStatus.OK,
                arrayList,
                "ok");
    }


    /** ------------------------------------------------------------------------------------ **
    /** -- EventListener ------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    @EventListener public void handleAddStoryEvent(UserStoryCreatedEvent event) {
        System.out.println("JEST FAJNIE");

        Optional<UserStory> optionalUserStory = userStoryService.findById(event.getUserStoryId());
        if (optionalUserStory.isEmpty()) {
            LOG.error("Problem z evenetem!");
            return;
        }

        List<Sprint> sprints = (List<Sprint>) sprintRepository.findAll(Sort.by("startDate"));
        for (Sprint sprint : sprints) {
            if (sprint.getStatus() == Sprint.StatusType.PENDING) {
                addUserStory(sprint.getId(), optionalUserStory.get(), true);
                LOG.info("Dodano do Sprinta o nazwie {} z id = {}", sprint.getName(), sprint.getId());
                return;
            }
        }

        LOG.info("Nie znaleziono  Sprinta o statusie PENDING :/");

    }



    /** ------------------------------------------------------------------------------------ **
    /** -- Mapper -------------------------------------------------------------------------- **
    /** ------------------------------------------------------------------------------------ **/
    @Override public SprintDto convertEntityToDto(Sprint sprint) {
        return new SprintDto(sprint.getId(), sprint.getName(), sprint.getDescription(), sprint.getStatus());
    }

    @Override public SprintZad11Dto convertEntityToZad10Dto(Sprint sprint) {
        return new SprintZad11Dto(sprint.getId(), sprint.getName(), sprint.getStartDate(), sprint.getEndDate(), sprint.getStatus());
    }


}
