package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.CashRegisterBalanceFullDto;
import com.lucalucenak.Noxus.dtos.CashRegisterBalanceFullDto;
import com.lucalucenak.Noxus.dtos.StatusFullDto;
import com.lucalucenak.Noxus.dtos.post.CashRegisterBalancePostDto;
import com.lucalucenak.Noxus.exceptions.AlreadyExistentActiveCashRegisterBalanceException;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.CashRegisterBalanceModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.CashRegisterBalanceRepository;
import com.lucalucenak.Noxus.repositories.CashRegisterBalanceRepository;
import com.lucalucenak.Noxus.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CashRegisterBalanceService {

    @Autowired
    private CashRegisterBalanceRepository cashRegisterBalanceRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public CashRegisterBalanceFullDto findCashRegisterBalanceById(Long cashRegisterBalanceId) {
        Optional<CashRegisterBalanceModel> cashRegisterBalanceOptional = cashRegisterBalanceRepository.findById(cashRegisterBalanceId);

        if (cashRegisterBalanceOptional.isPresent()) {
            return new CashRegisterBalanceFullDto(cashRegisterBalanceOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: CashRegisterBalance. Not found with id: " + cashRegisterBalanceId);
        }
    }

    @Transactional(readOnly = true)
    public Page<CashRegisterBalanceFullDto> findAllCashRegisterBalancePaginated(Pageable pageable) {
        Page<CashRegisterBalanceModel> pagedAdditions = cashRegisterBalanceRepository.findAll(pageable);
        return pagedAdditions.map(CashRegisterBalanceFullDto::new);
    }

    @Transactional
    public CashRegisterBalanceFullDto saveCashRegisterBalance(CashRegisterBalancePostDto cashRegisterBalancePostDto) {

        if(existsActiveCashRegister()) {
            throw new AlreadyExistentActiveCashRegisterBalanceException("There is already an active CashRegisterBalance.");
        }

        CashRegisterBalanceModel cashRegisterBalanceModel = new CashRegisterBalanceModel(cashRegisterBalancePostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(cashRegisterBalancePostDto.getStatusId()));
        cashRegisterBalanceModel.setStatus(statusModel);

        return new CashRegisterBalanceFullDto(cashRegisterBalanceRepository.save(cashRegisterBalanceModel));
    }

    public CashRegisterBalanceFullDto closeCashRegister(Long id, Double registeredValue) {

        CashRegisterBalanceModel cashRegisterBalance = cashRegisterBalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CashRegisterBalance not found for the given ID."));

        cashRegisterBalance.setStatus(new StatusModel(statusService.findStatusByStatus("FINISHED")));

        LocalDateTime startOfDay = cashRegisterBalance.getCreatedAt().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        Double accountedValue = orderRepository.sumFinishedOrdersOfTheDay(startOfDay, endOfDay);

        cashRegisterBalance.setRegisteredValue(registeredValue);
        cashRegisterBalance.setAccountedValue(accountedValue);

        return new CashRegisterBalanceFullDto(cashRegisterBalanceRepository.save(cashRegisterBalance));
    }

    public CashRegisterBalanceFullDto updateCashRegisterBalance(Long cashRegisterBalanceId, CashRegisterBalancePostDto cashRegisterBalancePostDto) {
        if (!cashRegisterBalanceId.equals(cashRegisterBalancePostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + cashRegisterBalanceId + ", Body Id: " + cashRegisterBalancePostDto.getId());
        }

        CashRegisterBalanceModel existingCashRegisterBalanceModel = new CashRegisterBalanceModel(this.findCashRegisterBalanceById(cashRegisterBalanceId));
        CashRegisterBalanceModel updatedCashRegisterBalanceModel = new CashRegisterBalanceModel(cashRegisterBalancePostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(cashRegisterBalancePostDto.getStatusId()));
        updatedCashRegisterBalanceModel.setStatus(statusModel);

        updatedCashRegisterBalanceModel.setCreatedAt(existingCashRegisterBalanceModel.getCreatedAt());
        BeanUtils.copyProperties(updatedCashRegisterBalanceModel, existingCashRegisterBalanceModel);
        return new CashRegisterBalanceFullDto(cashRegisterBalanceRepository.save(updatedCashRegisterBalanceModel));
    }

    @Transactional
    public void deleteCashRegisterBalanceById(Long cashRegisterBalanceId) {
        if (cashRegisterBalanceRepository.existsById(cashRegisterBalanceId)) {
            cashRegisterBalanceRepository.deleteById(cashRegisterBalanceId);
        } else {
            throw new ResourceNotFoundException("Resource: CashRegisterBalance. Not found with id: " + cashRegisterBalanceId);
        }
    }

    @Transactional
    public CashRegisterBalanceFullDto inactivateCashRegisterBalanceById(Long cashRegisterBalanceId) {
        CashRegisterBalanceModel cashRegisterBalanceModel = new CashRegisterBalanceModel(this.findCashRegisterBalanceById(cashRegisterBalanceId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        cashRegisterBalanceModel.setStatus(inactiveStatsModel);

        return new CashRegisterBalanceFullDto(cashRegisterBalanceRepository.save(cashRegisterBalanceModel));
    }

    public boolean existsActiveCashRegister() {
        StatusFullDto activeStatusDto = statusService.findStatusByStatus("ACTIVE");
        return cashRegisterBalanceRepository.existsByStatusId(activeStatusDto.getId());
    }


}
