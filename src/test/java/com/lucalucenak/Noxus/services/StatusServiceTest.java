package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.StatusFullDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
}
