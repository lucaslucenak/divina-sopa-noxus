package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.dtos.post.ClientAccountPostDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import com.lucalucenak.Noxus.dtos.response.ClientAccountReturnDto;
import com.lucalucenak.Noxus.services.ClientAccountService;
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
@RequestMapping(value = "/client-account")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientAccountController {

    @Autowired
    ClientAccountService clientAccountService;

    @GetMapping(value = "/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> getClientAccountById(@PathVariable Long clientAccountId) {
        return ResponseEntity.ok().body(new ClientAccountReturnDto(clientAccountService.findClientAccountById(clientAccountId)));
    }

    @GetMapping
    public ResponseEntity<Page<ClientAccountReturnDto>> getAllClientAccounts(Pageable pageable) {
        List<ClientAccountReturnDto> clientAccountReturnDtos = new ArrayList<>();
        for (ClientAccountFullDto i : clientAccountService.findAllClientAccountsPaginated(pageable)) {
            clientAccountReturnDtos.add(new ClientAccountReturnDto(i));
        }
        Page<ClientAccountReturnDto> clientAccountReturnDtoPage = new PageImpl<>(clientAccountReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), clientAccountReturnDtos.size());
        return ResponseEntity.ok().body(clientAccountReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<ClientAccountReturnDto> saveClientAccount(@RequestBody @Valid ClientAccountPostDto clientAccountPostDto) {
        return ResponseEntity.ok().body(new ClientAccountReturnDto(clientAccountService.saveClientAccount(clientAccountPostDto)));
    }

    @PutMapping(value = "/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> updateClientAccount(@PathVariable Long clientAccountId, @RequestBody @Valid ClientAccountPostDto clientAccountPostDto) {
        return ResponseEntity.ok().body(new ClientAccountReturnDto(clientAccountService.updateClientAccount(clientAccountId, clientAccountPostDto)));
    }

    @PostMapping(value = "/inactivate/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> inactivateClientAccount(@PathVariable Long clientAccountId) {
        return ResponseEntity.ok().body(new ClientAccountReturnDto(clientAccountService.inactivateClientAccountById(clientAccountId)));
    }

    @PostMapping(value = "/increase-placed-orders/cpf/{clientAccountCpf}")
    public ResponseEntity<ClientAccountReturnDto> increasePlacedOrdersQuantityByClientAccountCpf(@PathVariable String clientAccountCpf) {
        return ResponseEntity.ok().body(new ClientAccountReturnDto(clientAccountService.increasePlacedOrdersQuantityByClientAccountCpf(clientAccountCpf)));
    }

    @DeleteMapping(value = "/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> deleteClientAccountById(@PathVariable Long clientAccountId) {
        clientAccountService.deleteClientAccountById(clientAccountId);
        return ResponseEntity.noContent().build();
    }
}
