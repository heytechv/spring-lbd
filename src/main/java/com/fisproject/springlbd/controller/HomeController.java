package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.SprintZad11Dto;
import com.fisproject.springlbd.dto.UserStoryZad5Dto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class HomeController {

    final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired SprintService sprintService;
    @Autowired UserStoryService userStoryService;

    @GetMapping("/sprints")
    public List<SprintDto> getSprintList(@RequestParam("tasks") boolean showUserStories) {
        LOG.warn("called /sprints");

        List<Sprint> sprints = sprintService.findAll();

        return sprints.stream().map(sprint -> {
            SprintDto sprintDto = sprintService.convertEntityToDto(sprint);
            if (showUserStories)
                sprintDto.setUserStoryDtos(
                        sprint.getUserStories().stream().map(userStory -> userStoryService.convertEntityToZad2Dto(userStory)).collect(Collectors.toList())
                );
            else
                sprintDto.setUserStoryDtos(new ArrayList<>());

            return sprintDto;
        }).collect(Collectors.toList());

//        return sprints;
    }

    @PostMapping("/sprints/addstory")
    public String addNewUserStoryToSprintById(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/addstory");

        UserStory userStory = userStoryService.createUserStory(
                "stronaName",
                "stronaDesc",
                22,
                UserStory.StatusType.TO_DO,
                false);
        if (userStory == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        if (!sprintService.addUserStory(sprintId, userStory, true))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return HttpStatus.OK.toString();
    }

    /** Zad 4 */
    @GetMapping("/sprints/storypoints")
    public StandardResponse getStoryPoints(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/storypoints");

        return sprintService.getStoryPointsAmount(sprintId);
    }

    @GetMapping("/sprints/userstories")
    public StandardResponse getUserStoryListFromSprint(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/userstories");

        return sprintService.getUserStories(sprintId);
    }

    @GetMapping("/userstories/description")
    public String getUserStoryDescription(@RequestParam("userStoryId") Long userStoryId) {
        Optional<UserStory> optionalUserStory = userStoryService.findById(userStoryId);
        if (optionalUserStory.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return optionalUserStory.get().getDescription();
    }

    /* TODO Zad 7 i 8 */

    /** Zad 9 */
    @PutMapping("/sprints/status")
    public String updateSprintStatusById(@RequestParam("sprintId") Long sprintId, @RequestParam("newStatus") Sprint.StatusType newStatus) {

        if (!Arrays.asList(Sprint.StatusType.values()).contains(newStatus))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Optional<Sprint> optionalSprint = sprintService.findById(sprintId);
        if (optionalSprint.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Sprint sprint = optionalSprint.get();
        sprint.setStatus(newStatus);
        sprintService.save(sprint);

        return HttpStatus.OK.toString();
    }

    /** Zad 10
     * <a href="https://www.youtube.com/watch?v=vYNdjtf7iAQ&ab_channel=ThorbenJanssen">...</a> */
    @DeleteMapping("/userstories")
    public String deleteUserStory(@RequestParam("userStoryId") Long userStoryId) {
        Optional<UserStory> optionalUserStory = userStoryService.findById(userStoryId);
        if (optionalUserStory.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        userStoryService.delete(optionalUserStory.get());

        return HttpStatus.OK.toString();
    }

    /** Zad 11 */
    // TODO null zabezpiecz
    @GetMapping("/sprints/daterange")
    public List<SprintZad11Dto> getSprintsInDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {

        return sprintService.findBetweenDate(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate))
                .stream().map(sprint -> new SprintZad11Dto(sprint.getId(), sprint.getName(), sprint.getStartDate(), sprint.getEndDate(), sprint.getStatus())).collect(Collectors.toList());
    }

    /** Zad 12 */
    @GetMapping("/userstories/sorted")
    public List<UserStoryZad5Dto> getSortedUserStories(@RequestParam("page") int page, @RequestParam("limit") int limit) {

        return userStoryService.findAllPageAndSortByName(page, limit).stream().map(userStory -> userStoryService.convertEntityToZad5Dto(userStory)).collect(Collectors.toList());
    }








}
