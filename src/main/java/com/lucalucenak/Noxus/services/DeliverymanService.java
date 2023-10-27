package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DeliverymanFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliverymanPostDto;
import com.lucalucenak.Noxus.exceptions.AlreadyExistentDeliverymanException;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DeliverymanModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.DeliverymanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
public class DeliverymanService {

    @Autowired
    private DeliverymanRepository deliverymanRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public DeliverymanFullDto findDeliverymanById(Long deliverymanId) {
        Optional<DeliverymanModel> deliverymanOptional = deliverymanRepository.findById(deliverymanId);

        if (deliverymanOptional.isPresent()) {
            return new DeliverymanFullDto(deliverymanOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Deliveryman. Not found with id: " + deliverymanId);
        }
    }

    @Transactional(readOnly = true)
    public DeliverymanFullDto findDeliverymanByName(String deliverymanName) {
        Optional<DeliverymanModel> deliverymanOptional = deliverymanRepository.findByName(deliverymanName);

        if (deliverymanOptional.isPresent()) {
            return new DeliverymanFullDto(deliverymanOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Deliveryman. Not found with id: " + deliverymanName);
        }
    }

    @Transactional(readOnly = true)
    public Page<DeliverymanFullDto> findAllDeliverymansPaginated(Pageable pageable) {
        Page<DeliverymanModel> pagedDeliverymans = deliverymanRepository.findAll(pageable);
        return pagedDeliverymans.map(DeliverymanFullDto::new);
    }

    @Transactional
    public DeliverymanFullDto saveDeliveryman(DeliverymanPostDto deliverymanPostDto) {
        if (deliverymanRepository.existsByName(deliverymanPostDto.getName().toUpperCase(Locale.ROOT))) {
            DeliverymanModel existentDeliveryman = new DeliverymanModel(this.findDeliverymanByName(deliverymanPostDto.getName().toUpperCase(Locale.ROOT)));
            throw new AlreadyExistentDeliverymanException(deliverymanPostDto.getName() +  " is already mapped as an deliveryman with id: " + existentDeliveryman.getId());
        }

        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliverymanPostDto.getStatusId()));

        DeliverymanModel deliverymanModel = new DeliverymanModel(deliverymanPostDto);
        deliverymanModel.setStatus(statusModel);

        return new DeliverymanFullDto(deliverymanRepository.save(deliverymanModel));
    }

    @Transactional
    public DeliverymanFullDto updateDeliveryman(Long deliverymanId, DeliverymanPostDto deliverymanPostDto) {

        if (!deliverymanId.equals(deliverymanPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + deliverymanId + ", Body Id: " + deliverymanPostDto.getId());
        }

        DeliverymanModel existentDeliverymanModel = new DeliverymanModel(this.findDeliverymanById(deliverymanId));

        // Updating Deliveryman
        DeliverymanModel updatedDeliverymanModel = new DeliverymanModel(deliverymanPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliverymanPostDto.getStatusId()));
        updatedDeliverymanModel.setStatus(statusModel);

        updatedDeliverymanModel.setCreatedAt(existentDeliverymanModel.getCreatedAt());
        BeanUtils.copyProperties(updatedDeliverymanModel, existentDeliverymanModel, "createdAt, updatedAt");

        return new DeliverymanFullDto(deliverymanRepository.save(existentDeliverymanModel));
    }

    public void deleteDeliverymanById(Long deliverymanId) {
        if (deliverymanRepository.existsById(deliverymanId)) {
            deliverymanRepository.deleteById(deliverymanId);
        } else {
            throw new ResourceNotFoundException("Resource: Deliveryman. Not found with id: " + deliverymanId);
        }
    }

    @Transactional
    public DeliverymanFullDto inactivateDeliverymanById(Long deliverymanId) {
        DeliverymanModel deliverymanModel = new DeliverymanModel(this.findDeliverymanById(deliverymanId));
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

        deliverymanModel.setStatus(inactiveStatusModel);
        return new DeliverymanFullDto(deliverymanRepository.save(deliverymanModel));
    }
}
