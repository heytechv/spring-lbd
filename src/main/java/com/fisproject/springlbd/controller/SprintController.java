package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.event.UserStoryCreatedEvent;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/sprint")
@AllArgsConstructor
public class SprintController {

    SprintService sprintService;
    UserStoryService userStoryService;
    ApplicationEventPublisher publisher;

    final Logger log = LoggerFactory.getLogger(SprintController.class);

    /** Add new Sprint */
    @PostMapping("/add")
    public ResponseEntity<StandardResponse> addSprint(@Valid @RequestBody SprintDto sprintDto) {
        log.warn("\ncalled POST /sprint/add");
        sprintService.add(sprintDto);
        return new StandardResponse(HttpStatus.OK, "", "Sprint added successfully").buildResponseEntity();
    }

    /** Get all sprints - Zad 2 */
    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getSprintList(@RequestParam(value="tasks", required=false, defaultValue="false") boolean showUserStories) {
        log.warn("\ncalled GET /sprint/all");
        List l = sprintService.getAll(showUserStories);
        return new StandardResponse(HttpStatus.OK, l, "All found sprints").buildResponseEntity();
    }

    /** Add new UserStory to Sprint - Zad 3 */
    @PostMapping("/adduserstory/{id}")
    public ResponseEntity<StandardResponse> addUserStoryToSprintById(@PathVariable Long id, @Valid @RequestBody UserStoryDto userStoryDto) {
        log.warn("\ncalled /sprint/adduserstory/{id}");
        UserStory userStory = sprintService.addUserStory(id, userStoryDto);

        // Zad 18 - Event
        publisher.publishEvent(new UserStoryCreatedEvent(userStory.getId()));

        return new StandardResponse(HttpStatus.OK, "", "ok").buildResponseEntity();
    }

    /** Get sum of all story points that Sprint contains - Zad 4 */
    @GetMapping("/storypoints/{id}")
    public ResponseEntity<StandardResponse> getStoryPoints(@PathVariable Long id) {
        log.warn("\ncalled GET /sprint/storypoints/{id}");
        return new StandardResponse(HttpStatus.OK, sprintService.getStoryPointsAmount(id), "ok").buildResponseEntity();
    }

    /** Get all UserStories from Sprint - Zad 5 */
    @GetMapping("/userstories/{id}")
    public ResponseEntity<StandardResponse> getUserStoryListFromSprint(@PathVariable Long id) {
        log.warn("\ncalled GET /sprint/userstories/{id}");
        return new StandardResponse(HttpStatus.OK, sprintService.getUserStoryList(id), "ok").buildResponseEntity();
    }

    /** Update status of Sprint - Zad 9 */
    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<StandardResponse> updateSprintStatusById(@PathVariable Long id, @RequestParam Sprint.StatusType newStatus) {
        log.warn("\ncalled PUT /sprints/updatestatus/{id}");
        sprintService.updateSprintStatus(id, newStatus);
        return new StandardResponse(HttpStatus.OK, "", "Status updated").buildResponseEntity();
    }

    /** Find Sprints in date range - Zad 11 */
    @GetMapping("/daterange")
    public ResponseEntity<StandardResponse> getSprintsInDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        log.warn("called /sprints/daterange");
        return new StandardResponse(HttpStatus.OK, sprintService.getBetweenDate(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate)), "").buildResponseEntity();
    }


}
