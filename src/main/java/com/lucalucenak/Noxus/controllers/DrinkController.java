package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.post.DrinkPostDto;
import com.lucalucenak.Noxus.dtos.response.DrinkReturnDto;
import com.lucalucenak.Noxus.services.DrinkService;
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
@RequestMapping(value = "/drink")
public class DrinkController {

    @Autowired
    DrinkService drinkService;

    @GetMapping(value = "/{drinkId}")
    public ResponseEntity<DrinkReturnDto> getDrinkById(@PathVariable Long drinkId) {
        return ResponseEntity.ok().body(new DrinkReturnDto(drinkService.findDrinkById(drinkId)));
    }

    @GetMapping
    public ResponseEntity<Page<DrinkReturnDto>> getAllDrinks(Pageable pageable) {
        List<DrinkReturnDto> drinkReturnDtos = new ArrayList<>();
        for (DrinkFullDto i : drinkService.findAllDrinksPaginated(pageable)) {
            drinkReturnDtos.add(new DrinkReturnDto(i));
        }
        Page<DrinkReturnDto> drinkReturnDtoPage = new PageImpl<>(drinkReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), drinkReturnDtos.size());
        return ResponseEntity.ok().body(drinkReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<DrinkReturnDto> saveDrink(@RequestBody DrinkPostDto drinkPostDto) {
        return ResponseEntity.ok().body(new DrinkReturnDto(drinkService.saveDrink(drinkPostDto)));
    }

    @PutMapping(value = "/{drinkId}")
    public ResponseEntity<DrinkReturnDto> updateDrink(@PathVariable Long drinkId, @RequestBody DrinkPostDto drinkPostDto) {
        return ResponseEntity.ok().body(new DrinkReturnDto(drinkService.updateDrink(drinkId, drinkPostDto)));
    }

    @DeleteMapping(value = "/{drinkId}")
    public ResponseEntity<DrinkReturnDto> deleteDrinkById(@PathVariable Long drinkId) {
        drinkService.deleteDrinkById(drinkId);
        return ResponseEntity.noContent().build();
    }
}
