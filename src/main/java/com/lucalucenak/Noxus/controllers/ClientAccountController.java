package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.post.ClientAccountPostDto;
import com.lucalucenak.Noxus.dtos.response.ClientAccountReturnDto;
import com.lucalucenak.Noxus.services.ClientAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client-account")
public class ClientAccountController {

    @Autowired
    ClientAccountService clientAccountService;

    @GetMapping(value = "/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> getClientAccountById(@PathVariable Long clientAccountId) {
        return ResponseEntity.ok().body(clientAccountService.findClientAccountById(clientAccountId));
    }

    @GetMapping
    public ResponseEntity<Page<ClientAccountReturnDto>> getAllClientAccounts(Pageable pageable) {
        return ResponseEntity.ok().body(clientAccountService.findAllClientAccountsPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<ClientAccountReturnDto> saveClientAccount(@RequestBody ClientAccountPostDto clientAccountPostDto) {
        return ResponseEntity.ok().body(clientAccountService.saveClientAccount(clientAccountPostDto));
    }

    @PutMapping(value = "/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> updateClientAccount(@PathVariable Long clientAccountId, @RequestBody ClientAccountPostDto clientAccountPostDto) {
        return ResponseEntity.ok().body(clientAccountService.updateClientAccount(clientAccountId, clientAccountPostDto));
    }

    @DeleteMapping(value = "/{clientAccountId}")
    public ResponseEntity<ClientAccountReturnDto> deleteClientAccountById(@PathVariable Long clientAccountId) {
        clientAccountService.deleteClientAccountById(clientAccountId);
        return ResponseEntity.noContent().build();
    }
}
