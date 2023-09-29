package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.StatusModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    public void StatusRepository_FindByStatus_ReturnExistentStatus() {

        // Arrange
        StatusModel statusModelActive = StatusModel.builder().status("ACTIVE_TEST").build();
        StatusModel statusModelInactive = StatusModel.builder().status("INACTIVE_TEST").build();

        // Act
        StatusModel savedStatusActive = statusRepository.save(statusModelActive);
        StatusModel savedStatusInactive = statusRepository.save(statusModelInactive);

        StatusModel foundStatusActive = statusRepository.findByStatus("ACTIVE_TEST").get();
        StatusModel foundStatusInactive = statusRepository.findByStatus("INACTIVE_TEST").get();

        // Assert
        Assertions.assertEquals(savedStatusActive, foundStatusActive);
        Assertions.assertEquals(savedStatusInactive, foundStatusInactive);

    }
}
