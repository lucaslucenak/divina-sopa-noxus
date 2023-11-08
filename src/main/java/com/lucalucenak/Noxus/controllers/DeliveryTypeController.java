package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.DeliveryTypeFullDto;
import com.lucalucenak.Noxus.dtos.response.DeliveryTypeReturnDto;
import com.lucalucenak.Noxus.services.DeliveryTypeService;
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
@RequestMapping(value = "/delivery-type")
@CrossOrigin(origins = "http://localhost:4200")
public class DeliveryTypeController {

    @Autowired
    DeliveryTypeService deliveryTypeService;

    @GetMapping(value = "/{deliveryTypeId}")
    public ResponseEntity<DeliveryTypeReturnDto> getDeliveryTypeById(@PathVariable Long deliveryTypeId) {
        return ResponseEntity.ok().body(new DeliveryTypeReturnDto(deliveryTypeService.findDeliveryTypeById(deliveryTypeId)));
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryTypeReturnDto>> getAllDeliveryTypesPaginated(Pageable pageable) {
        List<DeliveryTypeReturnDto> deliveryTypeReturnDtos = new ArrayList<>();
        for (DeliveryTypeFullDto i : deliveryTypeService.findAllDeliveryTypesPaginated(pageable)) {
            deliveryTypeReturnDtos.add(new DeliveryTypeReturnDto(i));
        }
        Page<DeliveryTypeReturnDto> deliveryTypeReturnDtoPage = new PageImpl<>(deliveryTypeReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), deliveryTypeReturnDtos.size());
        return ResponseEntity.ok().body(deliveryTypeReturnDtoPage);
    }

    @PostMapping(value = "/inactivate/{deliveryTypeId}")
    public ResponseEntity<DeliveryTypeReturnDto> inactivateDeliveryTypeById(@PathVariable Long deliveryTypeId) {
        return ResponseEntity.ok().body(new DeliveryTypeReturnDto(deliveryTypeService.inactivateDeliveryTypeById(deliveryTypeId)));
    }

    @DeleteMapping(value = "/{deliveryTypeId}")
    public ResponseEntity<DeliveryTypeReturnDto> deleteDeliveryTypeById(@PathVariable Long deliveryTypeId) {
        deliveryTypeService.deleteDeliveryTypeById(deliveryTypeId);
        return ResponseEntity.noContent().build();
    }
}