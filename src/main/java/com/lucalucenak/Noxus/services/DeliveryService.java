package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliveryPostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DeliveryModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.DeliveryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public DeliveryFullDto findDeliveryById(Long deliveryId) {
        Optional<DeliveryModel> deliveryOptional = deliveryRepository.findById(deliveryId);

        if (deliveryOptional.isPresent()) {
            return new DeliveryFullDto(deliveryOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Delivery. Not found with id: " + deliveryId);
        }
    }

    @Transactional(readOnly = true)
    public Page<DeliveryFullDto> findAllDeliverysPaginated(Pageable pageable) {
        Page<DeliveryModel> pagedDeliverys = deliveryRepository.findAll(pageable);
        return pagedDeliverys.map(DeliveryFullDto::new);
    }

    @Transactional
    public DeliveryFullDto saveDelivery(DeliveryPostDto deliveryPostDto) {
        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliveryPostDto.getStatusId()));

        DeliveryModel deliveryModel = new DeliveryModel(deliveryPostDto);
        deliveryModel.setStatus(statusModel);

        return new DeliveryFullDto(deliveryRepository.save(deliveryModel));
    }

    @Transactional
    public DeliveryFullDto updateDelivery(Long deliveryId, DeliveryPostDto deliveryPostDto) {

        if (!deliveryId.equals(deliveryPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + deliveryId + ", Body Id: " + deliveryPostDto.getId());
        }

        DeliveryModel existentDeliveryModel = new DeliveryModel(this.findDeliveryById(deliveryId));

        // Updating Delivery
        DeliveryModel updatedDeliveryModel = new DeliveryModel(deliveryPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliveryPostDto.getStatusId()));
        updatedDeliveryModel.setStatus(statusModel);
        BeanUtils.copyProperties(updatedDeliveryModel, existentDeliveryModel, "createdAt, updatedAt");

        return new DeliveryFullDto(deliveryRepository.save(existentDeliveryModel));
    }

    public void deleteDeliveryById(Long deliveryId) {
        if (deliveryRepository.existsById(deliveryId)) {
            deliveryRepository.deleteById(deliveryId);
        } else {
            throw new ResourceNotFoundException("Resource: Delivery. Not found with id: " + deliveryId);
        }
    }

    @Transactional
    public DeliveryFullDto inactivateDeliveryById(Long deliveryId) {
        DeliveryModel deliveryModel = new DeliveryModel(this.findDeliveryById(deliveryId));
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

        deliveryModel.setStatus(inactiveStatusModel);
        return new DeliveryFullDto(deliveryRepository.save(deliveryModel));
    }
}
