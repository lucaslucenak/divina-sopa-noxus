package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.BusinessConfigurationFullDto;
import com.lucalucenak.Noxus.dtos.post.BusinessConfigurationPostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.BusinessConfigurationModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.BusinessConfigurationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BusinessConfigurationService {

    @Autowired
    private BusinessConfigurationRepository businessConfigurationRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public BusinessConfigurationFullDto findBusinessConfigurationById(Long businessConfigurationId) {
        Optional<BusinessConfigurationModel> businessConfigurationOptional = businessConfigurationRepository.findById(businessConfigurationId);

        if (businessConfigurationOptional.isPresent()) {
            return new BusinessConfigurationFullDto(businessConfigurationOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: BusinessConfiguration. Not found with id: " + businessConfigurationId);
        }
    }

    @Transactional(readOnly = true)
    public Page<BusinessConfigurationFullDto> findAllAdditionsPaginated(Pageable pageable) {
        Page<BusinessConfigurationModel> pagedAdditions = businessConfigurationRepository.findAll(pageable);
        return pagedAdditions.map(BusinessConfigurationFullDto::new);
    }

    @Transactional
    public BusinessConfigurationFullDto saveBusinessConfiguration(BusinessConfigurationPostDto businessConfigurationPostDto) {
        BusinessConfigurationModel businessConfigurationModel = new BusinessConfigurationModel(businessConfigurationPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(businessConfigurationPostDto.getStatusId()));
        businessConfigurationModel.setStatus(statusModel);

        return new BusinessConfigurationFullDto(businessConfigurationRepository.save(businessConfigurationModel));
    }

    public BusinessConfigurationFullDto updateBusinessConfiguration(Long businessConfigurationId, BusinessConfigurationPostDto businessConfigurationPostDto) {
        if (!businessConfigurationId.equals(businessConfigurationPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + businessConfigurationId + ", Body Id: " + businessConfigurationPostDto.getId());
        }

        BusinessConfigurationModel existingBusinessConfigurationModel = new BusinessConfigurationModel(this.findBusinessConfigurationById(businessConfigurationId));
        BusinessConfigurationModel updatedBusinessConfigurationModel = new BusinessConfigurationModel(businessConfigurationPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(businessConfigurationPostDto.getStatusId()));
        updatedBusinessConfigurationModel.setStatus(statusModel);

        updatedBusinessConfigurationModel.setCreatedAt(existingBusinessConfigurationModel.getCreatedAt());
        BeanUtils.copyProperties(updatedBusinessConfigurationModel, existingBusinessConfigurationModel);
        return new BusinessConfigurationFullDto(businessConfigurationRepository.save(updatedBusinessConfigurationModel));
    }

    @Transactional
    public void deleteBusinessConfigurationById(Long businessConfigurationId) {
        if (businessConfigurationRepository.existsById(businessConfigurationId)) {
            businessConfigurationRepository.deleteById(businessConfigurationId);
        } else {
            throw new ResourceNotFoundException("Resource: BusinessConfiguration. Not found with id: " + businessConfigurationId);
        }
    }

    @Transactional
    public BusinessConfigurationFullDto inactivateBusinessConfigurationById(Long businessConfigurationId) {
        BusinessConfigurationModel businessConfigurationModel = new BusinessConfigurationModel(this.findBusinessConfigurationById(businessConfigurationId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        businessConfigurationModel.setStatus(inactiveStatsModel);

        return new BusinessConfigurationFullDto(businessConfigurationRepository.save(businessConfigurationModel));
    }
}
