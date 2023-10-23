package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.ClientAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccountModel, Long> {
    Optional<UserDetails> findUserDetailsByCpf(String clientAccountCpf);
    Optional<ClientAccountModel> findClientAccountModelByCpf(String clientAccountCpf);
    boolean existsByCpf(String cpf);
}
