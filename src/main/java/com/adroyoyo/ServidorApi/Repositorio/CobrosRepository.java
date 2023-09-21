package com.adroyoyo.ServidorApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adroyoyo.ServidorApi.Entity.CobrosEntity;

public interface CobrosRepository extends JpaRepository<CobrosEntity, Long>{

    List<CobrosEntity> findByNombre(String nombre);
    
}