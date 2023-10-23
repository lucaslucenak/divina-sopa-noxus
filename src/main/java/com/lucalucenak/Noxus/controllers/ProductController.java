package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.ProductFullDto;
import com.lucalucenak.Noxus.dtos.post.ProductPostDto;
import com.lucalucenak.Noxus.dtos.response.ProductReturnDto;
import com.lucalucenak.Noxus.services.ProductService;
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
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductReturnDto> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok().body(new ProductReturnDto(productService.findProductById(productId)));
    }

    @GetMapping
    public ResponseEntity<Page<ProductReturnDto>> getAllProducts(Pageable pageable) {
        List<ProductReturnDto> productReturnDtos = new ArrayList<>();
        for (ProductFullDto i : productService.findAllProductsPaginated(pageable)) {
            productReturnDtos.add(new ProductReturnDto(i));
        }
        Page<ProductReturnDto> productReturnDtoPage = new PageImpl<>(productReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), productReturnDtos.size());
        return ResponseEntity.ok().body(productReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<ProductReturnDto> saveProduct(@RequestBody @Valid ProductPostDto productPostDto) {
        return ResponseEntity.ok().body(new ProductReturnDto(productService.saveProduct(productPostDto)));
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductReturnDto> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductPostDto productPostDto) {
        return ResponseEntity.ok().body(new ProductReturnDto(productService.updateProduct(productId, productPostDto)));
    }

    @PostMapping(value = "/inactivate/{productId}")
    public ResponseEntity<ProductReturnDto> inactivateProductById(@PathVariable Long productId) {
        return ResponseEntity.ok().body(new ProductReturnDto(productService.inactivateProductById(productId)));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<ProductReturnDto> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
