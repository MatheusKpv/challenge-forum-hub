package com.one.challengeforumhub.controller;

import com.one.challengeforumhub.domain.Usuario;
import com.one.challengeforumhub.dto.authentication.LoginRequestDto;
import com.one.challengeforumhub.dto.authentication.TokenDto;
import com.one.challengeforumhub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final TokenService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(TokenService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequestDto dto) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        final var authentication = authenticationManager.authenticate(authenticationToken);

        final var token = jwtService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }
}
