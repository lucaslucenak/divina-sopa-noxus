package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.repositories.ClientAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientAccountService {

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Transactional
    public ClientAccountFullDto findClientAccountById(Long clientAccountId) {
        Optional<ClientAccountModel> clientAccountOptional = clientAccountRepository.findById(clientAccountId);

        if (clientAccountOptional.isPresent()) {
            return new ClientAccountFullDto(clientAccountOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Client. Not found with id: " + clientAccountId);
        }
    }
}
