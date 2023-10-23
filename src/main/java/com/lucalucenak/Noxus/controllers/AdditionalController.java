package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.services.AdditionalService;
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
@RequestMapping(value = "/additional")
public class AdditionalController {

    @Autowired
    private AdditionalService additionalService;

    @GetMapping(value = "/{additionalId}")
    public ResponseEntity<AdditionalReturnDto> getAdditionalById(@PathVariable Long additionalId) {
        return ResponseEntity.ok().body(new AdditionalReturnDto(additionalService.findAdditionalById(additionalId)));
    }

    @GetMapping
    public ResponseEntity<Page<AdditionalReturnDto>> getAllAdditions(Pageable pageable) {
        List<AdditionalReturnDto> additionalReturnDtos = new ArrayList<>();
        for (AdditionalFullDto i : additionalService.findAllAdditionsPaginated(pageable)) {
            additionalReturnDtos.add(new AdditionalReturnDto(i));
        }
        Page<AdditionalReturnDto> additionalReturnDtoPage = new PageImpl<>(additionalReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), additionalReturnDtos.size());
        return ResponseEntity.ok().body(additionalReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<AdditionalReturnDto> saveAdditional(@RequestBody @Valid AdditionalPostDto additionalPostDto) {
        return ResponseEntity.ok().body(new AdditionalReturnDto(additionalService.saveAdditional(additionalPostDto)));
    }

    @PutMapping(value = "/{additionalId}")
    public ResponseEntity<AdditionalReturnDto> updateAdditional(@PathVariable Long additionalId, @RequestBody @Valid AdditionalPostDto additionalPostDto) {
        return ResponseEntity.ok().body(new AdditionalReturnDto(additionalService.updateAdditional(additionalId, additionalPostDto)));
    }

    @PostMapping(value = "/inactivate/{additionalId}")
    public ResponseEntity<AdditionalReturnDto> inactivateAdditionalById(@PathVariable Long additionalId) {
        return ResponseEntity.ok().body(new AdditionalReturnDto(additionalService.inactivateAdditionalById(additionalId)));
    }

    @DeleteMapping(value = "/{additionalId}")
    public ResponseEntity<AdditionalReturnDto> deleteAdditionalById(@PathVariable Long additionalId) {
        additionalService.deleteAdditionalById(additionalId);
        return ResponseEntity.noContent().build();
    }
}