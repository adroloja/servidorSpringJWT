package com.adroyoyo.ServidorApi.Security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adroyoyo.ServidorApi.Entity.UserEntity;
import com.adroyoyo.ServidorApi.Security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.lang.Maps;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils){

        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // TODO Auto-generated method stub

        UserEntity userEntity = null;
        String username = "";
        String password = "";
        
        try{

            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = userEntity.getUsername();
            password = userEntity.getPassword();

        }catch(Exception e){

        }

        UsernamePasswordAuthenticationToken authenticationToken = 
                                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User)authResult.getPrincipal();  // Varias librerias tb
        String token = jwtUtils.generateAccesToken(user.getUsername());

        response.addHeader("Authorization", token);

        Map<String, Object> httpReponse = new HashMap<>();
        httpReponse.put("token", token);
        httpReponse.put("mensaje", "Autenticacion correcto");
        httpReponse.put("username", user.getUsername());
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpReponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        
        super.successfulAuthentication(request, response, chain, authResult);
    }     
    
    
}
