package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.CouponFullDto;
import com.lucalucenak.Noxus.dtos.post.CouponPostDto;
import com.lucalucenak.Noxus.dtos.response.CouponReturnDto;
import com.lucalucenak.Noxus.services.CouponService;
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
@RequestMapping(value = "/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping(value = "/{couponId}")
    public ResponseEntity<CouponReturnDto> getCouponById(@PathVariable Long couponId) {
        return ResponseEntity.ok().body(new CouponReturnDto(couponService.findCouponById(couponId)));
    }

    @GetMapping(value = "/expires-today")
    public ResponseEntity<List<CouponReturnDto>> getAllCouponsWithFinishDateEqualsToday() {
        List<CouponReturnDto> couponReturnDtos = new ArrayList<>();
        for (CouponFullDto i : couponService.findAllCouponsWithFinishDataEqualsToday()) {
            couponReturnDtos.add(new CouponReturnDto(i));
        }
        return ResponseEntity.ok().body(couponReturnDtos);

    }

    @GetMapping
    public ResponseEntity<Page<CouponReturnDto>> getAllAdditions(Pageable pageable) {
        List<CouponReturnDto> couponReturnDtos = new ArrayList<>();
        for (CouponFullDto i : couponService.findAllAdditionsPaginated(pageable)) {
            couponReturnDtos.add(new CouponReturnDto(i));
        }
        Page<CouponReturnDto> couponReturnDtoPage = new PageImpl<>(couponReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), couponReturnDtos.size());
        return ResponseEntity.ok().body(couponReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<CouponReturnDto> saveCoupon(@RequestBody @Valid CouponPostDto couponPostDto) {
        return ResponseEntity.ok().body(new CouponReturnDto(couponService.saveCoupon(couponPostDto)));
    }

    @PutMapping(value = "/{couponId}")
    public ResponseEntity<CouponReturnDto> updateCoupon(@PathVariable Long couponId, @RequestBody @Valid CouponPostDto couponPostDto) {
        return ResponseEntity.ok().body(new CouponReturnDto(couponService.updateCoupon(couponId, couponPostDto)));
    }

    @PostMapping(value = "/inactivate/{couponId}")
    public ResponseEntity<CouponReturnDto> inactivateCouponById(@PathVariable Long couponId) {
        return ResponseEntity.ok().body(new CouponReturnDto(couponService.inactivateCouponById(couponId)));
    }

    @PostMapping(value = "/expire/{couponId}")
    public ResponseEntity<CouponReturnDto> expireCouponById(@PathVariable Long couponId) {
        return ResponseEntity.ok().body(new CouponReturnDto(couponService.expireCouponById(couponId)));
    }

    @DeleteMapping(value = "/{couponId}")
    public ResponseEntity<CouponReturnDto> deleteCouponById(@PathVariable Long couponId) {
        couponService.deleteCouponById(couponId);
        return ResponseEntity.noContent().build();
    }
}
