package com.fisproject.springlbd.controller;

import com.fisproject.springlbd.apiresponse.StandardResponse;
import com.fisproject.springlbd.dto.SprintDto;
import com.fisproject.springlbd.dto.UserStoryDto;
import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.repository.AttachmentRepository;
import com.fisproject.springlbd.service.AttachmentService;
import com.fisproject.springlbd.service.AttachmentServiceImpl;
import com.fisproject.springlbd.service.SprintService;
import com.fisproject.springlbd.service.UserStoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

    SprintService sprintService;
    AttachmentService attachmentService;
    ApplicationEventPublisher publisher;
    AttachmentRepository attachmentRepository;

    final Logger log = LoggerFactory.getLogger(AttachmentController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getAttachmentDownload(@PathVariable Long id) {
        /* Zad 8 */
        ByteArrayResource byteArrayResource = attachmentService.getDownload(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+byteArrayResource.getFilename())
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayResource);
    }

}
