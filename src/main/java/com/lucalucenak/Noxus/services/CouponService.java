package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.CouponFullDto;
import com.lucalucenak.Noxus.dtos.post.CouponPostDto;
import com.lucalucenak.Noxus.exceptions.IncompatibleIdsException;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.CouponModel;
import com.lucalucenak.Noxus.models.StatusModel;
import com.lucalucenak.Noxus.repositories.CouponRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private StatusService statusService;

    @Transactional(readOnly = true)
    public CouponFullDto findCouponById(Long couponId) {
        Optional<CouponModel> couponOptional = couponRepository.findById(couponId);

        if (couponOptional.isPresent()) {
            return new CouponFullDto(couponOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Coupon. Not found with id: " + couponId);
        }
    }

    @Transactional(readOnly = true)
    public Page<CouponFullDto> findAllAdditionsPaginated(Pageable pageable) {
        Page<CouponModel> pagedAdditions = couponRepository.findAll(pageable);
        return pagedAdditions.map(CouponFullDto::new);
    }

    @Transactional
    public CouponFullDto saveCoupon(CouponPostDto couponPostDto) {
        CouponModel couponModel = new CouponModel(couponPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(couponPostDto.getStatusId()));
        couponModel.setStatus(statusModel);

        return new CouponFullDto(couponRepository.save(couponModel));
    }

    public CouponFullDto updateCoupon(Long couponId, CouponPostDto couponPostDto) {
        if (!couponId.equals(couponPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + couponId + ", Body Id: " + couponPostDto.getId());
        }

        CouponModel existingCouponModel = new CouponModel(this.findCouponById(couponId));
        CouponModel updatedCouponModel = new CouponModel(couponPostDto);
        StatusModel statusModel = new StatusModel(statusService.findStatusById(couponPostDto.getStatusId()));
        updatedCouponModel.setStatus(statusModel);

        updatedCouponModel.setCreatedAt(existingCouponModel.getCreatedAt());
        updatedCouponModel.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(updatedCouponModel, existingCouponModel);
        return new CouponFullDto(couponRepository.save(updatedCouponModel));
    }

    @Transactional
    public void deleteCouponById(Long couponId) {
        if (couponRepository.existsById(couponId)) {
            couponRepository.deleteById(couponId);
        } else {
            throw new ResourceNotFoundException("Resource: Coupon. Not found with id: " + couponId);
        }
    }

    @Transactional
    public CouponFullDto inactivateCouponById(Long couponId) {
        CouponModel couponModel = new CouponModel(this.findCouponById(couponId));
        StatusModel inactiveStatsModel = new StatusModel(statusService.findStatusByStatus("INACTIVE"));
        couponModel.setStatus(inactiveStatsModel);

        return new CouponFullDto(couponRepository.save(couponModel));
    }
}
