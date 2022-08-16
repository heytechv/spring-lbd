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
@RequestMapping("/attachment")
@AllArgsConstructor
public class AttachmentController {

    final Logger log = LoggerFactory.getLogger(AttachmentController.class);

    SprintService sprintService;
    UserStoryService userStoryService;
    ApplicationEventPublisher publisher;


    /**
     * Zad 7 */
    // todo attachment
    @PostMapping("/attachment")
    public ResponseEntity<StandardResponse> addAttachmentToSprint(
            @RequestParam("userStoryId") Long userStoryId,
            @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        log.warn("called /userstories/addattachment");

        Attachment attachment = new Attachment();
        try { attachment.setBinaryFile(attachmentFile.getBytes()); }
        catch (Exception ignore) { return new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR, "", "internal server error").buildResponseEntity(); }
        // we do not save
        // let service do it for us
        return userStoryService.addAttachment(userStoryId, attachment, true).buildResponseEntity();
    }

    @GetMapping("/userstory/attachments/{id}")
    public StandardResponse getAttachments(@PathVariable Long id) {
        log.warn("\ncalled GET /userstory/attachments/{id}");
        return new StandardResponse(HttpStatus.OK, userStoryService.getAttachmentList(id), "ok");
    }

    /**
     * Zad 8 - pobierz zalacznik */
    // todo download attachment
    @GetMapping("/userstories/attachments/download")
    public ResponseEntity<ByteArrayResource> getAttachmentsDownload(
            @RequestParam("userStoryId") Long userStoryId,
            @RequestParam(value="attachmentId", required=false) Long attachmentId) {

        log.warn("called /userstories/attachments/download");


//        ByteArrayResource resource = new ByteArrayResource((byte[])userStoryService.getAttachmentList(userStoryId).getData());
        ByteArrayResource resource = new ByteArrayResource((byte[])"xdd".getBytes());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    /** Zad 22 */
    @GetMapping("/whoami")
    public ResponseEntity<StandardResponse> getLoggedUser() {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return new StandardResponse(
                HttpStatus.OK, auth.getName() + " " + auth.getAuthorities(), "logged in user").buildResponseEntity();
    }

}
