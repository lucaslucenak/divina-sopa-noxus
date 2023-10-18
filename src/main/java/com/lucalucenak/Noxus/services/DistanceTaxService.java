package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.DistanceTaxFullDto;
import com.lucalucenak.Noxus.dtos.post.DistanceTaxPostDto;
import com.lucalucenak.Noxus.exceptions.AlreadyDefinedDistanceTaxMetricException;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.DistanceTaxModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.DistanceTaxRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DistanceTaxService {

    @Autowired
    private DistanceTaxRepository distanceTaxRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public DistanceTaxFullDto findDistanceTaxById(Long distanceTaxId) {
        Optional<DistanceTaxModel> distanceTaxOptional = distanceTaxRepository.findById(distanceTaxId);

        if (distanceTaxOptional.isPresent()) {
            return new DistanceTaxFullDto(distanceTaxOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: DistanceTax. Not found with id: " + distanceTaxId);
        }
    }

    @Transactional(readOnly = true)
    public DistanceTaxFullDto findDistanceTaxTopByFinalDistance() {
        Optional<DistanceTaxModel> distanceTaxOptional = distanceTaxRepository.findTopByFinalDistance();

        if (distanceTaxOptional.isPresent()) {
            return new DistanceTaxFullDto(distanceTaxOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: DistanceTax.");
        }
    }

    @Transactional(readOnly = true)
    public Page<DistanceTaxFullDto> findAllDistanceTaxesPaginated(Pageable pageable) {
        Page<DistanceTaxModel> pagedDistanceTaxes = distanceTaxRepository.findAll(pageable);
        return pagedDistanceTaxes.map(DistanceTaxFullDto::new);
    }

    @Transactional
    public DistanceTaxFullDto saveDistanceTax(DistanceTaxPostDto distanceTaxPostDto) {
        if (distanceTaxRepository.existsByInitialDistanceOrFinalDistance(distanceTaxPostDto.getInitialDistance(), distanceTaxPostDto.getFinalDistance())) {
            throw new AlreadyDefinedDistanceTaxMetricException("Metrics already used, please check it out.");
        }
        DistanceTaxModel topDistanceTax = new DistanceTaxModel(this.findDistanceTaxTopByFinalDistance());
        if (distanceTaxPostDto.getInitialDistance() < topDistanceTax.getFinalDistance()) {
            throw new AlreadyDefinedDistanceTaxMetricException("The initialDistance must be greater than " + topDistanceTax.getFinalDistance() + ". Distance Tax with id: " + topDistanceTax.getId() + " have the final tax equal to " + topDistanceTax.getFinalDistance());
        }
        if (distanceTaxPostDto.getInitialDistance() > distanceTaxPostDto.getFinalDistance()) {
            throw new AlreadyDefinedDistanceTaxMetricException("The finalDistance must be greater than " + distanceTaxPostDto.getInitialDistance());
        }

        StatusModel statusModel = new StatusModel(statusService.findStatusById(distanceTaxPostDto.getStatusId()));

        DistanceTaxModel distanceTaxModel = new DistanceTaxModel(distanceTaxPostDto);
        distanceTaxModel.setStatus(statusModel);

        return new DistanceTaxFullDto(distanceTaxRepository.save(distanceTaxModel));
    }

    @Transactional
    public DistanceTaxFullDto updateDistanceTax(Long distanceTaxId, DistanceTaxPostDto distanceTaxPostDto) {
        if (!distanceTaxId.equals(distanceTaxPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + distanceTaxId + ", Body Id: " + distanceTaxPostDto.getId());
        }

        DistanceTaxModel existentDistanceTaxModel = new DistanceTaxModel(this.findDistanceTaxById(distanceTaxId));

        // Updating DistanceTax
        DistanceTaxModel updatedDistanceTaxModel = new DistanceTaxModel(distanceTaxPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(distanceTaxPostDto.getStatusId()));
        updatedDistanceTaxModel.setStatus(statusModel);
        BeanUtils.copyProperties(updatedDistanceTaxModel, existentDistanceTaxModel, "createdAt, updatedAt");

        return new DistanceTaxFullDto(distanceTaxRepository.save(existentDistanceTaxModel));
    }

    public void deleteDistanceTaxById(Long distanceTaxId) {
        if (distanceTaxRepository.existsById(distanceTaxId)) {
            distanceTaxRepository.deleteById(distanceTaxId);
        } else {
            throw new ResourceNotFoundException("Resource: DistanceTax. Not found with id: " + distanceTaxId);
        }
    }

    @Transactional
    public DistanceTaxFullDto inactivateDistanceTaxById(Long distanceTaxId) {
        DistanceTaxModel distanceTaxModel = new DistanceTaxModel(this.findDistanceTaxById(distanceTaxId));
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

        distanceTaxModel.setStatus(inactiveStatusModel);
        return new DistanceTaxFullDto(distanceTaxRepository.save(distanceTaxModel));
    }
}
