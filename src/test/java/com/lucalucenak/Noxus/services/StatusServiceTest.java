package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.StatusFullDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.StatusRepository;
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
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StatusServiceTest {

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private StatusService statusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void StatusService_FindStatusById_ReturnsAllStatusFullDto() {
        // Arrange
        StatusModel statusModel = StatusModel.builder()
                .id(1L)
                .status("ACTIVE")
                .build();

        Mockito.when(statusRepository.findById(statusModel.getId()))
                .thenReturn(Optional.of(statusModel));

        // Act
        StatusFullDto returnedStatusFullDto = statusService.findStatusById(statusModel.getId());

        // Assert
        Assertions.assertNotNull(returnedStatusFullDto);
        Assertions.assertEquals(returnedStatusFullDto.getId(), statusModel.getId());
        Assertions.assertEquals(returnedStatusFullDto.getStatus(), statusModel.getStatus());
    }

    @Test
    public void StatusService_FindStatusById_ThrowsResourceNotFoundException() {
        // Arrange
        Long statusId = 2L;

        Mockito.when(statusRepository.findById(statusId))
                .thenReturn(Optional.empty());

        // Act
        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            StatusFullDto returnedStatusFullDto = statusService.findStatusById(statusId);
        });
    }

    @Test
    public void StatusService_FindStatusByStatus_ReturnsAllStatusFullDto() {
        // Arrange
        StatusModel statusModel = StatusModel.builder()
                .status("ACTIVE")
                .build();

        Mockito.when(statusRepository.findByStatus(statusModel.getStatus()))
                .thenReturn(Optional.of(statusModel));

        // Act
        StatusFullDto returnedStatusFullDto = statusService.findStatusByStatus(statusModel.getStatus());

        // Assert
        Assertions.assertNotNull(returnedStatusFullDto);
        Assertions.assertEquals(returnedStatusFullDto.getStatus(), statusModel.getStatus());
    }

    @Test
    public void StatusService_FindStatusByStatus_ThrowsResourceNotFoundException() {
        // Arrange
        String status = "INACTIVE";

        Mockito.when(statusRepository.findByStatus(status))
                .thenReturn(Optional.empty());

        // Act
        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            StatusFullDto returnedStatusFullDto = statusService.findStatusByStatus(status);
        });
    }

    @Test
    public void StatusService_FindAllStatusPaginated_ReturnsAllStatusFullDto() {
        // Arrange
        List<StatusModel> statusModelList = List.of(
                StatusModel.builder()
                        .status("ACTIVE")
                        .build(),
                StatusModel.builder()
                        .status("INACTIVE")
                        .build()
        );

        Mockito.when(statusRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(statusModelList));

        // Act
        Page<StatusFullDto> resultPaged = statusService.findAllStatusPaginated(PageRequest.of(0, 10));

        // Assert
        Assertions.assertEquals(statusModelList.size(), resultPaged.getTotalElements());
        Assertions.assertEquals(statusModelList.get(0).getStatus(), resultPaged.getContent().get(0).getStatus());
        Assertions.assertEquals(statusModelList.get(1).getStatus(), resultPaged.getContent().get(1).getStatus());
    }

    @Test
    public void StatusService_SaveStatus_ReturnsStatusFullDto() {

        // Arrange
        StatusModel statusModel = StatusModel.builder().status("ACTIVE").build();
        StatusFullDto statusFullDto = StatusFullDto.builder().status("ACTIVE").build();

        Mockito.when(statusRepository.save(Mockito.any(StatusModel.class)))
                .thenReturn(statusModel);

        // Act
        StatusFullDto savedStatusFullDto = statusService.saveStatus(statusFullDto);

        // Assert
        Assertions.assertNotNull(savedStatusFullDto);
        Assertions.assertEquals(statusModel.getStatus(), savedStatusFullDto.getStatus());
    }

    @Test
    public void StatusService_UpdateStatus_ReturnUpdatedStatusFullDto() {
        // Arrange
        Long statusId = 1L;
        StatusModel existentStatus = StatusModel.builder()
                .id(statusId)
                .status("ACTIVE")
                .build();

        StatusModel updatedStatus = StatusModel.builder()
                .id(statusId)
                .status("ACTIVE_UPDATED")
                .build();

        Mockito.when(statusRepository.findById(statusId))
                .thenReturn(Optional.of(existentStatus));

        Mockito.when(statusRepository.save(Mockito.any(StatusModel.class)))
                .thenReturn(updatedStatus);

        existentStatus = statusRepository.findById(statusId).get();
        BeanUtils.copyProperties(existentStatus, updatedStatus);

        // Act
        StatusFullDto returnedUpdatedStatus = statusService.updateStatus(updatedStatus.getId(), new StatusFullDto(updatedStatus));

        // Assert
        Assertions.assertNotNull(returnedUpdatedStatus);
        Assertions.assertEquals(updatedStatus.getId(), returnedUpdatedStatus.getId());
        Assertions.assertEquals(updatedStatus.getStatus(), returnedUpdatedStatus.getStatus());
        Mockito.verify(statusRepository, Mockito.times(1)).save(Mockito.any(StatusModel.class));
    }

    @Test
    public void StatusService_UpdateStatus_ThrowsIncompatibleIdsException() {
        // Arrange
        Long statusId = 1L;

        StatusFullDto updatedStatus = StatusFullDto.builder()
                .id(2L)
                .status("ACTIVE_UPDATED")
                .build();

        // Act
        // Assert
        Assertions.assertThrows(IncompatibleIdsException.class, () -> {
            statusService.updateStatus(statusId, updatedStatus);
        });
        Mockito.verify(statusRepository, Mockito.never()).save(Mockito.any(StatusModel.class));
    }

    @Test
    public void StatusService_DeleteStatusById_StatusDeleted() {
        // Arrange
        Long statusId = 1L;

        Mockito.when(statusRepository.existsById(statusId))
                .thenReturn(true);

        // Act
        statusService.deleteStatusById(statusId);

        // Assert
        Mockito.verify(statusRepository).deleteById(statusId);

    }

    @Test
    public void StatusService_DeleteStatusById_ThrowsResourceNotFoundException() {
        // Arrange
        Long statusId = 2L;

        Mockito.when(statusRepository.existsById(statusId))
                .thenReturn(false);

        // Act
        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            statusService.deleteStatusById(statusId);
        });
    }
}
