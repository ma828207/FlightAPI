package com.flight.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Exception Response having exception attributes.
 */
@Data
public class ExceptionResponse {
    private String errorCode;
    private String errorMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
    private LocalDateTime timestamp;
}
