package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.ProductFullDto;
import com.lucalucenak.Noxus.dtos.post.ProductPostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.ProductModel;
import com.lucalucenak.Noxus.models.ProductTypeModel;
import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private SizeService sizeService;

    @Transactional(readOnly = true)
    public ProductFullDto findProductById(Long productId) {
        Optional<ProductModel> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            return new ProductFullDto(productOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Product. Not found with id: " + productId);
        }
    }
    @Transactional(readOnly = true)
    public Page<ProductFullDto> findAllProductsPaginated(Pageable pageable) {
        Page<ProductModel> pagedProducts = productRepository.findAll(pageable);
        return pagedProducts.map(ProductFullDto::new);
    }

    @Transactional(readOnly = true)
    public Page<ProductFullDto> findAllActiveProductsPaginated(Pageable pageable) {
        Long activeStatusId = 1L;
        Page<ProductModel> pagedProducts = productRepository.findByStatusId(activeStatusId, pageable);
        return pagedProducts.map(ProductFullDto::new);
    }

    @Transactional
    public ProductFullDto saveProduct(ProductPostDto productPostDto) {
        ProductModel productModel = new ProductModel(productPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(productPostDto.getStatusId()));
        productModel.setStatus(statusModel);
        ProductTypeModel productTypeModel = new ProductTypeModel(productTypeService.findProductTypeById(productPostDto.getProductTypeId()));
        productModel.setProductType(productTypeModel);
        SizeModel sizeModel = new SizeModel(sizeService.findSizeById(productPostDto.getSizeId()));
        productModel.setSize(sizeModel);

        return new ProductFullDto(productRepository.save(productModel));
    }

    public ProductFullDto updateProduct(Long productId, ProductPostDto productPostDto) {
        if (!productId.equals(productPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + productId + ", Body Id: " + productPostDto.getId());
        }

        ProductModel existingProductModel = new ProductModel(this.findProductById(productId));
        ProductModel updatedProductModel = new ProductModel(productPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(productPostDto.getStatusId()));
        updatedProductModel.setStatus(statusModel);
        ProductTypeModel productTypeModel = new ProductTypeModel(productTypeService.findProductTypeById(productPostDto.getProductTypeId()));
        updatedProductModel.setProductType(productTypeModel);
        SizeModel sizeModel = new SizeModel(sizeService.findSizeById(productPostDto.getSizeId()));
        updatedProductModel.setSize(sizeModel);

        updatedProductModel.setCreatedAt(existingProductModel.getCreatedAt());
        BeanUtils.copyProperties(updatedProductModel, existingProductModel);
        return new ProductFullDto(productRepository.save(updatedProductModel));
    }

    @Transactional
    public void deleteProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new ResourceNotFoundException("Resource: Product. Not found with id: " + productId);
        }
    }

    @Transactional
    public ProductFullDto inactivateProductById(Long productId) {
        ProductModel productModel = new ProductModel(this.findProductById(productId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        productModel.setStatus(inactiveStatsModel);

        return new ProductFullDto(productRepository.save(productModel));
    }

    @Transactional
    public List<ProductFullDto> inactivateProductsBySizeId(Long sizeId) {
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));

        List<ProductFullDto> updatedProducts = new ArrayList<>();
        for (Optional<ProductModel> i : productRepository.findBySizeId(sizeId)) {
            ProductModel productModel = i.get();
            productModel.setStatus(inactiveStatusModel);
            updatedProducts.add(new ProductFullDto(productRepository.save(productModel)));
        }
        return updatedProducts;
    }
}