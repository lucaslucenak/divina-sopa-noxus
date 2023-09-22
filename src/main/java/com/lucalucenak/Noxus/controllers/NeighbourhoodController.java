package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.NeighbourhoodFullDto;
import com.lucalucenak.Noxus.dtos.post.NeighbourhoodPostDto;
import com.lucalucenak.Noxus.dtos.response.NeighbourhoodReturnDto;
import com.lucalucenak.Noxus.services.NeighbourhoodService;
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
@RequestMapping(value = "/neighbourhood")
public class NeighbourhoodController {

    @Autowired
    NeighbourhoodService neighbourhoodService;

    @GetMapping(value = "/{neighbourhoodId}")
    public ResponseEntity<NeighbourhoodReturnDto> getNeighbourhoodById(@PathVariable Long neighbourhoodId) {
        return ResponseEntity.ok().body(new NeighbourhoodReturnDto(neighbourhoodService.findNeighbourhoodById(neighbourhoodId)));
    }

    @GetMapping
    public ResponseEntity<Page<NeighbourhoodReturnDto>> getAllNeighbourhoods(Pageable pageable) {
        List<NeighbourhoodReturnDto> neighbourhoodReturnDtos = new ArrayList<>();
        for (NeighbourhoodFullDto i : neighbourhoodService.findAllNeighbourhoodsPaginated(pageable)) {
            neighbourhoodReturnDtos.add(new NeighbourhoodReturnDto(i));
        }
        Page<NeighbourhoodReturnDto> neighbourhoodReturnDtoPage = new PageImpl<>(neighbourhoodReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), neighbourhoodReturnDtos.size());
        return ResponseEntity.ok().body(neighbourhoodReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<NeighbourhoodReturnDto> saveNeighbourhood(@RequestBody NeighbourhoodPostDto neighbourhoodPostDto) {
        return ResponseEntity.ok().body(new NeighbourhoodReturnDto(neighbourhoodService.saveNeighbourhood(neighbourhoodPostDto)));
    }

    @PutMapping(value = "/{neighbourhoodId}")
    public ResponseEntity<NeighbourhoodReturnDto> updateNeighbourhood(@PathVariable Long neighbourhoodId, @RequestBody NeighbourhoodPostDto neighbourhoodPostDto) {
        return ResponseEntity.ok().body(new NeighbourhoodReturnDto(neighbourhoodService.updateNeighbourhood(neighbourhoodId, neighbourhoodPostDto)));
    }

    @DeleteMapping(value = "/{neighbourhoodId}")
    public ResponseEntity<NeighbourhoodReturnDto> deleteNeighbourhoodById(@PathVariable Long neighbourhoodId) {
        neighbourhoodService.deleteNeighbourhoodById(neighbourhoodId);
        return ResponseEntity.noContent().build();
    }
}
