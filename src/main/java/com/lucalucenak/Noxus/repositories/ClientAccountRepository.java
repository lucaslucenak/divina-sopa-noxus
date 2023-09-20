package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.ClientAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccountModel, Long> {
}
