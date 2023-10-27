package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.repositories.AdditionalRepository;
import com.lucalucenak.Noxus.repositories.AdditionalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdditionalService {

    @Autowired
    private AdditionalRepository additionalRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public AdditionalFullDto findAdditionalById(Long additionalId) {
        Optional<AdditionalModel> additionalOptional = additionalRepository.findById(additionalId);

        if (additionalOptional.isPresent()) {
            return new AdditionalFullDto(additionalOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Additional. Not found with id: " + additionalId);
        }
    }

    @Transactional(readOnly = true)
    public Page<AdditionalFullDto> findAllAdditionsPaginated(Pageable pageable) {
        Page<AdditionalModel> pagedAdditions = additionalRepository.findAll(pageable);
        return pagedAdditions.map(AdditionalFullDto::new);
    }

    @Transactional
    public AdditionalFullDto saveAdditional(AdditionalPostDto additionalPostDto) {
        AdditionalModel additionalModel = new AdditionalModel(additionalPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(additionalPostDto.getStatusId()));
        additionalModel.setStatus(statusModel);

        return new AdditionalFullDto(additionalRepository.save(additionalModel));
    }

    public AdditionalFullDto updateAdditional(Long additionalId, AdditionalPostDto additionalPostDto) {
        if (!additionalId.equals(additionalPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + additionalId + ", Body Id: " + additionalPostDto.getId());
        }

        AdditionalModel existingAdditionalModel = new AdditionalModel(this.findAdditionalById(additionalId));
        AdditionalModel updatedAdditionalModel = new AdditionalModel(additionalPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(additionalPostDto.getStatusId()));
        updatedAdditionalModel.setStatus(statusModel);

        updatedAdditionalModel.setCreatedAt(existingAdditionalModel.getCreatedAt());
        BeanUtils.copyProperties(updatedAdditionalModel, existingAdditionalModel);
        return new AdditionalFullDto(additionalRepository.save(updatedAdditionalModel));
    }

    @Transactional
    public void deleteAdditionalById(Long additionalId) {
        if (additionalRepository.existsById(additionalId)) {
            additionalRepository.deleteById(additionalId);
        } else {
            throw new ResourceNotFoundException("Resource: Additional. Not found with id: " + additionalId);
        }
    }

    @Transactional
    public AdditionalFullDto inactivateAdditionalById(Long additionalId) {
        AdditionalModel additionalModel = new AdditionalModel(this.findAdditionalById(additionalId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        additionalModel.setStatus(inactiveStatsModel);

        return new AdditionalFullDto(additionalRepository.save(additionalModel));
    }
}
