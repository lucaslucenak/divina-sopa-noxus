package com.lucalucenak.Noxus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ClientAccountService clientAccountService;

    @Override
    public UserDetails loadUserByUsername(String clientAccountCpf) throws UsernameNotFoundException {
        return clientAccountService.findClientAccountByCpf(clientAccountCpf);
    }
}
