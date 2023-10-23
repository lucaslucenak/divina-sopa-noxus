package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.DeliverymanFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliverymanPostDto;
import com.lucalucenak.Noxus.dtos.response.DeliverymanReturnDto;
import com.lucalucenak.Noxus.services.DeliverymanService;
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
@RequestMapping(value = "/deliveryman")
public class DeliverymanController {

    @Autowired
    DeliverymanService deliverymanService;

    @GetMapping(value = "/{deliverymanId}")
    public ResponseEntity<DeliverymanReturnDto> getDeliverymanById(@PathVariable Long deliverymanId) {
        return ResponseEntity.ok().body(new DeliverymanReturnDto(deliverymanService.findDeliverymanById(deliverymanId)));
    }

    @GetMapping
    public ResponseEntity<Page<DeliverymanReturnDto>> getAllDeliveries(Pageable pageable) {
        List<DeliverymanReturnDto> deliverymanReturnDtos = new ArrayList<>();
        for (DeliverymanFullDto i : deliverymanService.findAllDeliverymansPaginated(pageable)) {
            deliverymanReturnDtos.add(new DeliverymanReturnDto(i));
        }
        Page<DeliverymanReturnDto> deliverymanReturnDtoPage = new PageImpl<>(deliverymanReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), deliverymanReturnDtos.size());
        return ResponseEntity.ok().body(deliverymanReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<DeliverymanReturnDto> saveDeliveryman(@RequestBody @Valid DeliverymanPostDto deliverymanPostDto) throws Exception {
        return ResponseEntity.ok().body(new DeliverymanReturnDto(deliverymanService.saveDeliveryman(deliverymanPostDto)));
    }

    @PutMapping(value = "/{deliverymanId}")
    public ResponseEntity<DeliverymanReturnDto> updateDeliveryman(@PathVariable Long deliverymanId, @RequestBody @Valid DeliverymanPostDto deliverymanPostDto) {
        return ResponseEntity.ok().body(new DeliverymanReturnDto(deliverymanService.updateDeliveryman(deliverymanId, deliverymanPostDto)));
    }

    @PostMapping(value = "/inactivate/{deliverymanId}")
    public ResponseEntity<DeliverymanReturnDto> inactivateDeliverymanById(@PathVariable Long deliverymanId) {
        return ResponseEntity.ok().body(new DeliverymanReturnDto(deliverymanService.inactivateDeliverymanById(deliverymanId)));
    }

    @DeleteMapping(value = "/{deliverymanId}")
    public ResponseEntity<DeliverymanReturnDto> deleteDeliverymanById(@PathVariable Long deliverymanId) {
        deliverymanService.deleteDeliverymanById(deliverymanId);
        return ResponseEntity.noContent().build();
    }
}
