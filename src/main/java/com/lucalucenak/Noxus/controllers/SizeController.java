package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.SizeFullDto;
import com.lucalucenak.Noxus.dtos.post.SizePostDto;
import com.lucalucenak.Noxus.dtos.response.SizeReturnDto;
import com.lucalucenak.Noxus.services.SizeService;
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
@RequestMapping(value = "/size")
public class SizeController {

    @Autowired
    SizeService sizeService;

    @GetMapping(value = "/{sizeId}")
    public ResponseEntity<SizeReturnDto> getSizeById(@PathVariable Long sizeId) {
        return ResponseEntity.ok().body(new SizeReturnDto(sizeService.findSizeById(sizeId)));
    }

    @GetMapping
    public ResponseEntity<Page<SizeReturnDto>> getAllSizes(Pageable pageable) {
        List<SizeReturnDto> sizeReturnDtos = new ArrayList<>();
        for (SizeFullDto i : sizeService.findAllSizesPaginated(pageable)) {
            sizeReturnDtos.add(new SizeReturnDto(i));
        }
        Page<SizeReturnDto> sizeReturnDtoPage = new PageImpl<>(sizeReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), sizeReturnDtos.size());
        return ResponseEntity.ok().body(sizeReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<SizeReturnDto> saveSize(@RequestBody SizePostDto sizePostDto) {
        return ResponseEntity.ok().body(new SizeReturnDto(sizeService.saveSize(sizePostDto)));
    }

    @PutMapping(value = "/{sizeId}")
    public ResponseEntity<SizeReturnDto> updateSize(@PathVariable Long sizeId, @RequestBody SizePostDto sizePostDto) throws Exception {
        return ResponseEntity.ok().body(new SizeReturnDto(sizeService.updateSize(sizeId, sizePostDto)));
    }

    @DeleteMapping(value = "/{sizeId}")
    public ResponseEntity<SizeReturnDto> deleteSizeById(@PathVariable Long sizeId) {
        sizeService.deleteSizeById(sizeId);
        return ResponseEntity.noContent().build();
    }
}
