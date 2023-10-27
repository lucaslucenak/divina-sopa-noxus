package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.StatusFullDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.StatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Transactional
    public StatusFullDto findStatusById(Long statusId) {
        Optional<StatusModel> statusOptional = statusRepository.findById(statusId);

        if (statusOptional.isPresent()) {
            return new StatusFullDto(statusOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Status. Not found with id: " + statusId);
        }
    }

    @Transactional
    public StatusFullDto findStatusByStatus(String status) {
        Optional<StatusModel> statusOptional = statusRepository.findByStatus(status);

        if (statusOptional.isPresent()) {
            return new StatusFullDto(statusOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Status. Not found with status: " + status);
        }
    }

    @Transactional
    public Page<StatusFullDto> findAllStatusPaginated(Pageable pageable) {
        Page<StatusModel> pagedStatus = statusRepository.findAll(pageable);
        return pagedStatus.map(StatusFullDto::new);
    }

    @Transactional
    public StatusFullDto saveStatus(StatusFullDto statusFullDto) {
        StatusModel statusModel = new StatusModel(statusFullDto);
        return new StatusFullDto(statusRepository.save(statusModel));
    }

    @Transactional
    public StatusFullDto updateStatus(Long statusId, StatusFullDto statusFullDto) {
        if (!statusId.equals(statusFullDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + statusId + ", Body Id: " + statusFullDto.getId());
        }

        StatusModel existingStatusModel = new StatusModel(this.findStatusById(statusId));
        StatusModel updatedStatusModel = new StatusModel(statusFullDto);

        updatedStatusModel.setCreatedAt(existingStatusModel.getCreatedAt());
        BeanUtils.copyProperties(existingStatusModel, updatedStatusModel);
        return new StatusFullDto(statusRepository.save(updatedStatusModel));
    }

    @Transactional
    public void deleteStatusById(Long statusId) {
        if (statusRepository.existsById(statusId)) {
            statusRepository.deleteById(statusId);
        } else {
            throw new ResourceNotFoundException("Resource: Status. Not found with id: " + statusId);
        }
    }
}
