package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.dtos.NeighbourhoodFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import com.lucalucenak.Noxus.repositories.ClientAccountRepository;
import com.lucalucenak.Noxus.repositories.NeighbourhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
