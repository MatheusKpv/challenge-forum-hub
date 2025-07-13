package com.one.challengeforumhub.security;

import com.one.challengeforumhub.exception.TokenInvalidoException;
import com.one.challengeforumhub.service.TokenService;
import com.one.challengeforumhub.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserDetailService userDetailService;

    public SecurityFilter(TokenService tokenService, UserDetailService userDetailService) {
        this.tokenService = tokenService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        final var path = request.getServletPath();

        if (path.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        final var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            final var token = authorizationHeader.substring(7);

            try {
                final var usernameUsuario = tokenService.verificarToken(token);
                final var usuario = userDetailService.loadUserByUsername(usernameUsuario);
                final var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (TokenInvalidoException ex) {
                responderErro(response, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                return;
            }
        } else {
            responderErro(response, HttpServletResponse.SC_UNAUTHORIZED, "Token ausente");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void responderErro(final HttpServletResponse response, final int status, final String mensagem) throws IOException {
        final var json = "{\"message\": \"" + mensagem + "\"}";

        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
