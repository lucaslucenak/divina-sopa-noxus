package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.SoupFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.SoupModel;
import com.lucalucenak.Noxus.repositories.SoupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SoupService {
    @Autowired
    private SoupRepository soupRepository;

    @Transactional
    public SoupFullDto findSoupById(Long soupId) {
        Optional<SoupModel> soupOptional = soupRepository.findById(soupId);

        if (soupOptional.isPresent()) {
            return new SoupFullDto(soupOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Soup. Not found with id: " + soupId);
        }
    }

    @Transactional
    public Page<SoupFullDto> findAllSoupsPaginated(Pageable pageable) {
        Page<SoupModel> pagedSoups = soupRepository.findAll(pageable);
        return pagedSoups.map(SoupFullDto::new);
    }

    @Transactional
    public SoupFullDto saveSoup(SoupFullDto soupFullDto) {
        SoupModel soupModel = new SoupModel(soupFullDto);
        return new SoupFullDto(soupRepository.save(soupModel));
    }

    public SoupFullDto updateSoup(Long soupId, SoupFullDto soupFullDto) {
        SoupModel existingSoupModel = new SoupModel(this.findSoupById(soupId));
        SoupModel updatedSoupModel = new SoupModel(soupFullDto);

        BeanUtils.copyProperties(existingSoupModel, updatedSoupModel);
        return new SoupFullDto(soupRepository.save(updatedSoupModel));
    }

    @Transactional
    public void deleteSoupById(Long soupId) {
        if (soupRepository.existsById(soupId)) {
            soupRepository.deleteById(soupId);
        } else {
            throw new ResourceNotFoundException("Resource: Soup. Not found with id: " + soupId);
        }
    }
}
