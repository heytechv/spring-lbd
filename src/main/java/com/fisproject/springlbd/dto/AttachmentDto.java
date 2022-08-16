package com.fisproject.springlbd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentDto {

    private Long id;
    private byte[] binaryFile;

    public AttachmentDto(Long id, byte[] binaryFile) {
        this.id=id;
        this.binaryFile=binaryFile;
    }


//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }

    public byte[] getBinaryFile() { return binaryFile; }
    public void setName(byte[] binaryFile) {
        this.binaryFile = binaryFile;
    }

}