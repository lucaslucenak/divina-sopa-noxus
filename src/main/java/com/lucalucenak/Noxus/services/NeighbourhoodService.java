package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.NeighbourhoodFullDto;
import com.lucalucenak.Noxus.dtos.post.NeighbourhoodPostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import com.lucalucenak.Noxus.repositories.NeighbourhoodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NeighbourhoodService {

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    @Transactional
    public NeighbourhoodFullDto findNeighbourhoodById(Long neighbourhoodId) {
        Optional<NeighbourhoodModel> neighbourhoodOptional = neighbourhoodRepository.findById(neighbourhoodId);

        if (neighbourhoodOptional.isPresent()) {
            return new NeighbourhoodFullDto(neighbourhoodOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Neighbourhood. Not found with id: " + neighbourhoodId);
        }
    }

    @Transactional
    public Page<NeighbourhoodFullDto> findAllNeighbourhoodsPaginated(Pageable pageable) {
        Page<NeighbourhoodModel> pagedNeighbourhoods = neighbourhoodRepository.findAll(pageable);
        return pagedNeighbourhoods.map(NeighbourhoodFullDto::new);
    }

    @Transactional
    public NeighbourhoodFullDto saveNeighbourhood(NeighbourhoodPostDto neighbourhoodPostDto) {
        NeighbourhoodModel neighbourhoodModel = new NeighbourhoodModel(neighbourhoodPostDto);
        return new NeighbourhoodFullDto(neighbourhoodRepository.save(neighbourhoodModel));
    }

    @Transactional
    public NeighbourhoodFullDto updateNeighbourhood(Long neighbourhoodId, NeighbourhoodPostDto neighbourhoodPostDto) {
        if (!neighbourhoodId.equals(neighbourhoodPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + neighbourhoodId + ", Body Id: " + neighbourhoodPostDto.getId());
        }

        NeighbourhoodModel existingNeighbourhoodModel = new NeighbourhoodModel(this.findNeighbourhoodById(neighbourhoodId));
        NeighbourhoodModel updatedNeighbourhoodModel = new NeighbourhoodModel(neighbourhoodPostDto);

        BeanUtils.copyProperties(updatedNeighbourhoodModel, existingNeighbourhoodModel, "createdAt, updatedAt");
        return new NeighbourhoodFullDto(neighbourhoodRepository.save(existingNeighbourhoodModel));
    }

    @Transactional
    public void deleteNeighbourhoodById(Long neighbourhoodId) {
        if (neighbourhoodRepository.existsById(neighbourhoodId)) {
            neighbourhoodRepository.deleteById(neighbourhoodId);
        } else {
            throw new ResourceNotFoundException("Resource: Neighbourhood. Not found with id: " + neighbourhoodId);
        }
    }
}
