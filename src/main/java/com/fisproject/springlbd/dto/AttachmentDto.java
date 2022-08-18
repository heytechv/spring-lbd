package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter @Getter
public class AttachmentDto {

    private Long id;
    private String fileName;
    private byte[] binaryFile;

    public AttachmentDto() { }

    public AttachmentDto(Long id, String fileName, byte[] binaryFile) {
        this.id = id;
        this.fileName = fileName;
        this.binaryFile=binaryFile;
    }


}
