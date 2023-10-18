package com.lucalucenak.Noxus.exceptions.handlers;

import com.lucalucenak.Noxus.dtos.ExceptionHandlerDto;
import com.lucalucenak.Noxus.exceptions.MustOrderAtLeastOneSoupOrOneDrinkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MustOrderAtLeastOneSoupOrOneDrinkExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MustOrderAtLeastOneSoupOrOneDrinkException.class)
    public ResponseEntity<ExceptionHandlerDto<?>> handle(MustOrderAtLeastOneSoupOrOneDrinkException ex) {
        ExceptionHandlerDto<?> handlerDto = new ExceptionHandlerDto<>();

        Map<String, String> errors = new HashMap<>();
        errors.put("status", ex.getMessage());

        handlerDto.setErrors(errors);
        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        handlerDto.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.badRequest().body(handlerDto);
    }
}