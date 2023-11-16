package com.lucalucenak.Noxus.exceptions.handlers;

import com.lucalucenak.Noxus.dtos.ExceptionHandlerDto;
import com.lucalucenak.Noxus.exceptions.PaymentMethodCashWithoutPaidValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PaymentMethodCashWithoutPaidValueExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PaymentMethodCashWithoutPaidValueException.class)
    public ResponseEntity<ExceptionHandlerDto<?>> handle(PaymentMethodCashWithoutPaidValueException ex) {
        ExceptionHandlerDto<?> handlerDto = new ExceptionHandlerDto<>();

        Map<String, String> errors = new HashMap<>();
        errors.put("status", ex.getMessage());

        handlerDto.setErrors(errors);
        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        handlerDto.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.badRequest().body(handlerDto);
    }
}