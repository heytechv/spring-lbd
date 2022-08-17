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

    @PostMapping()
    public ResponseEntity<StandardResponse> addSprint(@Valid @RequestBody SprintDto sprintDto) {
        sprintService.add(sprintDto);
        return new StandardResponse(HttpStatus.OK, "Sprint added successfully").buildResponseEntity();
    }

    @GetMapping()
    public ResponseEntity<StandardResponse> getSprintList(
            @RequestParam(value="tasks", required=false, defaultValue="false") boolean showUserStories) {
        /* Zad 2 */
        return new StandardResponse(HttpStatus.OK, sprintService.getAll(showUserStories), "Found sprints")
                .buildResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getSprintById(
            @PathVariable Long id,
            @RequestParam(value="tasks", required=false, defaultValue="false") boolean showUserStories) {
        return new StandardResponse(HttpStatus.OK, sprintService.getById(id, showUserStories), "Found sprint")
                .buildResponseEntity();
    }

    @PostMapping("/{id}/adduserstory")
    public ResponseEntity<StandardResponse> addUserStoryToSprintById(@PathVariable Long id,
                                                                     @Valid @RequestBody UserStoryDto userStoryDto) {
        /* Zad 3 */
        UserStory userStory = sprintService.addUserStory(id, userStoryDto);

        // Zad 18 - Event
        publisher.publishEvent(new UserStoryCreatedEvent(userStory.getId()));

        return new StandardResponse(HttpStatus.OK, "ok").buildResponseEntity();
    }

    @GetMapping("/{id}/storypoints")
    public ResponseEntity<StandardResponse> getStoryPoints(@PathVariable Long id) {
        /* Zad 4 */
        return new StandardResponse(HttpStatus.OK, sprintService.getStoryPointsAmount(id), "ok")
                .buildResponseEntity();
    }

    @GetMapping("/{id}/userstories")
    public ResponseEntity<StandardResponse> getUserStoryListFromSprint(@PathVariable Long id) {
        /* Zad 5 */
        return new StandardResponse(HttpStatus.OK, sprintService.getUserStoryList(id), "ok")
                .buildResponseEntity();
    }

    @PutMapping("/{id}/updatestatus")
    public ResponseEntity<StandardResponse> updateSprintStatusById(@PathVariable Long id,
                                                                   @RequestParam Sprint.StatusType newStatus) {
        /* Zad 9 */
        sprintService.updateSprintStatus(id, newStatus);
        return new StandardResponse(HttpStatus.OK, "Status updated").buildResponseEntity();
    }

    @GetMapping("/daterange")
    public ResponseEntity<StandardResponse> getSprintsInDateRange(@RequestParam String startDate,
                                                                  @RequestParam String endDate) {
        /* Zad 11 */
        return new StandardResponse(HttpStatus.OK, sprintService.getBetweenDate(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate)), "")
                .buildResponseEntity();
    }


}
