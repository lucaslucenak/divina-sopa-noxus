package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.PaymentMethodFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.PaymentMethodModel;
import com.lucalucenak.Noxus.repositories.PaymentMethodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethodFullDto findPaymentMethodById(Long paymentMethodId) {
        Optional<PaymentMethodModel> paymentMethodOptional = paymentMethodRepository.findById(paymentMethodId);

        if (paymentMethodOptional.isPresent()) {
            return new PaymentMethodFullDto(paymentMethodOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: PaymentMethod. Not found with id: " + paymentMethodId);
        }
    }

    @Transactional
    public Page<PaymentMethodFullDto> findAllPaymentMethodPaginated(Pageable pageable) {
        Page<PaymentMethodModel> pagedPaymentMethod = paymentMethodRepository.findAll(pageable);
        return pagedPaymentMethod.map(PaymentMethodFullDto::new);
    }

    @Transactional
    public PaymentMethodFullDto savePaymentMethod(PaymentMethodFullDto paymentMethodFullDto) {
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel(paymentMethodFullDto);
        return new PaymentMethodFullDto(paymentMethodRepository.save(paymentMethodModel));
    }

    @Transactional
    public PaymentMethodFullDto updatePaymentMethod(Long paymentMethodId, PaymentMethodFullDto paymentMethodFullDto) {
        PaymentMethodModel existingPaymentMethodModel = new PaymentMethodModel(this.findPaymentMethodById(paymentMethodId));
        PaymentMethodModel updatedPaymentMethodModel = new PaymentMethodModel(paymentMethodFullDto);

        BeanUtils.copyProperties(existingPaymentMethodModel, updatedPaymentMethodModel);
        return new PaymentMethodFullDto(paymentMethodRepository.save(updatedPaymentMethodModel));
    }

    @Transactional
    public void deletePaymentMethodById(Long paymentMethodId) {
        if (paymentMethodRepository.existsById(paymentMethodId)) {
            paymentMethodRepository.deleteById(paymentMethodId);
        } else {
            throw new ResourceNotFoundException("Resource: PaymentMethod. Not found with id: " + paymentMethodId);
        }
    }
}
