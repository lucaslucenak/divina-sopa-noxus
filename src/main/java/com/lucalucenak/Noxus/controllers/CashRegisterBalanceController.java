package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.CashRegisterBalanceFullDto;
import com.lucalucenak.Noxus.dtos.post.CashRegisterBalancePostDto;
import com.lucalucenak.Noxus.dtos.response.CashRegisterBalanceReturnDto;
import com.lucalucenak.Noxus.services.CashRegisterBalanceService;
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
@RequestMapping(value = "/cash-register-balance")
public class CashRegisterBalanceController {

    @Autowired
    CashRegisterBalanceService cashRegisterBalanceService;

    @GetMapping(value = "/{cashRegisterBalanceId}")
    public ResponseEntity<CashRegisterBalanceReturnDto> getCashRegisterBalanceById(@PathVariable Long cashRegisterBalanceId) {
        return ResponseEntity.ok().body(new CashRegisterBalanceReturnDto(cashRegisterBalanceService.findCashRegisterBalanceById(cashRegisterBalanceId)));
    }

    @GetMapping
    public ResponseEntity<Page<CashRegisterBalanceReturnDto>> getAllCashRegisterBalances(Pageable pageable) {
        List<CashRegisterBalanceReturnDto> cashRegisterBalanceReturnDtos = new ArrayList<>();
        for (CashRegisterBalanceFullDto i : cashRegisterBalanceService.findAllCashRegisterBalancePaginated(pageable)) {
            cashRegisterBalanceReturnDtos.add(new CashRegisterBalanceReturnDto(i));
        }
        Page<CashRegisterBalanceReturnDto> cashRegisterBalanceReturnDtoPage = new PageImpl<>(cashRegisterBalanceReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), cashRegisterBalanceReturnDtos.size());
        return ResponseEntity.ok().body(cashRegisterBalanceReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<CashRegisterBalanceReturnDto> saveCashRegisterBalance(@RequestBody @Valid CashRegisterBalancePostDto cashRegisterBalancePostDto) {
        return ResponseEntity.ok().body(new CashRegisterBalanceReturnDto(cashRegisterBalanceService.saveCashRegisterBalance(cashRegisterBalancePostDto)));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<CashRegisterBalanceReturnDto> closeCashRegister(@PathVariable Long id, @RequestBody CashRegisterBalancePostDto cashRegisterBalancePostDto) {
        Double registeredValue = cashRegisterBalancePostDto.getRegisteredValue();
        CashRegisterBalanceFullDto closedCashRegister = cashRegisterBalanceService.closeCashRegister(id, registeredValue);
        return ResponseEntity.ok().body(new CashRegisterBalanceReturnDto(closedCashRegister));
    }

    @PutMapping(value = "/{cashRegisterBalanceId}")
    public ResponseEntity<CashRegisterBalanceReturnDto> updateCashRegisterBalance(@PathVariable Long cashRegisterBalanceId, @RequestBody @Valid CashRegisterBalancePostDto cashRegisterBalancePostDto) {
        return ResponseEntity.ok().body(new CashRegisterBalanceReturnDto(cashRegisterBalanceService.updateCashRegisterBalance(cashRegisterBalanceId, cashRegisterBalancePostDto)));
    }

    @PostMapping(value = "/inactivate/{cashRegisterBalanceId}")
    public ResponseEntity<CashRegisterBalanceReturnDto> inactivateCashRegisterBalanceById(@PathVariable Long cashRegisterBalanceId) {
        return ResponseEntity.ok().body(new CashRegisterBalanceReturnDto(cashRegisterBalanceService.inactivateCashRegisterBalanceById(cashRegisterBalanceId)));
    }

    @DeleteMapping(value = "/{cashRegisterBalanceId}")
    public ResponseEntity<CashRegisterBalanceReturnDto> deleteCashRegisterBalanceById(@PathVariable Long cashRegisterBalanceId) {
        cashRegisterBalanceService.deleteCashRegisterBalanceById(cashRegisterBalanceId);
        return ResponseEntity.noContent().build();
    }
}
