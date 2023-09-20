package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
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

    @Transactional
    public AddressReturnDto findAddressById(Long addressId) {
        Optional<AddressModel> addressOptional = addressRepository.findById(addressId);

        if (addressOptional.isPresent()) {
            return new AddressReturnDto(addressOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + addressId);
        }
    }

    @Transactional
    public Page<AddressReturnDto> findAllAddressesPaginated(Pageable pageable) {
        Page<AddressModel> pagedAddresses = addressRepository.findAll(pageable);

        List<AddressReturnDto> addressReturnDtos = new ArrayList<>();
        for (AddressModel i : pagedAddresses) {
            addressReturnDtos.add(new AddressReturnDto(i));
        }

        Page<AddressReturnDto> addressReturnDtoPage = new PageImpl<>(addressReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), addressReturnDtos.size());
        return addressReturnDtoPage;
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
        AddressModel existentAddressModel = new AddressModel(this.findAddressById(addressId));

        // Updating Address
        AddressModel updatedAddressModel = new AddressModel(addressPostDto);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(addressPostDto.getClientAccountId()));
        updatedAddressModel.setClientAccount(clientAccountModel);
        BeanUtils.copyProperties(existentAddressModel, updatedAddressModel);

        addressRepository.save(updatedAddressModel);

        AddressReturnDto addressReturnDto = new AddressReturnDto(updatedAddressModel);
        return addressReturnDto;
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
