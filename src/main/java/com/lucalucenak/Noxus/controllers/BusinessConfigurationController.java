package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.BusinessConfigurationFullDto;
import com.lucalucenak.Noxus.dtos.post.BusinessConfigurationPostDto;
import com.lucalucenak.Noxus.dtos.response.BusinessConfigurationReturnDto;
import com.lucalucenak.Noxus.services.BusinessConfigurationService;
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
@RequestMapping(value = "business-configuration")
public class BusinessConfigurationController {

    @Autowired
    private BusinessConfigurationService BusinessConfigurationService;

    @GetMapping(value = "/{BusinessConfigurationId}")
    public ResponseEntity<BusinessConfigurationReturnDto> getBusinessConfigurationById(@PathVariable Long BusinessConfigurationId) {
        return ResponseEntity.ok().body(new BusinessConfigurationReturnDto(BusinessConfigurationService.findBusinessConfigurationById(BusinessConfigurationId)));
    }

    @GetMapping
    public ResponseEntity<Page<BusinessConfigurationReturnDto>> getAllAdditions(Pageable pageable) {
        List<BusinessConfigurationReturnDto> BusinessConfigurationReturnDtos = new ArrayList<>();
        for (BusinessConfigurationFullDto i : BusinessConfigurationService.findAllAdditionsPaginated(pageable)) {
            BusinessConfigurationReturnDtos.add(new BusinessConfigurationReturnDto(i));
        }
        Page<BusinessConfigurationReturnDto> BusinessConfigurationReturnDtoPage = new PageImpl<>(BusinessConfigurationReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), BusinessConfigurationReturnDtos.size());
        return ResponseEntity.ok().body(BusinessConfigurationReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<BusinessConfigurationReturnDto> saveBusinessConfiguration(@RequestBody @Valid BusinessConfigurationPostDto BusinessConfigurationPostDto) {
        return ResponseEntity.ok().body(new BusinessConfigurationReturnDto(BusinessConfigurationService.saveBusinessConfiguration(BusinessConfigurationPostDto)));
    }

    @PutMapping(value = "/{BusinessConfigurationId}")
    public ResponseEntity<BusinessConfigurationReturnDto> updateBusinessConfiguration(@PathVariable Long BusinessConfigurationId, @RequestBody @Valid BusinessConfigurationPostDto BusinessConfigurationPostDto) {
        return ResponseEntity.ok().body(new BusinessConfigurationReturnDto(BusinessConfigurationService.updateBusinessConfiguration(BusinessConfigurationId, BusinessConfigurationPostDto)));
    }

    @PostMapping(value = "/inactivate/{BusinessConfigurationId}")
    public ResponseEntity<BusinessConfigurationReturnDto> inactivateBusinessConfigurationById(@PathVariable Long BusinessConfigurationId) {
        return ResponseEntity.ok().body(new BusinessConfigurationReturnDto(BusinessConfigurationService.inactivateBusinessConfigurationById(BusinessConfigurationId)));
    }

    @DeleteMapping(value = "/{BusinessConfigurationId}")
    public ResponseEntity<BusinessConfigurationReturnDto> deleteBusinessConfigurationById(@PathVariable Long BusinessConfigurationId) {
        BusinessConfigurationService.deleteBusinessConfigurationById(BusinessConfigurationId);
        return ResponseEntity.noContent().build();
    }
}
