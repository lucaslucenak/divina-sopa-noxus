package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import com.lucalucenak.Noxus.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/{addressId}")
    public ResponseEntity<AddressReturnDto> getAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok().body(addressService.findAddressById(addressId));
    }

    @GetMapping
    public ResponseEntity<Page<AddressReturnDto>> getAllAddresses(Pageable pageable) {
        return ResponseEntity.ok().body(addressService.findAllAddressesPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<AddressReturnDto> saveAddress(@RequestBody AddressPostDto addressPostDto) {
        return ResponseEntity.ok().body(addressService.saveAddress(addressPostDto));
    }

    @PutMapping(value = "/{addressId}")
    public ResponseEntity<AddressReturnDto> updateAddress(@PathVariable Long addressId, @RequestBody AddressPostDto addressPostDto) {
        return ResponseEntity.ok().body(addressService.updateAddress(addressId, addressPostDto));
    }

    @DeleteMapping(value = "/{addressId}")
    public ResponseEntity<AddressReturnDto> deleteAddressById(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.noContent().build();
    }
}
