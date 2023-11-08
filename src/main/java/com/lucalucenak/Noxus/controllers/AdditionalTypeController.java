package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.AdditionalTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalTypePostDto;
import com.lucalucenak.Noxus.dtos.response.AdditionalTypeReturnDto;
import com.lucalucenak.Noxus.services.AdditionalTypeService;
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
@RequestMapping(value = "/additional-type")
@CrossOrigin(origins = "http://localhost:4200")
public class AdditionalTypeController {

    @Autowired
    private AdditionalTypeService additionalTypeService;

    @GetMapping(value = "/{additionalTypeId}")
    public ResponseEntity<AdditionalTypeReturnDto> getAdditionalTypeById(@PathVariable Long additionalTypeId) {
        return ResponseEntity.ok().body(new AdditionalTypeReturnDto(additionalTypeService.findAdditionalTypeById(additionalTypeId)));
    }

    @GetMapping
    public ResponseEntity<Page<AdditionalTypeReturnDto>> getAllAdditionalTypes(Pageable pageable) {
        List<AdditionalTypeReturnDto> additionalTypeReturnDtos = new ArrayList<>();
        for (AdditionalTypeFullDto i : additionalTypeService.findAllAdditionalTypesPaginated(pageable)) {
            additionalTypeReturnDtos.add(new AdditionalTypeReturnDto(i));
        }
        Page<AdditionalTypeReturnDto> additionalTypeReturnDtoPage = new PageImpl<>(additionalTypeReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), additionalTypeReturnDtos.size());
        return ResponseEntity.ok().body(additionalTypeReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<AdditionalTypeReturnDto> saveAdditionalType(@RequestBody @Valid AdditionalTypePostDto additionalTypePostDto) {
        return ResponseEntity.ok().body(new AdditionalTypeReturnDto(additionalTypeService.saveAdditionalType(additionalTypePostDto)));
    }

    @PutMapping(value = "/{additionalTypeId}")
    public ResponseEntity<AdditionalTypeReturnDto> updateAdditionalType(@PathVariable Long additionalTypeId, @RequestBody @Valid AdditionalTypePostDto additionalTypePostDto) {
        return ResponseEntity.ok().body(new AdditionalTypeReturnDto(additionalTypeService.updateAdditionalType(additionalTypeId, additionalTypePostDto)));
    }

    @PostMapping(value = "/inactivate/{additionalTypeId}")
    public ResponseEntity<AdditionalTypeReturnDto> inactivateAdditionalTypeById(@PathVariable Long additionalTypeId) {
        return ResponseEntity.ok().body(new AdditionalTypeReturnDto(additionalTypeService.inactivateAdditionalTypeById(additionalTypeId)));
    }

    @DeleteMapping(value = "/{additionalTypeId}")
    public ResponseEntity<AdditionalTypeReturnDto> deleteAdditionalTypeById(@PathVariable Long additionalTypeId) {
        additionalTypeService.deleteAdditionalTypeById(additionalTypeId);
        return ResponseEntity.noContent().build();
    }
}
