package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.SoupFullDto;
import com.lucalucenak.Noxus.dtos.post.SoupPostDto;
import com.lucalucenak.Noxus.dtos.response.SoupReturnDto;
import com.lucalucenak.Noxus.services.SoupService;
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
@RequestMapping(value = "/soup")
public class SoupController {

    @Autowired
    SoupService soupService;

    @GetMapping(value = "/{soupId}")
    public ResponseEntity<SoupReturnDto> getSoupById(@PathVariable Long soupId) {
        return ResponseEntity.ok().body(new SoupReturnDto(soupService.findSoupById(soupId)));
    }

    @GetMapping
    public ResponseEntity<Page<SoupReturnDto>> getAllSoups(Pageable pageable) {
        List<SoupReturnDto> soupReturnDtos = new ArrayList<>();
        for (SoupFullDto i : soupService.findAllSoupsPaginated(pageable)) {
            soupReturnDtos.add(new SoupReturnDto(i));
        }
        Page<SoupReturnDto> soupReturnDtoPage = new PageImpl<>(soupReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), soupReturnDtos.size());
        return ResponseEntity.ok().body(soupReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<SoupReturnDto> saveSoup(@RequestBody SoupPostDto soupPostDto) {
        return ResponseEntity.ok().body(new SoupReturnDto(soupService.saveSoup(soupPostDto)));
    }

    @PutMapping(value = "/{soupId}")
    public ResponseEntity<SoupReturnDto> updateSoup(@PathVariable Long soupId, @RequestBody SoupPostDto soupPostDto) throws Exception {
        return ResponseEntity.ok().body(new SoupReturnDto(soupService.updateSoup(soupId, soupPostDto)));
    }

    @DeleteMapping(value = "/{soupId}")
    public ResponseEntity<SoupReturnDto> deleteSoupById(@PathVariable Long soupId) {
        soupService.deleteSoupById(soupId);
        return ResponseEntity.noContent().build();
    }
}
