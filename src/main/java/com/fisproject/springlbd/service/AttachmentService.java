package com.fisproject.springlbd.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;

public interface AttachmentService {

    ByteArrayResource getDownload(Long id);

}
