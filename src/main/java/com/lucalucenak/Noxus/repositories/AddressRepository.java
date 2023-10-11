package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    List<Optional<AddressModel>> findByClientAccountId(Long clientAccountId);
}
