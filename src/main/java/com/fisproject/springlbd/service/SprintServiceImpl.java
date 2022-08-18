package com.fisproject.springlbd.service;

import com.fisproject.springlbd.dto.*;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.event.UserStoryCreatedEvent;
import com.fisproject.springlbd.mapper.UniversalMapper;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class SprintServiceImpl implements SprintService {

    private UniversalMapper universalMapper;
    private SprintRepository sprintRepository;
    private UserStoryService userStoryService;
    private AttachmentRepository attachmentRepository;

    private UserStoryRepository userStoryRepository;

    private final Logger log = LoggerFactory.getLogger(SprintServiceImpl.class);


    /**
     * Private (utils)
     * */
    private Sprint findById(Long id) {
        return sprintRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sprint with id="+id+" not found!"));
    }

    private List<Sprint> findAll() {
        return (List<Sprint>) sprintRepository.findAll();
    }

    /**
     * Public
     * */
    @Override @Transactional public void add(@Valid SprintDto sprintDto) {
        if (sprintDto == null)
            throw new RuntimeException("Sprint cannot be null!");
        sprintRepository.save(universalMapper.sprintDtoToSprint(sprintDto));
    }

    @Override @Transactional public void addAll(List<@Valid SprintDto> sprintDtoList) {
        if (sprintDtoList == null)
            throw new RuntimeException("SprintList cannot be null!");
        sprintRepository.saveAll(universalMapper.sprintDtoListToList(sprintDtoList));
    }


    @Override public List<SprintDto> getAll(boolean showUserStories) {
        /* (stworzone do Zad 2) */
        List<Sprint> sprints = findAll();
        return sprints.stream().map(sprint -> {
            SprintDto sprintDto = universalMapper.sprintToDto(sprint);
            if (showUserStories)
                sprintDto.setUserStoryList(universalMapper.userStoryListToListDto(new ArrayList<>(sprint.getUserStorySet())));
            return sprintDto;
        }).collect(Collectors.toList());
    }

    @Override public SprintDto getById(Long id, boolean showUserStories) {
        Sprint sprint = findById(id);

        SprintDto sprintDto = universalMapper.sprintToDto(sprint);
        if (showUserStories)
            sprintDto.setUserStoryList(universalMapper.userStoryListToListDto(new ArrayList<>(sprint.getUserStorySet())));
        return sprintDto;
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

    @Override public Page<Sprint> getAllPageAndSortByDate(Integer page, Integer size) {
        // https://www.baeldung.com/spring-data-jpa-pagination-sorting
        return sprintRepository.findAll(
                PageRequest.of(page, size,
                        Sort.by("startDate")));
    }

    @Override @Transactional public UserStory addUserStory(Long id, UserStoryDto userStoryDto) {
        if (userStoryDto == null)
            throw new RuntimeException("internal server error. Unable to create UserStory");
        // find Sprint by id
        Sprint sprint = findById(id);
        // save UserStory
        UserStory userStory = universalMapper.userStoryDtoToUserStory(userStoryDto);
        userStoryRepository.save(userStory);
        // add UserStory to Sprint (and save automatically @Transactional)
        sprint.addUserStory(userStory);

        return userStory;
    }

    @Override @Transactional public void addSprintWithUserStoryZad16(String sprintName) {
        /* Zad 16 */
        UserStory userStory = UserStory.builder()
                .name("user_story_zad16")
                .description("opis user story (zad 16)")
                .storyPointsAmount(12)
                .status(UserStory.StatusType.IN_PROGRESS)
                .build();
        userStoryRepository.save(userStory);

        Sprint sprint = Sprint.builder()
                .name(sprintName)
                .startDate(Timestamp.valueOf("2022-07-07 00:00:00.0"))
                .endDate(Timestamp.valueOf("2022-07-08 00:00:00.0"))
                .description("sprint na potrzeby zad 16 :)")
                .status(Sprint.StatusType.PENDING)
                .build();
        sprint.addUserStory(userStory);
        sprintRepository.save(sprint);
    }

    @Override public Integer getStoryPointsAmount(Long sprintId) {
        /* Zad 4 */
        Sprint sprint = findById(sprintId);

        int pointSum = 0;
        Set<UserStory> userStories = sprint.getUserStorySet();
        for (UserStory us : userStories) {
            if (us.getStoryPointsAmount() != null)
                pointSum += us.getStoryPointsAmount();
        }

        return pointSum;
    }

    @Override public List<UserStoryDto> getUserStoryList(Long sprintId) {
        /* Zad 5 */
        Sprint sprint = findById(sprintId);
        return sprint.getUserStorySet()
                .stream().map(userStory -> universalMapper.userStoryToDto(userStory)).collect(Collectors.toList());
    }

    @Override @Transactional public void updateSprintStatus(Long id, Sprint.StatusType newStatus) {
        /* Zad 9 */
        if (!Arrays.asList(Sprint.StatusType.values()).contains(newStatus))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        findById(id).setStatus(newStatus);
    }

    @Override public ArrayList<SprintDto> getBetweenDate(Timestamp start_range, Timestamp end_range) {
        /* Zad 11 */
        Optional<List<Sprint>> optionalSprints = sprintRepository.getSprintListBetweenDates(start_range, end_range);
        if (optionalSprints.isEmpty())
            throw new EntityNotFoundException("Sprints not found in this date range!");

        return new ArrayList<>(optionalSprints.get().stream().map(sprint ->
                universalMapper.sprintToDto(sprint)).collect(Collectors.toList()));
    }

    /**
     * Event Listener */
    @EventListener public void handleAddStoryEvent(UserStoryCreatedEvent event) {
        System.out.println("JEST FAJNIE");

        UserStory userStory = userStoryService.getById(event.getUserStoryId());

        List<Sprint> sprints = (List<Sprint>) sprintRepository.findAll(Sort.by("startDate"));
        for (Sprint sprint : sprints) {
            if (sprint.getStatus() == Sprint.StatusType.PENDING) {
                sprint.addUserStory(userStory);
                sprintRepository.save(sprint);
                log.info("Dodano do Sprinta o nazwie {} z id = {}", sprint.getName(), sprint.getId());
                return;
            }
        }

        log.info("Nie znaleziono  Sprinta o statusie PENDING :/");
    }


}
