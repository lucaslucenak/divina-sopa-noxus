package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.ClientAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAccountRepository extends JpaRepository<ClientAccountModel, Long> {
}
