package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.post.AuthenticationPostDto;
import com.lucalucenak.Noxus.dtos.response.AuthenticationReturnDto;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.services.ClientAccountService;
import com.lucalucenak.Noxus.services.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ClientAccountService clientAccountService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationReturnDto> login(@RequestBody @Valid AuthenticationPostDto authenticationPostDto) {

        var clientAccountPassword = new UsernamePasswordAuthenticationToken(authenticationPostDto.getCpf(), authenticationPostDto.getPassword());
        var auth = authenticationManager.authenticate(clientAccountPassword);

        var jwtToken = jwtTokenService.generateJwtToken(new ClientAccountModel(clientAccountService.findClientAccountFullDtoByCpf(authenticationPostDto.getCpf())));

        return ResponseEntity.ok(new AuthenticationReturnDto(jwtToken));
    }
}
