package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.post.DrinkPostDto;
import com.lucalucenak.Noxus.dtos.response.DrinkReturnDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.models.StatusModel;
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
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public DrinkFullDto findDrinkById(Long drinkId) {
        Optional<DrinkModel> drinkOptional = drinkRepository.findById(drinkId);

        if (drinkOptional.isPresent()) {
            return new DrinkFullDto(drinkOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + drinkId);
        }
    }

    @Transactional(readOnly = true)
    public Page<DrinkFullDto> findAllDrinksPaginated(Pageable pageable) {
        Page<DrinkModel> pagedDrinks = drinkRepository.findAll(pageable);
        return pagedDrinks.map(DrinkFullDto::new);
    }

    @Transactional
    public DrinkFullDto saveDrink(DrinkPostDto drinkPostDto) {
        DrinkModel drinkModel = new DrinkModel(drinkPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(drinkPostDto.getStatusId()));
        drinkModel.setStatus(statusModel);

        return new DrinkFullDto(drinkRepository.save(drinkModel));
    }

    public DrinkFullDto updateDrink(Long drinkId, DrinkPostDto drinkPostDto) {
        if (!drinkId.equals(drinkPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + drinkId + ", Body Id: " + drinkPostDto.getId());
        }

        DrinkModel existingDrinkModel = new DrinkModel(this.findDrinkById(drinkId));
        DrinkModel updatedDrinkModel = new DrinkModel(drinkPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(drinkPostDto.getStatusId()));
        updatedDrinkModel.setStatus(statusModel);

        BeanUtils.copyProperties(updatedDrinkModel, existingDrinkModel, "createdAt, updatedAt");
        return new DrinkFullDto(drinkRepository.save(existingDrinkModel));
    }

    @Transactional
    public void deleteDrinkById(Long drinkId) {
        if (drinkRepository.existsById(drinkId)) {
            drinkRepository.deleteById(drinkId);
        } else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + drinkId);
        }
    }

    @Transactional
    public DrinkFullDto inactivateDrinkById(Long drinkId) {
        DrinkModel drinkModel = new DrinkModel(this.findDrinkById(drinkId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        drinkModel.setStatus(inactiveStatsModel);

        return new DrinkFullDto(drinkRepository.save(drinkModel));
    }
}
