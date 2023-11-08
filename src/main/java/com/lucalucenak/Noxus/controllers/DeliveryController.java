package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliveryPostDto;
import com.lucalucenak.Noxus.dtos.response.DeliveryReturnDto;
import com.lucalucenak.Noxus.services.DeliveryService;
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
@RequestMapping(value = "/delivery")
@CrossOrigin(origins = "http://localhost:4200")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping(value = "/{deliveryId}")
    public ResponseEntity<DeliveryReturnDto> getDeliveryById(@PathVariable Long deliveryId) {
        return ResponseEntity.ok().body(new DeliveryReturnDto(deliveryService.findDeliveryById(deliveryId)));
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryReturnDto>> getAllDeliveries(Pageable pageable) {
        List<DeliveryReturnDto> deliveryReturnDtos = new ArrayList<>();
        for (DeliveryFullDto i : deliveryService.findAllDeliveriesPaginated(pageable)) {
            deliveryReturnDtos.add(new DeliveryReturnDto(i));
        }
        Page<DeliveryReturnDto> deliveryReturnDtoPage = new PageImpl<>(deliveryReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), deliveryReturnDtos.size());
        return ResponseEntity.ok().body(deliveryReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<DeliveryReturnDto> saveDelivery(@RequestBody @Valid DeliveryPostDto deliveryPostDto) throws Exception {
        return ResponseEntity.ok().body(new DeliveryReturnDto(deliveryService.saveDelivery(deliveryPostDto)));
    }

    @PutMapping(value = "/{deliveryId}")
    public ResponseEntity<DeliveryReturnDto> updateDelivery(@PathVariable Long deliveryId, @RequestBody @Valid DeliveryPostDto deliveryPostDto) {
        return ResponseEntity.ok().body(new DeliveryReturnDto(deliveryService.updateDelivery(deliveryId, deliveryPostDto)));
    }

    @PostMapping(value = "/inactivate/{deliveryId}")
    public ResponseEntity<DeliveryReturnDto> inactivateDeliveryById(@PathVariable Long deliveryId) {
        return ResponseEntity.ok().body(new DeliveryReturnDto(deliveryService.inactivateDeliveryById(deliveryId)));
    }

    @DeleteMapping(value = "/{deliveryId}")
    public ResponseEntity<DeliveryReturnDto> deleteDeliveryById(@PathVariable Long deliveryId) {
        deliveryService.deleteDeliveryById(deliveryId);
        return ResponseEntity.noContent().build();
    }
}
