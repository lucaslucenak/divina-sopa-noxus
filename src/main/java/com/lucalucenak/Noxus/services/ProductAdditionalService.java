package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.ProductAdditionalFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.ProductAdditionalModel;
import com.lucalucenak.Noxus.models.pks.ProductAdditionalPK;
import com.lucalucenak.Noxus.repositories.ProductAdditionaltRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductAdditionalService {

    @Autowired
    private ProductAdditionaltRepository productAdditionaltRepository;
    @Autowired
    private OrderService orderService;

    @Transactional
    public ProductAdditionalFullDto findProductAdditionalById(ProductAdditionalPK productAdditionalPK) {
        Optional<ProductAdditionalModel> productAdditionalModelOptional = productAdditionaltRepository.findById(productAdditionalPK);

        if (productAdditionalModelOptional.isPresent()) {
            return new ProductAdditionalFullDto(productAdditionalModelOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: ProductAdditional. Not found with order id: "+ productAdditionalPK.getAdditional().getId() + " and product id: " + productAdditionalPK.getProduct().getId());
        }
    }

    @Transactional
    public Page<ProductAdditionalFullDto> findAllProductAdditionalsPaginated(Pageable pageable) {
        Page<ProductAdditionalModel> pagedProductAdditionals = productAdditionaltRepository.findAll(pageable);
        return pagedProductAdditionals.map(ProductAdditionalFullDto::new);
    }

    @Transactional
    public ProductAdditionalFullDto saveProductAdditional(ProductAdditionalFullDto productAdditionalFullDto) {
        ProductAdditionalModel productAdditionalModel = new ProductAdditionalModel(productAdditionalFullDto);
        return new ProductAdditionalFullDto(productAdditionaltRepository.save(productAdditionalModel));
    }

    @Transactional
    public void deleteProductAdditionalByAdditionalId(Long additionalId) {
        if (productAdditionaltRepository.existsByIdAdditionalId(additionalId)) {
            productAdditionaltRepository.deleteByIdAdditionalId(additionalId);
        }
        else {
            throw new ResourceNotFoundException("Resource: ProductAdditional. Not found with order id: " + additionalId);
        }
    }
}
