package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DeliveryTypeFullDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DeliveryTypeModel;
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
    public DeliveryTypeFullDto saveDeliveryType(DeliveryTypeFullDto deliveryTypeFullDto) {
        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(deliveryTypeFullDto);
        return new DeliveryTypeFullDto(deliveryTypeRepository.save(deliveryTypeModel));
    }

    @Transactional
    public DeliveryTypeFullDto updateDeliveryType(Long deliveryTypeId, DeliveryTypeFullDto deliveryTypeFullDto) {
        if (!deliveryTypeId.equals(deliveryTypeFullDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + deliveryTypeId + ", Body Id: " + deliveryTypeFullDto.getId());
        }

        DeliveryTypeModel existingDeliveryTypeModel = new DeliveryTypeModel(this.findDeliveryTypeById(deliveryTypeId));
        DeliveryTypeModel updatedDeliveryTypeModel = new DeliveryTypeModel(deliveryTypeFullDto);

        BeanUtils.copyProperties(existingDeliveryTypeModel, updatedDeliveryTypeModel);
        return new DeliveryTypeFullDto(deliveryTypeRepository.save(updatedDeliveryTypeModel));
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
