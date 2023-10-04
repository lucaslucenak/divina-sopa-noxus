package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.SizeFullDto;
import com.lucalucenak.Noxus.dtos.post.SizePostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.repositories.SizeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SizeServiceTest {

    @Mock
    private SizeRepository sizeRepository;

    @InjectMocks
    private SizeService sizeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void SizeService_FindSizeById_ReturnsSavedSizeFullDto() {
        // Arrange
        Long sizeId = 1L;
        SizeModel existentSizeModel = SizeModel.builder()
                .size("1000ML")
                .build();

        Mockito.when(sizeRepository.findById(sizeId))
                .thenReturn(Optional.of(existentSizeModel));

        // Act
        SizeModel returnedSizeModel = new SizeModel(sizeService.findSizeById(sizeId));

        // Assert
        Assertions.assertEquals(existentSizeModel.getSize(), returnedSizeModel.getSize());
        Mockito.verify(sizeRepository, Mockito.times(1)).findById(sizeId);
    }

    @Test
    public void SizeService_FindSizeById_ThrowsResourceNotFoundException() {
        // Arrange
        Long sizeId = 1L;

        Mockito.when(sizeRepository.findById(sizeId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            sizeService.findSizeById(sizeId);
        });
    }

    @Test
    public void SizeService_FindAllSizesPaginated_ReturnsAllSizesFullDtosPaginated() {
        List<SizeModel> existentSizesModels = List.of(
                SizeModel.builder()
                        .size("1000ML")
                        .build(),
                SizeModel.builder()
                        .size("1500ML")
                        .build()
        );
        Page<SizeModel> existentSizesModelsPage = new PageImpl<>(existentSizesModels);

        Mockito.when(sizeRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(existentSizesModelsPage);

        Page<SizeFullDto> returnedPage = sizeService.findAllSizesPaginated(PageRequest.of(0, 10));

        Assertions.assertEquals(existentSizesModels.get(0).getSize(), returnedPage.getContent().get(0).getSize());
        Assertions.assertEquals(existentSizesModels.get(1).getSize(), returnedPage.getContent().get(1).getSize());
    }

    @Test
    public void SizeService_SaveSize_ReturnsSizeFullDtoSaved() {
        // Arrange
        // Got from POST
        SizePostDto sizePostDto = SizePostDto.builder()
                .size("1000ML")
                .build();

        SizeModel sizeModel = new SizeModel(sizePostDto);

        Mockito.when(sizeRepository.save(Mockito.any(SizeModel.class)))
                .thenReturn(sizeModel);

        // Expected Return
        SizeFullDto expectedReturn = SizeFullDto.builder()
                .size("1000ML")
                .build();

        // Act
        SizeFullDto returnedSizeFullDto = sizeService.saveSize(sizePostDto);

        // Assert
        Assertions.assertEquals(expectedReturn.getSize(), returnedSizeFullDto.getSize());
    }

    @Test
    public void SizeService_UpdateSize_ReturnsSizeFullDtoSaved() {
        // Arrange
        SizeModel initialSizeModel = SizeModel.builder()
                .size("1000ML")
                .build();

        Long sizeId = 1L;
        SizePostDto recivedSizeFromPost = SizePostDto.builder()
                .id(sizeId)
                .size("1500ML")
                .build();

        SizeModel expectedSizeModel = SizeModel.builder()
                .size("1500ML")
                .build();

        Mockito.when(sizeRepository.findById(sizeId))
                .thenReturn(Optional.of(initialSizeModel));

        Mockito.when(sizeRepository.save(Mockito.any(SizeModel.class)))
                .thenReturn(expectedSizeModel);

        // Act
        SizeFullDto returnedSizeFullDto = sizeService.updateSize(sizeId, recivedSizeFromPost);

        // Assert
        Assertions.assertEquals(expectedSizeModel.getSize(), returnedSizeFullDto.getSize());
        Mockito.verify(sizeRepository, Mockito.times(1)).findById(sizeId);
        Mockito.verify(sizeRepository, Mockito.times(1)).save(Mockito.any(SizeModel.class));
    }

    @Test
    public void SizeService_UpdateSize_ThrowsIncompatibleIdsException() {
        // Arrange
        Long sizeId = 2L;
        SizePostDto recivedSizeFromPost = SizePostDto.builder()
                .id(1L)
                .size("1000ML")
                .build();

        // Act
        // Assert
        Assertions.assertThrows(IncompatibleIdsException.class, () -> {
            sizeService.updateSize(sizeId, recivedSizeFromPost);
        });
    }

    @Test
    public void SizeService_DeleteSizeById_ReturnsVoid() {
        // Arrange
        Long sizeId = 1L;

        Mockito.when(sizeRepository.existsById(sizeId))
                .thenReturn(true);

        // Act
        sizeService.deleteSizeById(sizeId);

        // Assert
        Mockito.verify(sizeRepository, Mockito.times(1)).deleteById(sizeId);
    }

    @Test
    public void SizeService_DeleteSizeById_ThrowsResourceNotFoundException() {
        // Arrange
        Long sizeId = 1L;

        Mockito.when(sizeRepository.existsById(sizeId))
                .thenReturn(false);

        // Act
        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
           sizeService.deleteSizeById(sizeId);
        });
        Mockito.verify(sizeRepository, Mockito.times(0)).deleteById(sizeId);
    }
}
