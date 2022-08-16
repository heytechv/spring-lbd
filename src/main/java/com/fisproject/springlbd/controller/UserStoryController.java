package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/userstory")
@AllArgsConstructor
public class UserStoryController {

    SprintService sprintService;
    UserStoryService userStoryService;
    ApplicationEventPublisher publisher;

    final Logger log = LoggerFactory.getLogger(UserStoryController.class);

    /** Get description from UserStory - Zad 6 */
    @GetMapping("/description/{id}")
    public ResponseEntity<StandardResponse> getUserStoryDescription(@PathVariable Long id) {
        log.warn("called GET /userstory/description/{id}");
        return new StandardResponse(HttpStatus.OK, userStoryService.getDescription(id), "ok").buildResponseEntity();
    }

    /** Get list of all Atachments that belongs to UserStory */
    @GetMapping("/attachments/{id}")
    public StandardResponse getAttachments(@PathVariable Long id) {
        log.warn("called GET /userstory/attachments/{id}");
        return new StandardResponse(HttpStatus.OK, userStoryService.getAttachmentList(id), "ok");
    }

    /** Delete UserStory - Zad 10 */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteUserStory(@PathVariable Long id) {
        log.warn("called DELETE /userstory/{id}");
        userStoryService.deleteById(id);
        return new StandardResponse(HttpStatus.OK, "", "UserStory deleted").buildResponseEntity();
    }

    /** Get UserStories (page+limit) - Zad 12*/
    @GetMapping("/sorted")
    public ResponseEntity<StandardResponse> getSortedUserStories(@RequestParam Integer page, @RequestParam Integer limit) {
        return new StandardResponse(HttpStatus.OK, userStoryService.getSortedUserStories(page, limit), "ok").buildResponseEntity();
    }


}
