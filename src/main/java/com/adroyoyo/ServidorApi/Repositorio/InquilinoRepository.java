package com.adroyoyo.ServidorApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adroyoyo.ServidorApi.Entity.InquilinoEntity;

public interface InquilinoRepository extends JpaRepository<InquilinoEntity, Long>{

    List<InquilinoEntity> findByNombre(String nombre);
    
}
