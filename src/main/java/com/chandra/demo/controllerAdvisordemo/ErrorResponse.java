package com.chandra.demo.controllerAdvisordemo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

	public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
	
	private LocalDateTime timestamp;
 
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
 
 
    //Getter and setters
}
