package com.lucalucenak.Noxus.jobs;

import com.lucalucenak.Noxus.dtos.CouponFullDto;
import com.lucalucenak.Noxus.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponScheduler {

    @Autowired
    private CouponService couponService;

    @Scheduled(cron = "0 0 * * * ?")
    public void inactivateCouponsWithFinishDateReached() {
        List<CouponFullDto> couponFullDtos = couponService.findAllCouponsWithFinishDataEqualsToday();
        if (couponFullDtos.size() > 0) {
            for (CouponFullDto i : couponFullDtos) {
                if (i.getStatus().getStatus().equals("ACTIVE")) {
                    couponService.expireCouponById(i.getId());
                }
            }
        }
    }

}
