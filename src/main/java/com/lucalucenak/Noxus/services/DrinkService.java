package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.repositories.DrinkRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Transactional
    public DrinkFullDto findDrinkById(Long drinkId) {
        Optional<DrinkModel> drinkOptional = drinkRepository.findById(drinkId);

        if (drinkOptional.isPresent()) {
            return new DrinkFullDto(drinkOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + drinkId);
        }
    }

    @Transactional
    public Page<DrinkFullDto> findAllDrinksPaginated(Pageable pageable) {
        Page<DrinkModel> pagedDrinks = drinkRepository.findAll(pageable);
        return pagedDrinks.map(DrinkFullDto::new);
    }

    @Transactional
    public DrinkFullDto saveDrink(DrinkFullDto drinkFullDto) {
        DrinkModel drinkModel = new DrinkModel(drinkFullDto);
        return new DrinkFullDto(drinkRepository.save(drinkModel));
    }

    public DrinkFullDto updateDrink(Long drinkId, DrinkFullDto drinkFullDto) {
        DrinkModel existingDrinkModel = new DrinkModel(this.findDrinkById(drinkId));
        DrinkModel updatedDrinkModel = new DrinkModel(drinkFullDto);

        BeanUtils.copyProperties(existingDrinkModel, updatedDrinkModel);
        return new DrinkFullDto(drinkRepository.save(updatedDrinkModel));
    }

    @Transactional
    public void deleteDrinkById(Long drinkId) {
        if (drinkRepository.existsById(drinkId)) {
            drinkRepository.deleteById(drinkId);
        } else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + drinkId);
        }
    }
}
