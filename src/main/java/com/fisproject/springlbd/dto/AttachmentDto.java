package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter @Getter
public class AttachmentDto {

    private Long id;
    private byte[] binaryFile;

    public AttachmentDto() { }

    public AttachmentDto(Long id, byte[] binaryFile) {
        this.binaryFile=binaryFile;
    }


}
