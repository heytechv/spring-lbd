package com.fisproject.springlbd.apiresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter @Getter
public class StandardResponse {

    private Integer status;
    private Object data;
    private String message;

    public StandardResponse(HttpStatus status, Object data, String message) {
        this.status=status.value();
        this.data=data;
        this.message=message;
    }

    /**
     * Build ResponseEntity message */
    public ResponseEntity<StandardResponse> buildResponseEntity() {
        return ResponseEntity.status(this.getStatus()).body(this);
    }

    public ResponseEntity<Object> buildResponseEntityObject() {
        return ResponseEntity.status(this.getStatus()).body(this);
    }

}
