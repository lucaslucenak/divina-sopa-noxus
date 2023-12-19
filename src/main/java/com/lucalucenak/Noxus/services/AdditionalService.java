package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.OrderProductFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import com.lucalucenak.Noxus.models.pks.ProductAdditionalPK;
import com.lucalucenak.Noxus.repositories.AdditionalRepository;
import com.lucalucenak.Noxus.repositories.AdditionalRepository;
import com.lucalucenak.Noxus.repositories.ProductAdditionaltRepository;
import com.lucalucenak.Noxus.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdditionalService {

    @Autowired
    private AdditionalRepository additionalRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private AdditionalTypeService additionalTypeService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductAdditionaltRepository productAdditionalRepository;


    @Transactional(readOnly = true)
    public AdditionalFullDto findAdditionalById(Long additionalId) {
        Optional<AdditionalModel> additionalOptional = additionalRepository.findById(additionalId);

        if (additionalOptional.isPresent()) {
            return new AdditionalFullDto(additionalOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Additional. Not found with id: " + additionalId);
        }
    }

    @Transactional(readOnly = true)
    public Page<AdditionalFullDto> findAllAdditionsPaginated(Pageable pageable) {
        Page<AdditionalModel> pagedAdditions = additionalRepository.findAll(pageable);
        return pagedAdditions.map(AdditionalFullDto::new);
    }

    @Transactional
    public AdditionalFullDto saveAdditional(AdditionalPostDto additionalPostDto) {
        AdditionalModel additionalModel = new AdditionalModel(additionalPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(additionalPostDto.getStatusId()));
        additionalModel.setStatus(statusModel);
        AdditionalTypeModel additionalTypeModel = new AdditionalTypeModel(additionalTypeService.findAdditionalTypeById(additionalPostDto.getAdditionalTypeId()));
        additionalModel.setAdditionalType(additionalTypeModel);

        additionalModel = additionalRepository.save(additionalModel);

        for (Long productId : additionalPostDto.getProductIds()) {
            ProductModel product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));
            ProductAdditionalPK productAdditionalPK = new ProductAdditionalPK(product, additionalModel);
            ProductAdditionalModel productAdditionalModel = new ProductAdditionalModel(productAdditionalPK, LocalDateTime.now(), LocalDateTime.now());
            productAdditionalRepository.save(productAdditionalModel);
        }

        return new AdditionalFullDto(additionalModel);
    }


    @Transactional
    public AdditionalFullDto updateAdditional(Long additionalId, AdditionalPostDto additionalPostDto) {
        if (!additionalId.equals(additionalPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + additionalId + ", Body Id: " + additionalPostDto.getId());
        }

        AdditionalModel existingAdditionalModel = new AdditionalModel(this.findAdditionalById(additionalId));
        AdditionalModel updatedAdditionalModel = new AdditionalModel(additionalPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(additionalPostDto.getStatusId()));
        updatedAdditionalModel.setStatus(statusModel);
        AdditionalTypeModel additionalTypeModel = new AdditionalTypeModel(additionalTypeService.findAdditionalTypeById(additionalPostDto.getAdditionalTypeId()));
        updatedAdditionalModel.setAdditionalType(additionalTypeModel);

        updatedAdditionalModel.setCreatedAt(existingAdditionalModel.getCreatedAt());
        BeanUtils.copyProperties(updatedAdditionalModel, existingAdditionalModel);

        // Salvar existingAdditionalModel para persistir as atualizações
        existingAdditionalModel = additionalRepository.save(updatedAdditionalModel);

        // Excluir as associações existentes e recriá-las
        productAdditionalRepository.deleteByIdAdditionalId(additionalId);
        for (Long productId : additionalPostDto.getProductIds()) {
            ProductModel product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));
            ProductAdditionalPK productAdditionalPK = new ProductAdditionalPK(product, existingAdditionalModel);
            ProductAdditionalModel productAdditionalModel = new ProductAdditionalModel(productAdditionalPK, LocalDateTime.now(), LocalDateTime.now());
            productAdditionalRepository.save(productAdditionalModel);
        }

        return new AdditionalFullDto(existingAdditionalModel);
    }

    @Transactional
    public void deleteAdditionalById(Long additionalId) {
        if (additionalRepository.existsById(additionalId)) {
            additionalRepository.deleteById(additionalId);
        } else {
            throw new ResourceNotFoundException("Resource: Additional. Not found with id: " + additionalId);
        }
    }

    @Transactional
    public AdditionalFullDto inactivateAdditionalById(Long additionalId) {
        AdditionalModel additionalModel = new AdditionalModel(this.findAdditionalById(additionalId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        additionalModel.setStatus(inactiveStatsModel);

        return new AdditionalFullDto(additionalRepository.save(additionalModel));
    }
}
