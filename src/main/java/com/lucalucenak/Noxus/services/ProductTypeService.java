package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.ProductTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.ProductTypePostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.ProductTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.ProductTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private StatusService statusService;

    @Transactional
    public ProductTypeFullDto findProductTypeById(Long productTypeId) {
        Optional<ProductTypeModel> productTypeOptional = productTypeRepository.findById(productTypeId);

        if (productTypeOptional.isPresent()) {
            return new ProductTypeFullDto(productTypeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: ProductType. Not found with id: " + productTypeId);
        }
    }

    @Transactional
    public ProductTypeFullDto findProductTypeByType(String type) {
        Optional<ProductTypeModel> productTypeOptional = productTypeRepository.findByType(type);

        if (productTypeOptional.isPresent()) {
            return new ProductTypeFullDto(productTypeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: ProductType. Not found with type: " + type);
        }
    }

    @Transactional
    public Page<ProductTypeFullDto> findAllProductTypesPaginated(Pageable pageable) {
        Page<ProductTypeModel> pagedProductType = productTypeRepository.findAll(pageable);
        return pagedProductType.map(ProductTypeFullDto::new);
    }

    @Transactional
    public ProductTypeFullDto saveProductType(ProductTypePostDto productTypePostDto) {
        ProductTypeModel productTypeModel = new ProductTypeModel(productTypePostDto);

        StatusModel statusModel = new StatusModel(statusService.findStatusById(productTypePostDto.getStatusId()));
        productTypeModel.setStatus(statusModel);

        return new ProductTypeFullDto(productTypeRepository.save(productTypeModel));
    }

    @Transactional
    public ProductTypeFullDto updateProductType(Long productTypeId, ProductTypePostDto productTypePostDto) {
        if (!productTypeId.equals(productTypePostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + productTypeId + ", Body Id: " + productTypePostDto.getId());
        }

        ProductTypeModel existingProductTypeModel = new ProductTypeModel(this.findProductTypeById(productTypeId));
        ProductTypeModel updatedProductTypeModel = new ProductTypeModel(productTypePostDto);

        StatusModel statusModel = new StatusModel(statusService.findStatusById(productTypePostDto.getId()));
        updatedProductTypeModel.setStatus(statusModel);

        updatedProductTypeModel.setCreatedAt(existingProductTypeModel.getCreatedAt());
        BeanUtils.copyProperties(updatedProductTypeModel, existingProductTypeModel);
        return new ProductTypeFullDto(productTypeRepository.save(updatedProductTypeModel));
    }

    @Transactional
    public ProductTypeFullDto inactivateProductTypeById(Long productTypeId) {
        if (productTypeRepository.existsById(productTypeId)) {
            ProductTypeModel productTypeModel = new ProductTypeModel(this.findProductTypeById(productTypeId));
            StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

            productTypeModel.setStatus(inactiveStatusModel);

            return new ProductTypeFullDto(productTypeRepository.save(productTypeModel));
        }
        else {
            throw new ResourceNotFoundException("Resource: ProductType. Not found with id: " + productTypeId);
        }
    }

    @Transactional
    public void deleteProductTypeById(Long productTypeId) {
        if (productTypeRepository.existsById(productTypeId)) {
            productTypeRepository.deleteById(productTypeId);
        } else {
            throw new ResourceNotFoundException("Resource: ProductType. Not found with id: " + productTypeId);
        }
    }
}
