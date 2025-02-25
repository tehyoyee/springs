package com.taehyeong.security_idpw.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taehyeong.security_idpw.authentication.dto.LoginReqDTO;
import com.taehyeong.security_idpw.authentication.service.UsernamePasswordAuthenticationProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends OncePerRequestFilter {

    private final UsernamePasswordAuthenticationProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LoginReqDTO loginReqDTO = new ObjectMapper().readValue(request.getInputStream(), LoginReqDTO.class);
        provider.authenticate(
                new UsernamePasswordAuthentication(loginReqDTO.getUsername(), loginReqDTO.getPassword())
        );
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return !request.getServletPath().equals("/login");

    }
}