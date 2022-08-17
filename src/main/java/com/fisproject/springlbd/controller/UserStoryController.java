package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.AttachmentDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        return new StandardResponse(HttpStatus.OK, userStoryService.getSortedUserStoryList(page, limit), "ok").buildResponseEntity();
    }

    /** Add new Attachment to UserStory - Zad 7 */
    @PostMapping("/addattachment/{id}")
    public ResponseEntity<StandardResponse> addAttachmentToSprint(@PathVariable Long id, @RequestParam MultipartFile attachmentFile) {
        log.warn("called /userstory/addattachment/{id}");

        AttachmentDto attachmentDto = new AttachmentDto();
        try { attachmentDto.setBinaryFile(attachmentFile.getBytes()); }
        catch (Exception ignore) { throw new RuntimeException("Couldn't parse attachment!"); }
        // we do not save
        // let service do it for us
        userStoryService.addAttachment(id, attachmentDto);

        return new StandardResponse(HttpStatus.OK, "Attachment added successfully").buildResponseEntity();
    }

    /** Get list of all Atachments that belongs to UserStory */
    @GetMapping("/attachments/{id}")
    public StandardResponse getAttachmentsList(@PathVariable Long id) {
        log.warn("called GET /userstory/attachments/{id}");
        return new StandardResponse(HttpStatus.OK, userStoryService.getAttachmentList(id), "ok");
    }



}
