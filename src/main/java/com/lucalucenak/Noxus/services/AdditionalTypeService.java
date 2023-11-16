package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AdditionalTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalTypePostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.AdditionalTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class AdditionalTypeService {

    @Autowired
    private AdditionalTypeRepository additionalTypeRepository;
    @Autowired
    private StatusService statusService;

    @Transactional
    public AdditionalTypeFullDto findAdditionalTypeById(Long additionalTypeId) {
        Optional<AdditionalTypeModel> additionalTypeOptional = additionalTypeRepository.findById(additionalTypeId);

        if (additionalTypeOptional.isPresent()) {
            return new AdditionalTypeFullDto(additionalTypeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: AdditionalType. Not found with id: " + additionalTypeId);
        }
    }

    @Transactional
    public AdditionalTypeFullDto findAdditionalTypeByType(String type) {
        Optional<AdditionalTypeModel> additionalTypeOptional = additionalTypeRepository.findByType(type);

        if (additionalTypeOptional.isPresent()) {
            return new AdditionalTypeFullDto(additionalTypeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: AdditionalType. Not found with type: " + type);
        }
    }

    @Transactional
    public Page<AdditionalTypeFullDto> findAllAdditionalTypesPaginated(Pageable pageable) {
        Page<AdditionalTypeModel> pagedAdditionalType = additionalTypeRepository.findAll(pageable);
        return pagedAdditionalType.map(AdditionalTypeFullDto::new);
    }

    @Transactional
    public AdditionalTypeFullDto saveAdditionalType(AdditionalTypePostDto additionalTypePostDto) {
        AdditionalTypeModel additionalTypeModel = new AdditionalTypeModel(additionalTypePostDto);

        StatusModel statusModel = new StatusModel(statusService.findStatusById(additionalTypePostDto.getStatusId()));
        additionalTypeModel.setStatus(statusModel);

        return new AdditionalTypeFullDto(additionalTypeRepository.save(additionalTypeModel));
    }

    @Transactional
    public AdditionalTypeFullDto updateAdditionalType(Long additionalTypeId, AdditionalTypePostDto additionalTypePostDto) {
        if (!additionalTypeId.equals(additionalTypePostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + additionalTypeId + ", Body Id: " + additionalTypePostDto.getId());
        }

        AdditionalTypeModel existingAdditionalTypeModel = new AdditionalTypeModel(this.findAdditionalTypeById(additionalTypeId));
        AdditionalTypeModel updatedAdditionalTypeModel = new AdditionalTypeModel(additionalTypePostDto);

        StatusModel statusModel = new StatusModel(statusService.findStatusById(additionalTypePostDto.getId()));
        updatedAdditionalTypeModel.setStatus(statusModel);

        updatedAdditionalTypeModel.setCreatedAt(existingAdditionalTypeModel.getCreatedAt());
        updatedAdditionalTypeModel.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(updatedAdditionalTypeModel, existingAdditionalTypeModel);
        return new AdditionalTypeFullDto(additionalTypeRepository.save(updatedAdditionalTypeModel));
    }

    @Transactional
    public AdditionalTypeFullDto inactivateAdditionalTypeById(Long additionalTypeId) {
        if (additionalTypeRepository.existsById(additionalTypeId)) {
            AdditionalTypeModel additionalTypeModel = new AdditionalTypeModel(this.findAdditionalTypeById(additionalTypeId));
            StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

            additionalTypeModel.setStatus(inactiveStatusModel);

            return new AdditionalTypeFullDto(additionalTypeRepository.save(additionalTypeModel));
        }
        else {
            throw new ResourceNotFoundException("Resource: AdditionalType. Not found with id: " + additionalTypeId);
        }
    }

    @Transactional
    public void deleteAdditionalTypeById(Long additionalTypeId) {
        if (additionalTypeRepository.existsById(additionalTypeId)) {
            additionalTypeRepository.deleteById(additionalTypeId);
        } else {
            throw new ResourceNotFoundException("Resource: AdditionalType. Not found with id: " + additionalTypeId);
        }
    }
}
