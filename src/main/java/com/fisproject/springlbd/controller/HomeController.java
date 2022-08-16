package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.component.StandardResponse;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

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
    @PostMapping("/userstory")
//    public StandardResponse addNewUserStoryToSprintById(
//            @RequestParam("sprintId") Long sprintId,
//            @RequestParam("userStoryName") String userStoryName,
//            @RequestParam("userStoryDesc") String userStoryDesc,
//            @RequestParam("userStoryPoints") Integer userStoryPoints,
//            @RequestParam("userStoryStatus") UserStory.StatusType userStoryStatus) {
    public StandardResponse addNewUserStoryToSprintById(@RequestParam Long sprintId, @RequestBody UserStoryDto userStoryDto) {

        LOG.warn("called /sprints/addstory");
        sprintService.addUserStory(sprintId, userStoryDto);

        /** Zad 18 - Event */
        // TODO event naprawa
//        if (response.getStatus() == HttpStatus.OK.value())
//            publisher.publishEvent(new UserStoryCreatedEvent(userStory.getId()));

        return new StandardResponse(HttpStatus.OK, "", "ok");
    }

    /** Zad 4 */
    @GetMapping("/sprints/storypoints")
    public StandardResponse getStoryPoints(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/storypoints");
        sprintService.getStoryPointsAmount(sprintId);

        return new StandardResponse(HttpStatus.OK, "", "ok");
    }

    /** Zad 5 */
    @GetMapping("/sprints/userstories")
    public StandardResponse getUserStoryListFromSprint(@RequestParam("sprintId") Long sprintId) {
        LOG.warn("called /sprints/userstories");
        sprintService.getUserStories(sprintId);

        return new StandardResponse(HttpStatus.OK, "", "ok");
    }

    /** Zad 6 */
    @GetMapping("/userstories/description")
    public StandardResponse getUserStoryDescription(@RequestParam("userStoryId") Long userStoryId) {
        LOG.warn("called /userstories/description");
        return userStoryService.getUserStoryDescription(userStoryId);
    }

    /** Zad 7 */
    @PostMapping("/userstories/addattachment")
    public StandardResponse addAttachmentToSprint(
            @RequestParam("userStoryId") Long userStoryId,
            @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        LOG.warn("called /userstories/addattachment");

        Attachment attachment = new Attachment();
        try { attachment.setBinaryFile(attachmentFile.getBytes()); }
        catch (Exception ignore) { return new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR, "", "internal server error"); }
        // we do not save
        // let service do it for us
        return userStoryService.addAttachment(userStoryId, attachment, true);
    }

    /** Zad moje - wyswietl wszystkie zalaczniki */
    @GetMapping("/userstories/attachments")
    public StandardResponse getAttachments(@RequestParam("userStoryId") Long userStoryId) {
        LOG.warn("called /userstories/attachments");
        return userStoryService.getAttachmentList(userStoryId);
    }

    /** Zad 8 - pobierz zalacznik */
    @GetMapping("/userstories/attachments/download")
    public ResponseEntity<ByteArrayResource> getAttachmentsDownload(
            @RequestParam("userStoryId") Long userStoryId,
            @RequestParam(value="attachmentId", required=false) Long attachmentId) {

        LOG.warn("called /userstories/attachments/download");


        ByteArrayResource resource = new ByteArrayResource((byte[])userStoryService.getAttachmentList(userStoryId).getData());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    /** Zad 9 */
    @PutMapping("/sprints/updatestatus")
    public StandardResponse updateSprintStatusById(
            @RequestParam("sprintId") Long sprintId,
            @RequestParam("newStatus") Sprint.StatusType newStatus) {

        LOG.warn("called /sprints/status");
        sprintService.updateSprintStatus(sprintId, newStatus);

        return new StandardResponse(HttpStatus.OK, "", "ok");
    }

    /** Zad 10
     * <a href="https://www.youtube.com/watch?v=vYNdjtf7iAQ&ab_channel=ThorbenJanssen">...</a> */
    @DeleteMapping("/userstories/deleteuserstory")
    public StandardResponse deleteUserStory(@RequestParam("userStoryId") Long userStoryId) {
        LOG.warn("called /userstories/deleteuserstory");
        return userStoryService.deleteById(userStoryId);
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
        return new StandardResponse(
                HttpStatus.OK, auth.getName() + " " + auth.getAuthorities(), "logged in user");
    }

}
