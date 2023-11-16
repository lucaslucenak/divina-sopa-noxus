package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.ProductTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.ProductTypePostDto;
import com.lucalucenak.Noxus.dtos.response.ProductTypeReturnDto;
import com.lucalucenak.Noxus.services.ProductTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product-type")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping(value = "/{productTypeId}")
    public ResponseEntity<ProductTypeReturnDto> getProductTypeById(@PathVariable Long productTypeId) {
        return ResponseEntity.ok().body(new ProductTypeReturnDto(productTypeService.findProductTypeById(productTypeId)));
    }

    @GetMapping
    public ResponseEntity<Page<ProductTypeReturnDto>> getAllProductTypes(Pageable pageable) {
        List<ProductTypeReturnDto> productTypeReturnDtos = new ArrayList<>();
        for (ProductTypeFullDto i : productTypeService.findAllProductTypesPaginated(pageable)) {
            productTypeReturnDtos.add(new ProductTypeReturnDto(i));
        }
        Page<ProductTypeReturnDto> productTypeReturnDtoPage = new PageImpl<>(productTypeReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), productTypeReturnDtos.size());
        return ResponseEntity.ok().body(productTypeReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<ProductTypeReturnDto> saveProductType(@RequestBody @Valid ProductTypePostDto productTypePostDto) {
        return ResponseEntity.ok().body(new ProductTypeReturnDto(productTypeService.saveProductType(productTypePostDto)));
    }

    @PutMapping(value = "/{productTypeId}")
    public ResponseEntity<ProductTypeReturnDto> updateProductType(@PathVariable Long productTypeId, @RequestBody @Valid ProductTypePostDto productTypePostDto) {
        return ResponseEntity.ok().body(new ProductTypeReturnDto(productTypeService.updateProductType(productTypeId, productTypePostDto)));
    }

    @PostMapping(value = "/inactivate/{productTypeId}")
    public ResponseEntity<ProductTypeReturnDto> inactivateProductTypeById(@PathVariable Long productTypeId) {
        return ResponseEntity.ok().body(new ProductTypeReturnDto(productTypeService.inactivateProductTypeById(productTypeId)));
    }

    @DeleteMapping(value = "/{productTypeId}")
    public ResponseEntity<ProductTypeReturnDto> deleteProductTypeById(@PathVariable Long productTypeId) {
        productTypeService.deleteProductTypeById(productTypeId);
        return ResponseEntity.noContent().build();
    }
}
