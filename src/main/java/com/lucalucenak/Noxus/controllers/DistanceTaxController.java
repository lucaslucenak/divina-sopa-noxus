package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.DistanceTaxFullDto;
import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.post.DistanceTaxPostDto;
import com.lucalucenak.Noxus.dtos.response.DistanceTaxReturnDto;
import com.lucalucenak.Noxus.dtos.response.DrinkReturnDto;
import com.lucalucenak.Noxus.services.DistanceTaxService;
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
@RequestMapping(value = "/distance-tax")
public class DistanceTaxController {

    @Autowired
    DistanceTaxService distanceTaxService;

    @GetMapping(value = "/{distanceTaxId}")
    public ResponseEntity<DistanceTaxReturnDto> getDistanceTaxById(@PathVariable Long distanceTaxId) {
        return ResponseEntity.ok().body(new DistanceTaxReturnDto(distanceTaxService.findDistanceTaxById(distanceTaxId)));
    }

    @GetMapping
    public ResponseEntity<Page<DistanceTaxReturnDto>> getAllDeliveries(Pageable pageable) {
        List<DistanceTaxReturnDto> distanceTaxReturnDtos = new ArrayList<>();
        for (DistanceTaxFullDto i : distanceTaxService.findAllDistanceTaxesPaginated(pageable)) {
            distanceTaxReturnDtos.add(new DistanceTaxReturnDto(i));
        }
        Page<DistanceTaxReturnDto> distanceTaxReturnDtoPage = new PageImpl<>(distanceTaxReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), distanceTaxReturnDtos.size());
        return ResponseEntity.ok().body(distanceTaxReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<DistanceTaxReturnDto> saveDistanceTax(@RequestBody @Valid DistanceTaxPostDto distanceTaxPostDto) throws Exception {
        return ResponseEntity.ok().body(new DistanceTaxReturnDto(distanceTaxService.saveDistanceTax(distanceTaxPostDto)));
    }

    @PutMapping(value = "/{distanceTaxId}")
    public ResponseEntity<DistanceTaxReturnDto> updateDistanceTax(@PathVariable Long distanceTaxId, @RequestBody @Valid DistanceTaxPostDto distanceTaxPostDto) {
        return ResponseEntity.ok().body(new DistanceTaxReturnDto(distanceTaxService.updateDistanceTax(distanceTaxId, distanceTaxPostDto)));
    }

    @PostMapping(value = "/inactivate/{distanceTaxId}")
    public ResponseEntity<DistanceTaxReturnDto> inactivateDistanceTaxById(@PathVariable Long distanceTaxId) {
        return ResponseEntity.ok().body(new DistanceTaxReturnDto(distanceTaxService.inactivateDistanceTaxById(distanceTaxId)));
    }

    @DeleteMapping(value = "/{distanceTaxId}")
    public ResponseEntity<DistanceTaxReturnDto> deleteDistanceTaxById(@PathVariable Long distanceTaxId) {
        distanceTaxService.deleteDistanceTaxById(distanceTaxId);
        return ResponseEntity.noContent().build();
    }
}
