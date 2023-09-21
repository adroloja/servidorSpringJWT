package com.adroyoyo.ServidorApi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adroyoyo.ServidorApi.Entity.InquilinoEntity;
import com.adroyoyo.ServidorApi.Repositorio.InquilinoRepository;

@Service
public class InquilinoService {

    private InquilinoRepository inquilinoRepository;

    @Autowired
    public InquilinoService(InquilinoRepository inquilinoRepository){

        this.inquilinoRepository = inquilinoRepository;
    }
    
    @Scheduled(cron = "0 0 1 * * ?") // Se ejecuta a las 01:00:00 AM todos los días 1 de cada mes
    @Transactional
    public void actualizarMensualidades() {
    
            List<InquilinoEntity> lista = inquilinoRepository.findAll();

            for(InquilinoEntity i : lista){

                i.setTotal(i.getTotal() + i.getMensualidad());                
            }

            inquilinoRepository.saveAll(lista);
    }
}
