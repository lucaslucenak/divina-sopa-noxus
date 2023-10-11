package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.dtos.post.ClientAccountPostDto;
import com.lucalucenak.Noxus.dtos.response.ClientAccountReturnDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.ClientAccountRepository;
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
public class ClientAccountService {

    @Autowired
    private ClientAccountRepository clientAccountRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private AddressService addressService;

    @Transactional(readOnly = true)
    public ClientAccountFullDto findClientAccountById(Long clientAccountId) {
        Optional<ClientAccountModel> clientAccountOptional = clientAccountRepository.findById(clientAccountId);

        if (clientAccountOptional.isPresent()) {
            return new ClientAccountFullDto(clientAccountOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Client. Not found with id: " + clientAccountId);
        }
    }

    @Transactional(readOnly = true)
    public Page<ClientAccountFullDto> findAllClientAccountsPaginated(Pageable pageable) {
        Page<ClientAccountModel> pagedClientAccounts = clientAccountRepository.findAll(pageable);

        return pagedClientAccounts.map(ClientAccountFullDto::new);
    }

    @Transactional
    public ClientAccountReturnDto saveClientAccount(ClientAccountPostDto clientAccountPostDto) {
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(clientAccountPostDto.getStatusId()));
        clientAccountModel.setStatus(statusModel);
        clientAccountModel.setPlacedOrdersQuantity(0);

        clientAccountRepository.save(clientAccountModel);

        ClientAccountReturnDto clientAccountReturnDto = new ClientAccountReturnDto(clientAccountModel);
        return clientAccountReturnDto;
    }

    @Transactional
    public ClientAccountReturnDto updateClientAccount(Long clientAccountId, ClientAccountPostDto clientAccountPostDto) {
        if (!clientAccountId.equals(clientAccountPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + clientAccountId + ", Body Id: " + clientAccountPostDto.getId());
        }

        ClientAccountModel existentClientAccountModel = new ClientAccountModel(this.findClientAccountById(clientAccountId));

        // Updating ClientAccount
        ClientAccountModel updatedClientAccountModel = new ClientAccountModel(clientAccountPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(clientAccountPostDto.getStatusId()));
        updatedClientAccountModel.setStatus(statusModel);
        updatedClientAccountModel.setPlacedOrdersQuantity(existentClientAccountModel.getPlacedOrdersQuantity());

        BeanUtils.copyProperties(updatedClientAccountModel, existentClientAccountModel, "updatedAt, createdAt");

        clientAccountRepository.save(existentClientAccountModel);

        // Inactivate All Referent Addresses
        if (existentClientAccountModel.getStatus().getStatus().equals("INACTIVE")) {
            addressService.inactivateAddressesByClientAccountId(clientAccountId);
        }

        ClientAccountReturnDto clientAccountReturnDto = new ClientAccountReturnDto(existentClientAccountModel);
        return clientAccountReturnDto;
    }

    @Transactional
    public void deleteClientAccountById(Long clientAccountId) {
        if (clientAccountRepository.existsById(clientAccountId)) {
            clientAccountRepository.deleteById(clientAccountId);
        } else {
            throw new ResourceNotFoundException("Resource: ClientAccount. Not found with id: " + clientAccountId);
        }
    }

    public boolean existsById(Long clientAccountId) {
            return clientAccountRepository.existsById(clientAccountId);
    }

    @Transactional
    public ClientAccountReturnDto increasePlacedOrdersQuantityByClientAccountId(Long clientAccountId) {
        ClientAccountModel clientAccountModel = new ClientAccountModel(this.findClientAccountById(clientAccountId));
        clientAccountModel.setPlacedOrdersQuantity(clientAccountModel.getPlacedOrdersQuantity() + 1);
        clientAccountRepository.save(clientAccountModel);

        return new ClientAccountReturnDto(clientAccountModel);
    }

    @Transactional
    public ClientAccountFullDto inactivateClientAccountById(Long clientAccountId) {
        ClientAccountModel clientAccountModel = new ClientAccountModel(this.findClientAccountById(clientAccountId));
        StatusModel inactiveStatusModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        clientAccountModel.setStatus(inactiveStatusModel);

        return new ClientAccountFullDto(clientAccountRepository.save(clientAccountModel));
    }
}
