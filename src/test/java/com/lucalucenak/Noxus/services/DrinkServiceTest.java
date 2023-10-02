package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.post.DrinkPostDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.repositories.DrinkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DrinkServiceTest {

    @Mock
    private DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkService drinkService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void DrinkService_FindDrinkById_ReturnsSavedDrinkFullDto() {
        // Arrange
        Long drinkId = 1L;
        DrinkModel drinkModel = DrinkModel.builder()
                .name("COCA-COLA 1L")
                .price(8.0)
                .build();

        Mockito.when(drinkRepository.findById(1L))
                .thenReturn(Optional.of(drinkModel));

        // Act
        DrinkFullDto foundDrinkFullDto = drinkService.findDrinkById(drinkId);

        // Assert
        Assertions.assertNotNull(foundDrinkFullDto);
        Assertions.assertEquals(drinkModel.getName(), foundDrinkFullDto.getName());
        Assertions.assertEquals(drinkModel.getPrice(), foundDrinkFullDto.getPrice());
    }

    @Test
    public void DrinkService_FindDrinkById_ThrowsResourceNotFoundException() {
        // Arrange
        Long drinkId = 2L;

        Mockito.when(drinkRepository.findById(drinkId))
                .thenReturn(Optional.empty());
        Mockito.when(drinkRepository.findById(drinkId))
                .thenThrow(ResourceNotFoundException.class);

        // Assert
        // Act
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            DrinkFullDto foundDrinkFullDto = drinkService.findDrinkById(drinkId);
        });
    }

    @Test
    public void DrinkService_FindAllDrinksPaginated_ReturnsPageOfDrinksFullDtos() {
        List<DrinkModel> drinkModels = List.of(
                DrinkModel.builder()
                        .name("COCA COLA")
                        .price(8.5)
                        .build(),
                DrinkModel.builder()
                        .name("GUARANÁ")
                        .price(10.0)
                        .build()
        );

        Mockito.when(drinkRepository.findAll(PageRequest.of(0, 10)))
                        .thenReturn(new PageImpl<>(drinkModels));

        Page<DrinkFullDto> returnedPagedDrinksFullDtos = drinkService.findAllDrinksPaginated(PageRequest.of(0, 10));

        // Assert
        Assertions.assertEquals(drinkModels.size(), returnedPagedDrinksFullDtos.getTotalElements());
        Assertions.assertEquals(drinkModels.get(0).getName(), returnedPagedDrinksFullDtos.getContent().get(0).getName());
        Assertions.assertEquals(drinkModels.get(1).getName(), returnedPagedDrinksFullDtos.getContent().get(1).getName());
        Assertions.assertEquals(drinkModels.get(0).getPrice(), returnedPagedDrinksFullDtos.getContent().get(0).getPrice());
        Assertions.assertEquals(drinkModels.get(1).getPrice(), returnedPagedDrinksFullDtos.getContent().get(1).getPrice());
    }

    @Test
    public void DrinkService_SaveDrink_ReturnsSavedDrinkFullDto() {
        // Arrange
        DrinkPostDto drinkPostDto = DrinkPostDto.builder()
                .name("COCA-COLA 1L")
                .price(8.0)
                .build();
        DrinkModel drinkModel = new DrinkModel(drinkPostDto);

        Mockito.when(drinkRepository.save(Mockito.any(DrinkModel.class)))
                .thenReturn(drinkModel);

        // Act
        DrinkFullDto savedDrink = drinkService.saveDrink(drinkPostDto);

        // Assert
        Assertions.assertNotNull(savedDrink);
        Assertions.assertEquals(drinkModel.getName(), savedDrink.getName());
        Assertions.assertEquals(drinkModel.getPrice(), savedDrink.getPrice());
        Mockito.verify(drinkRepository, Mockito.times(1)).save(Mockito.any(DrinkModel.class));
    }

    @Test
    public void DrinkService_UpdateDrink_ReturnsSavedDrinkFullDto() {
        // Arrange
        Long drinkId = 1L;

        DrinkModel initialDrinkModel = DrinkModel.builder()
                .id(drinkId)
                .name("COCA_COLA")
                .price(10.0)
                .build();

        // Got from POST
        DrinkPostDto updatedDrinkPostDto = DrinkPostDto.builder()
                .id(drinkId)
                .name("COCA_COLA_UPDATED")
                .price(100.0)
                .build();

        // After Update
        DrinkModel expectedDrinkModel = DrinkModel.builder()
                .id(drinkId)
                .name("COCA_COLA_UPDATED")
                .price(100.0)
                .build();

        Mockito.when(drinkRepository.findById(drinkId))
                .thenReturn(Optional.of(initialDrinkModel));

        Mockito.when(drinkRepository.save(Mockito.any(DrinkModel.class)))
                .thenReturn(expectedDrinkModel);

        // Act
        DrinkFullDto returnedUpdatedDrink = drinkService.updateDrink(drinkId, updatedDrinkPostDto);

        // Assert
        Assertions.assertEquals(expectedDrinkModel.getName(), returnedUpdatedDrink.getName());
        Assertions.assertEquals(expectedDrinkModel.getPrice(), returnedUpdatedDrink.getPrice());
        Mockito.verify(drinkRepository, Mockito.times(1)).save(Mockito.any(DrinkModel.class));
    }
}
