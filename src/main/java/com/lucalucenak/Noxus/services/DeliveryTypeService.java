package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.dtos.DeliveryTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliveryTypePostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DeliveryModel;
import com.lucalucenak.Noxus.models.DeliveryTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.DeliveryTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public DeliveryTypeFullDto findDeliveryTypeById(Long deliveryTypeId) {
        Optional<DeliveryTypeModel> deliveryTypeOptional = deliveryTypeRepository.findById(deliveryTypeId);

        if (deliveryTypeOptional.isPresent()) {
            return new DeliveryTypeFullDto(deliveryTypeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: DeliveryType. Not found with id: " + deliveryTypeId);
        }
    }

    @Transactional(readOnly = true)
    public Page<DeliveryTypeFullDto> findAllDeliveryTypesPaginated(Pageable pageable) {
        Page<DeliveryTypeModel> pagedDeliveryTypes = deliveryTypeRepository.findAll(pageable);
        return pagedDeliveryTypes.map(DeliveryTypeFullDto::new);
    }

    @Transactional
    public DeliveryTypeFullDto saveDeliveryType(DeliveryTypePostDto deliveryTypePostDto) {
        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(deliveryTypePostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliveryTypePostDto.getStatusId()));
        deliveryTypeModel.setStatus(statusModel);

        return new DeliveryTypeFullDto(deliveryTypeRepository.save(deliveryTypeModel));
    }

    @Transactional
    public DeliveryTypeFullDto updateDeliveryType(Long deliveryTypeId, DeliveryTypePostDto deliveryTypePostDto) {
        if (!deliveryTypeId.equals(deliveryTypePostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + deliveryTypeId + ", Body Id: " + deliveryTypePostDto.getId());
        }

        DeliveryTypeModel existingDeliveryTypeModel = new DeliveryTypeModel(this.findDeliveryTypeById(deliveryTypeId));
        DeliveryTypeModel updatedDeliveryTypeModel = new DeliveryTypeModel(deliveryTypePostDto);

        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliveryTypePostDto.getStatusId()));
        updatedDeliveryTypeModel.setStatus(statusModel);

        updatedDeliveryTypeModel.setCreatedAt(existingDeliveryTypeModel.getCreatedAt());
        BeanUtils.copyProperties(existingDeliveryTypeModel, updatedDeliveryTypeModel);
        return new DeliveryTypeFullDto(deliveryTypeRepository.save(updatedDeliveryTypeModel));
    }

    @Transactional
    public DeliveryTypeFullDto inactivateDeliveryTypeById(Long deliveryTypeId) {
        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(this.findDeliveryTypeById(deliveryTypeId));
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

        deliveryTypeModel.setStatus(inactiveStatusModel);
        return new DeliveryTypeFullDto(deliveryTypeRepository.save(deliveryTypeModel));
    }

    @Transactional
    public void deleteDeliveryTypeById(Long deliveryTypeId) {
        if (deliveryTypeRepository.existsById(deliveryTypeId)) {
            deliveryTypeRepository.deleteById(deliveryTypeId);
        } else {
            throw new ResourceNotFoundException("Resource: DeliveryType. Not found with id: " + deliveryTypeId);
        }
    }
}
