package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import com.lucalucenak.Noxus.dtos.response.DrinkReturnDto;
import com.lucalucenak.Noxus.services.AddressService;
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
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/{addressId}")
    public ResponseEntity<AddressReturnDto> getAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok().body(new AddressReturnDto(addressService.findAddressById(addressId)));
    }

    @GetMapping
    public ResponseEntity<Page<AddressReturnDto>> getAllAddresses(Pageable pageable) {
        List<AddressReturnDto> addressReturnDtos = new ArrayList<>();
        for (AddressFullDto i : addressService.findAllAddressesPaginated(pageable)) {
            addressReturnDtos.add(new AddressReturnDto(i));
        }
        Page<AddressReturnDto> addressReturnDtoPage = new PageImpl<>(addressReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), addressReturnDtos.size());
        return ResponseEntity.ok().body(addressReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<AddressReturnDto> saveAddress(@RequestBody @Valid AddressPostDto addressPostDto) {
        return ResponseEntity.ok().body(addressService.saveAddress(addressPostDto));
    }

    @PutMapping(value = "/{addressId}")
    public ResponseEntity<AddressReturnDto> updateAddress(@PathVariable Long addressId, @RequestBody @Valid AddressPostDto addressPostDto) {
        return ResponseEntity.ok().body(addressService.updateAddress(addressId, addressPostDto));
    }

    @PostMapping(value = "/inactivate/{addressId}")
    public ResponseEntity<AddressReturnDto> inactivateAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok().body(new AddressReturnDto(addressService.inactivateAddressById(addressId)));
    }

    @DeleteMapping(value = "/{addressId}")
    public ResponseEntity<AddressReturnDto> deleteAddressById(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.noContent().build();
    }
}
