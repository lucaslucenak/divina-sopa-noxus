package com.lucalucenak.Noxus.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionHandlerDto<VE> {

    private Map<String, String> errors;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;
    private List<? extends VE> validExamples;


    public ExceptionHandlerDto() {
    }

    public ExceptionHandlerDto(Map<String, String> errors, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.errors = errors;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public ExceptionHandlerDto(Map<String, String> errors, HttpStatus httpStatus, ZonedDateTime zonedDateTime, List<VE> validExamples) {
        this.errors = errors;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
        this.validExamples = validExamples;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public List<? extends VE> getValidExamples() {
        return validExamples;
    }

    public void setValidExamples(List<? extends VE> validExamples) {
        this.validExamples = validExamples;
    }
}
