package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.dtos.post.ClientAccountPostDto;
import com.lucalucenak.Noxus.dtos.response.ClientAccountReturnDto;
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

    @Transactional(readOnly = true)
    public ClientAccountReturnDto findClientAccountById(Long clientAccountId) {
        Optional<ClientAccountModel> clientAccountOptional = clientAccountRepository.findById(clientAccountId);

        if (clientAccountOptional.isPresent()) {
            return new ClientAccountReturnDto(clientAccountOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Client. Not found with id: " + clientAccountId);
        }
    }

    @Transactional(readOnly = true)
    public Page<ClientAccountReturnDto> findAllClientAccountsPaginated(Pageable pageable) {
        Page<ClientAccountModel> pagedClientAccounts = clientAccountRepository.findAll(pageable);

        List<ClientAccountReturnDto> clientAccountReturnDtos = new ArrayList<>();
        for (ClientAccountModel i : pagedClientAccounts) {
            clientAccountReturnDtos.add(new ClientAccountReturnDto(i));
        }

        Page<ClientAccountReturnDto> clientAccountReturnDtoPage = new PageImpl<>(clientAccountReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), clientAccountReturnDtos.size());
        return clientAccountReturnDtoPage;
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
        ClientAccountModel existentClientAccountModel = new ClientAccountModel(this.findClientAccountById(clientAccountId));

        // Updating ClientAccount
        ClientAccountModel updatedClientAccountModel = new ClientAccountModel(clientAccountPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(clientAccountPostDto.getStatusId()));
        updatedClientAccountModel.setStatus(statusModel);
        updatedClientAccountModel.setPlacedOrdersQuantity(existentClientAccountModel.getPlacedOrdersQuantity());
        BeanUtils.copyProperties(updatedClientAccountModel, existentClientAccountModel, "id");

        clientAccountRepository.save(existentClientAccountModel);

        ClientAccountReturnDto clientAccountReturnDto = new ClientAccountReturnDto(existentClientAccountModel);
        return clientAccountReturnDto;
    }

    @Transactional
    public void deleteClientAccountById(Long clientAccountId) {
        System.out.println(clientAccountId);
        if (clientAccountRepository.existsById(clientAccountId)) {
            clientAccountRepository.deleteById(clientAccountId);
        } else {
            throw new ResourceNotFoundException("Resource: ClientAccount. Not found with id: " + clientAccountId);
        }
    }

    public ClientAccountReturnDto increasePlacedOrdersQuantityByClientAccountId(Long clientAccountId) {
        ClientAccountModel clientAccountModel = new ClientAccountModel(this.findClientAccountById(clientAccountId));
        clientAccountModel.setPlacedOrdersQuantity(clientAccountModel.getPlacedOrdersQuantity() + 1);
        clientAccountRepository.save(clientAccountModel);

        return new ClientAccountReturnDto(clientAccountModel);
    }
}
