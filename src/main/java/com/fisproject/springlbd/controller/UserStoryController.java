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

    @GetMapping("/{id}/description")
    public ResponseEntity<StandardResponse> getUserStoryDescription(@PathVariable Long id) {
        /* Zad 6 */
        return new StandardResponse(HttpStatus.OK, userStoryService.getDescription(id), "Found")
                .buildResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteUserStory(@PathVariable Long id) {
        /* Zad 10 */
        userStoryService.deleteById(id);
        return new StandardResponse(HttpStatus.OK, "UserStory deleted").buildResponseEntity();
    }

    @GetMapping("/sorted")
    public ResponseEntity<StandardResponse> getSortedUserStories(@RequestParam Integer page,
                                                                 @RequestParam Integer limit) {
        /* Zad 12 */
        return new StandardResponse(HttpStatus.OK, userStoryService.getSortedUserStoryList(page, limit), "Found")
                .buildResponseEntity();
    }

    @PostMapping("/{id}/addattachment")
    public ResponseEntity<StandardResponse> addAttachmentToUserStory(@PathVariable Long id,
                                                                     @RequestParam MultipartFile attachmentFile) {
        /* Zad 7 */
        AttachmentDto attachmentDto = new AttachmentDto();
        try { attachmentDto.setBinaryFile(attachmentFile.getBytes()); attachmentDto.setFileName(attachmentFile.getOriginalFilename()); }
        catch (Exception ignore) { throw new RuntimeException("Couldn't parse attachment!"); }
        // we do not save
        // let service do it for us
        userStoryService.addAttachment(id, attachmentDto);

        return new StandardResponse(HttpStatus.OK, "Attachment added successfully").buildResponseEntity();
    }

    @GetMapping("/{id}/attachment")
    public StandardResponse getAttachmentsList(@PathVariable Long id) {
        log.warn("called GET /userstory/attachments/{id}");
        return new StandardResponse(HttpStatus.OK, userStoryService.getAttachmentList(id), "Found");
    }


}