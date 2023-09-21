package com.adroyoyo.ServidorApi.Security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adroyoyo.ServidorApi.Security.jwt.JwtUtils;
import com.adroyoyo.ServidorApi.Service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, 
                                    @NotNull HttpServletResponse response, 
                                    @NotNull FilterChain filterChain)
                                    throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");

        if( tokenHeader != null && tokenHeader.startsWith("Bearer ")){

            String token = tokenHeader.substring(7);

            if(jwtUtils.isTokenValid(token)){

                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = 
                            new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);

            }

        }

    }

}
