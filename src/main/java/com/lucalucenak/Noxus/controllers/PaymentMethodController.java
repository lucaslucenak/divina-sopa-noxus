package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.PaymentMethodFullDto;
import com.lucalucenak.Noxus.dtos.response.PaymentMethodReturnDto;
import com.lucalucenak.Noxus.services.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/payment-method")
public class PaymentMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping(value = "/{paymentMethodId}")
    public ResponseEntity<PaymentMethodReturnDto> getPaymentMethodById(@PathVariable Long paymentMethodId) {
        return ResponseEntity.ok().body(new PaymentMethodReturnDto(paymentMethodService.findPaymentMethodById(paymentMethodId)));
    }

    @GetMapping
    public ResponseEntity<Page<PaymentMethodReturnDto>> getAllPaymentMethodsPaginated(Pageable pageable) {
        List<PaymentMethodReturnDto> paymentMethodReturnDtos = new ArrayList<>();
        for (PaymentMethodFullDto i : paymentMethodService.findAllPaymentMethodsPaginated(pageable)) {
            paymentMethodReturnDtos.add(new PaymentMethodReturnDto(i));
        }
        Page<PaymentMethodReturnDto> paymentMethodReturnDtoPage = new PageImpl<>(paymentMethodReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), paymentMethodReturnDtos.size());
        return ResponseEntity.ok().body(paymentMethodReturnDtoPage);
    }

    @PostMapping(value = "/inactivate/{paymentMethodId}")
    public ResponseEntity<PaymentMethodReturnDto> inactivatePaymentMethodById(@PathVariable Long paymentMethodId) {
        return ResponseEntity.ok().body(new PaymentMethodReturnDto(paymentMethodService.inactivatePaymentMethodById(paymentMethodId)));
    }

    @DeleteMapping(value = "/{paymentMethodId}")
    public ResponseEntity<PaymentMethodReturnDto> deletePaymentMethodById(@PathVariable Long paymentMethodId) {
        paymentMethodService.deletePaymentMethodById(paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}