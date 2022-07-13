package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.entity.UserStory;
import com.fisproject.springlbd.event.UserStoryCreatedEvent;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class HomeController {

    final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired SprintService sprintService;
    @Autowired UserStoryService userStoryService;

    @Autowired ApplicationEventPublisher publisher;


    /** Zad 2 */
    @GetMapping("/sprints")
    public StandardResponse getSprintList(@RequestParam(value="tasks", required=false, defaultValue="false") boolean showUserStories) {
        LOG.warn("called /sprints");
        return sprintService.getSprints(showUserStories);
    }

    /** Zad 3 */
    @PostMapping("/sprints/addstory")
    public StandardResponse addNewUserStoryToSprintById(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/addstory");

        UserStory userStory = userStoryService.createUserStory(
                "stronaName",
                "stronaDesc",
                22,
                UserStory.StatusType.TO_DO,
                false);
        if (userStory == null)
            return new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR, "", "error");
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        if (!sprintService.addUserStory(sprintId, userStory, true))
            return new StandardResponse(HttpStatus.BAD_REQUEST, "", "id not found");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);


        /** Zad 18 - Event */
        publisher.publishEvent(new UserStoryCreatedEvent(userStory.getId()));

        return new StandardResponse(HttpStatus.OK, "", "added");
    }

    /** Zad 4 */
    @GetMapping("/sprints/storypoints")
    public StandardResponse getStoryPoints(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/storypoints");
        return sprintService.getStoryPointsAmount(sprintId);
    }

    /** Zad 5 */
    @GetMapping("/sprints/userstories")
    public StandardResponse getUserStoryListFromSprint(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/userstories");
        return sprintService.getUserStories(sprintId);
    }

    /** Zad 6 */
    @GetMapping("/userstories/description")
    public StandardResponse getUserStoryDescription(@RequestParam("userStoryId") Long userStoryId) {
        LOG.warn("called /userstories/description");
        return userStoryService.getUserStoryDescription(userStoryId);
    }

    /** Zad 7 */
    @PostMapping("/userstories/addattachment")
    public StandardResponse addAttachmentToSprint(@RequestParam("userStoryId") Long userStoryId) {

        LOG.warn("called /userstories/addattachment");
        Attachment attachment = new Attachment();
        attachment.setBinaryFile("siema".getBytes());
        // we do not save
        // let service do it for us

        return userStoryService.addAttachment(userStoryId, attachment, true);
    }

    /** Zad 8 */
    // TODO binary FILE nie string :/
    @GetMapping("/userstories/attachments")
    public StandardResponse getAttachments(@RequestParam("userStoryId") Long userStoryId) {
        LOG.warn("called /userstories/attachments");
        return userStoryService.getAttachmentList(userStoryId);
    }

    /** Zad 9 */
    @PutMapping("/sprints/updatestatus")
    public StandardResponse updateSprintStatusById(@RequestParam("sprintId") Long sprintId, @RequestParam("newStatus") Sprint.StatusType newStatus) {
        LOG.warn("called /sprints/status");
        return sprintService.updateSprintStatus(sprintId, newStatus);
    }

    /** Zad 10
     * <a href="https://www.youtube.com/watch?v=vYNdjtf7iAQ&ab_channel=ThorbenJanssen">...</a> */
    @DeleteMapping("/userstories/deleteuserstory")
    public StandardResponse deleteUserStory(@RequestParam("userStoryId") Long userStoryId) {
        Optional<UserStory> optionalUserStory = userStoryService.findById(userStoryId);
        if (optionalUserStory.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        userStoryService.delete(optionalUserStory.get());

        return new StandardResponse(HttpStatus.OK, "", "deleted");
    }

    /** Zad 11 */
    @GetMapping("/sprints/daterange")
    public StandardResponse getSprintsInDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        LOG.warn("called /sprints/daterange");
        return sprintService.findBetweenDate(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
    }

    /** Zad 12 */
    @GetMapping("/userstories/sorted")
    public StandardResponse getSortedUserStories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return userStoryService.getSortedUserStories(page, limit);
    }

    /** Zad 22 */
    @GetMapping("/whoami")
    public StandardResponse getLoggedUser() {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return new StandardResponse(HttpStatus.OK, auth.getName() + " " + auth.getAuthorities(), "show logger user");
    }


}
