package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.SizeFullDto;
import com.lucalucenak.Noxus.dtos.SizeFullDto;
import com.lucalucenak.Noxus.dtos.post.SizePostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.SizeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ProductService productService;

    @Transactional
    public SizeFullDto findSizeById(Long sizeId) {
        Optional<SizeModel> sizeOptional = sizeRepository.findById(sizeId);

        if (sizeOptional.isPresent()) {
            return new SizeFullDto(sizeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Size. Not found with id: " + sizeId);
        }
    }

    @Transactional
    public Page<SizeFullDto> findAllSizesPaginated(Pageable pageable) {
        Page<SizeModel> pagedSizes = sizeRepository.findAll(pageable);
        return pagedSizes.map(SizeFullDto::new);
    }

    @Transactional
    public SizeFullDto saveSize(SizePostDto sizePostDto) {
        SizeModel sizeModel = new SizeModel(sizePostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(sizePostDto.getStatusId()));
        sizeModel.setStatus(statusModel);
        return new SizeFullDto(sizeRepository.save(sizeModel));
    }

    @Transactional
    public SizeFullDto updateSize(Long sizeId, SizePostDto sizePostDto) {
        if (!sizeId.equals(sizePostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + sizeId + ", Body Id: " + sizePostDto.getId());
        }

        SizeModel existingSizeModel = new SizeModel(this.findSizeById(sizeId));
        SizeModel updatedSizeModel = new SizeModel(sizePostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(sizePostDto.getStatusId()));
        updatedSizeModel.setStatus(statusModel);

        if (statusModel.getStatus().equals("INACTIVE")) {
            productService.inactivateProductsBySizeId(sizeId);
        }

        updatedSizeModel.setCreatedAt(existingSizeModel.getCreatedAt());
        BeanUtils.copyProperties(updatedSizeModel, existingSizeModel, "createdAt, updatedAt");
        return new SizeFullDto(sizeRepository.save(existingSizeModel));
    }

    @Transactional
    public SizeFullDto inactivateSizeById(Long sizeId) {
        SizeModel sizeModel = new SizeModel(this.findSizeById(sizeId));
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

        sizeModel.setStatus(inactiveStatusModel);

        productService.inactivateProductsBySizeId(sizeId);
        return new SizeFullDto(sizeRepository.save(sizeModel));
    }

    public boolean existsById(Long sizeId) {
        return sizeRepository.existsById(sizeId);
    }

    @Transactional
    public void deleteSizeById(Long sizeId) {
        if (sizeRepository.existsById(sizeId)) {
            sizeRepository.deleteById(sizeId);
        } else {
            throw new ResourceNotFoundException("Resource: Size. Not found with id: " + sizeId);
        }
    }
}
