package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliveryPostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.NotRecognizedDeliveryTypeException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.*;
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
    @Autowired
    private DeliveryTypeService deliveryTypeService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DeliverymanService deliverymanService;
    @Autowired
    private DistanceTaxService distanceTaxService;


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
    public Page<DeliveryFullDto> findAllDeliveriesPaginated(Pageable pageable) {
        Page<DeliveryModel> pagedDeliveries = deliveryRepository.findAll(pageable);
        return pagedDeliveries.map(DeliveryFullDto::new);
    }

    @Transactional
    public DeliveryFullDto saveDelivery(DeliveryPostDto deliveryPostDto) throws Exception {
        DeliveryModel deliveryModel = new DeliveryModel(deliveryPostDto);

        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(deliveryTypeService.findDeliveryTypeById(deliveryPostDto.getDeliveryTypeId()));
        deliveryModel.setDeliveryType(deliveryTypeModel);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(deliveryPostDto.getStatusId()));
        deliveryModel.setStatus(statusModel);

        if (deliveryTypeModel.getDeliveryType().equals("WITHDRAWAL")) {
            return new DeliveryFullDto(deliveryRepository.save(deliveryModel));
        }

        else if (deliveryTypeModel.getDeliveryType().equals("DELIVERY")) {
            AddressModel addressModel = new AddressModel(addressService.findAddressById(deliveryPostDto.getAddressId()));
            deliveryModel.setAddress(addressModel);
            DeliverymanModel deliverymanModel = new DeliverymanModel(deliverymanService.findDeliverymanById(deliveryPostDto.getDeliverymanId()));
            deliveryModel.setDeliveryman(deliverymanModel);

            // Delivery Tax by neighbourhood
            if (deliveryPostDto.getDistanceTaxId() == null) {
                Double deliveryTax = addressModel.getNeighbourhood().getDeliveryTax();
                deliveryModel.setTax(deliveryTax);
            }

            // By distance tax
            else {
                DistanceTaxModel distanceTaxModel = new DistanceTaxModel(distanceTaxService.findDistanceTaxById(deliveryPostDto.getDistanceTaxId()));
                deliveryModel.setDistanceTax(distanceTaxModel);
                Double deliveryTax = distanceTaxModel.getTax();
                deliveryModel.setTax(deliveryTax);
            }

            return new DeliveryFullDto(deliveryRepository.save(deliveryModel));
        }
        else {
            throw new NotRecognizedDeliveryTypeException("Delivery type must be DELIVERY or WITHDRAWAL");
        }
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
        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(deliveryTypeService.findDeliveryTypeById(deliveryPostDto.getDeliveryTypeId()));
        updatedDeliveryModel.setDeliveryType(deliveryTypeModel);

        if (deliveryTypeModel.getDeliveryType().equals("WITHDRAWAL")) {
            BeanUtils.copyProperties(updatedDeliveryModel, existentDeliveryModel, "createdAt, updatedAt");
            return new DeliveryFullDto(deliveryRepository.save(updatedDeliveryModel));
        }

        else if (deliveryTypeModel.getDeliveryType().equals("DELIVERY")) {
            AddressModel addressModel = new AddressModel(addressService.findAddressById(deliveryPostDto.getAddressId()));
            updatedDeliveryModel.setAddress(addressModel);
            DeliverymanModel deliverymanModel = new DeliverymanModel(deliverymanService.findDeliverymanById(deliveryPostDto.getDeliverymanId()));
            updatedDeliveryModel.setDeliveryman(deliverymanModel);

            // Delivery Tax by neighbourhood
            if (deliveryPostDto.getDistanceTaxId() == null) {
                Double deliveryTax = addressModel.getNeighbourhood().getDeliveryTax();
                updatedDeliveryModel.setTax(deliveryTax);
            }

            // By distance tax
            else {
                DistanceTaxModel distanceTaxModel = new DistanceTaxModel(distanceTaxService.findDistanceTaxById(deliveryPostDto.getDistanceTaxId()));
                updatedDeliveryModel.setDistanceTax(distanceTaxModel);
                Double deliveryTax = distanceTaxModel.getTax();
                updatedDeliveryModel.setTax(deliveryTax);
            }

            BeanUtils.copyProperties(updatedDeliveryModel, existentDeliveryModel, "createdAt, updatedAt");
            return new DeliveryFullDto(deliveryRepository.save(updatedDeliveryModel));
        }
        else {
            throw new NotRecognizedDeliveryTypeException("Delivery type must be DELIVERY or WITHDRAWAL");
        }
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
