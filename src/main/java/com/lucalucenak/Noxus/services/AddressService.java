package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import com.lucalucenak.Noxus.repositories.AddressRepository;
import com.lucalucenak.Noxus.repositories.ClientAccountRepository;
import com.lucalucenak.Noxus.repositories.NeighbourhoodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientAccountService clientAccountService;
    @Autowired
    private NeighbourhoodService neighbourhoodService;

    @Transactional(readOnly = true)
    public AddressFullDto findAddressById(Long addressId) {
        Optional<AddressModel> addressOptional = addressRepository.findById(addressId);

        if (addressOptional.isPresent()) {
            return new AddressFullDto(addressOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + addressId);
        }
    }

    @Transactional(readOnly = true)
    public Page<AddressFullDto> findAllAddressesPaginated(Pageable pageable) {
        Page<AddressModel> pagedAddresses = addressRepository.findAll(pageable);
        return pagedAddresses.map(AddressFullDto::new);
    }

    @Transactional
    public AddressReturnDto saveAddress(AddressPostDto addressPostDto) {
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(addressPostDto.getClientAccountId()));
        NeighbourhoodModel neighbourhoodModel = new NeighbourhoodModel(neighbourhoodService.findNeighbourhoodById(addressPostDto.getNeighbourhoodId()));

        AddressModel addressModel = new AddressModel(addressPostDto);
        addressModel.setClientAccount(clientAccountModel);
        addressModel.setNeighbourhood(neighbourhoodModel);

        addressRepository.save(addressModel);

        AddressReturnDto addressReturnDto = new AddressReturnDto(addressModel);
        return addressReturnDto;
    }

    @Transactional
    public AddressReturnDto updateAddress(Long addressId, AddressPostDto addressPostDto) {

        if (!addressId.equals(addressPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + addressId + ", Body Id: " + addressPostDto.getId());
        }

        AddressModel existentAddressModel = new AddressModel(this.findAddressById(addressId));

        // Updating Address
        AddressModel updatedAddressModel = new AddressModel(addressPostDto);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(addressPostDto.getClientAccountId()));
        updatedAddressModel.setClientAccount(clientAccountModel);
        NeighbourhoodModel neighbourhoodModel = new NeighbourhoodModel(neighbourhoodService.findNeighbourhoodById(addressPostDto.getNeighbourhoodId()));
        updatedAddressModel.setNeighbourhood(neighbourhoodModel);
        BeanUtils.copyProperties(updatedAddressModel, existentAddressModel, "createdAt, updatedAt");

        addressRepository.save(existentAddressModel);

        AddressReturnDto addressReturnDto = new AddressReturnDto(existentAddressModel);
        return addressReturnDto;
    }

    public void deleteAddressById(Long addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + addressId);
        }
    }
}
