package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import com.fisproject.springlbd.repository.AttachmentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.FileInputStream;

import java.nio.file.Files;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    AttachmentRepository attachmentRepository;

    private final Logger log = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    /**
     * Private Utilities
     * */
    private Attachment findById(Long id) {
        return attachmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Attachment with id="+id+" not found!"));
    }

    /**
     * Public
     * */
    @Override public ByteArrayResource getDownload(Long id) {
        Attachment attachment = findById(id);
        return new ByteArrayResource(attachment.getBinaryFile()) {
            @Override public String getFilename() {
                return attachment.getFileName();
            }
        };
    }

}
