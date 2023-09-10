package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {
}
