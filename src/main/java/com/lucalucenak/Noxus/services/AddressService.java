package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientAccountService clientAccountService;
    @Autowired
    private NeighbourhoodService neighbourhoodService;

    @Transactional
    public AddressFullDto findAddressById(Long addressId) {
        Optional<AddressModel> addressOptional = addressRepository.findById(addressId);

        if (addressOptional.isPresent()) {
            return new AddressFullDto(addressOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + addressId);
        }
    }

    @Transactional
    public Page<AddressFullDto> findAllAddressesPaginated(Pageable pageable) {
        Page<AddressModel> pagedAddresses = addressRepository.findAll(pageable);
        return pagedAddresses.map(AddressFullDto::new);
    }

    @Transactional
    public AddressFullDto saveAddress(AddressPostDto addressPostDto) {
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(addressPostDto.getClientAccountId()));
        NeighbourhoodModel neighbourhoodModel = new NeighbourhoodModel(neighbourhoodService.findNeighbourhoodById(addressPostDto.getNeighbourhoodId()));

        AddressModel addressModel = new AddressModel(addressPostDto);
        addressModel.setClientAccount(clientAccountModel);
        addressModel.setNeighbourhood(neighbourhoodModel);

        return new AddressFullDto(addressRepository.save(addressModel));
    }

    @Transactional
    public AddressFullDto updateAddress(Long addressId, AddressPostDto addressPostDto) {
        AddressModel existentAddressModel = new AddressModel(this.findAddressById(addressId));

        // Updating Address
        AddressModel updatedAddressModel = new AddressModel(addressPostDto);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(addressPostDto.getClientAccountId()));
        updatedAddressModel.setClientAccount(clientAccountModel);
        BeanUtils.copyProperties(existentAddressModel, updatedAddressModel);
        return new AddressFullDto(addressRepository.save(updatedAddressModel));
    }

    @Transactional
    public void deleteAddressById(Long addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + addressId);
        }
    }
}
