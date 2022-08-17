package com.fisproject.springlbd.apiresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Setter @Getter
public class StandardResponse {

    private Integer status;
    private LocalDateTime timestamp;
    private Object data;
    private String message;
    private String debugMessage;
    private List<ApiError> apiErrorList;

    public StandardResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public StandardResponse(HttpStatus status, String message) {
        this();
        this.status=status.value();
        this.message=message;
    }

    public StandardResponse(HttpStatus status, Object data, String message) {
        this();
        this.status=status.value();
        this.data=data;
        this.message=message;
    }

    public StandardResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private void addError(ApiError error) {
        if (apiErrorList == null)
            apiErrorList = new ArrayList<>();
        apiErrorList.add(error);
    }

    /**
     * Validation errors (for @Valid) */
    public void addValidationError(String objectName, String message) {
        addError(new ApiValidationError(objectName, message));
    }

    public void addValidationError(String objectName, String fieldName, Object rejectedValue, String message) {
        addError(new ApiValidationError(objectName, fieldName, rejectedValue, message));
    }

    public void addValidationError(ObjectError objectError) {
        if (objectError != null)
            addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    public void addValidationError(FieldError fieldError) {
        if (fieldError != null)
            addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrorList) {
        fieldErrorList.forEach(this::addValidationError);
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
