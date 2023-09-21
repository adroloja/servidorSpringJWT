package com.adroyoyo.ServidorApi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adroyoyo.ServidorApi.Entity.CobrosEntity;
import com.adroyoyo.ServidorApi.Entity.InquilinoEntity;
import com.adroyoyo.ServidorApi.Repositorio.CobrosRepository;
import com.adroyoyo.ServidorApi.Repositorio.InquilinoRepository;

@RestController
public class InquilinoController {

    private InquilinoRepository inquilinoRepository;
    private CobrosRepository cobrosRepository;

    @Autowired
    public InquilinoController(InquilinoRepository inquilinoRepository, CobrosRepository cobrosRepository){

        this.inquilinoRepository = inquilinoRepository;
        this.cobrosRepository = cobrosRepository;
    }

    @GetMapping("/getInquilino/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public InquilinoEntity getInquilino(@PathVariable long id){

        Optional<InquilinoEntity> inquilinoEntity = inquilinoRepository.findById(id);

        if(inquilinoEntity.isPresent()){

            InquilinoEntity inquilino = inquilinoEntity.get();

            return inquilino;
            
        }else{

            return null;
        }
        
    }

    @PostMapping("/introducirInquilino")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> introducirInquilino(@RequestBody InquilinoEntity request){

        inquilinoRepository.save(request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getInquilinos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<InquilinoEntity> getInquilinos(){

        List<InquilinoEntity> lista = inquilinoRepository.findAll();

        return lista;
    }

    @PostMapping("/cobrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> cobrar(@PathVariable long id,
                                            @RequestBody InquilinoEntity inquilinoEntity){

        Optional<InquilinoEntity> optionalInquilino = inquilinoRepository.findById(id);
        
        if(optionalInquilino.isPresent()){

            InquilinoEntity inquilinoEntity2 = optionalInquilino.get();

            CobrosEntity cobrosEntity = new CobrosEntity();
            cobrosEntity.setIdInquilino(inquilinoEntity2.getId());
            cobrosEntity.setAgua(inquilinoEntity2.getAgua());
            cobrosEntity.setFecha(inquilinoEntity2.getFecha());
            cobrosEntity.setMensualidad(inquilinoEntity2.getMensualidad());
            cobrosEntity.setLuz(inquilinoEntity2.getLuz());
            cobrosEntity.setTotal(inquilinoEntity2.getTotal() + inquilinoEntity2.getAgua() + inquilinoEntity2.getLuz());
            cobrosEntity.setNombre(inquilinoEntity2.getNombre());

            cobrosRepository.save(cobrosEntity);

            inquilinoEntity2.setAgua(inquilinoEntity.getAgua());
            inquilinoEntity2.setNombre(inquilinoEntity.getNombre());
            inquilinoEntity2.setFecha(inquilinoEntity.getFecha());
            inquilinoEntity2.setLuz(inquilinoEntity.getLuz());
            inquilinoEntity2.setMensualidad(inquilinoEntity.getMensualidad());
            inquilinoEntity2.setTotal(0);

            inquilinoRepository.save(inquilinoEntity2);

            return new ResponseEntity<>("ok", HttpStatus.OK);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
        
        }
        }
    
    @PostMapping("/actualizarInquilino/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InquilinoEntity> actualizarInquilino( @RequestBody InquilinoEntity inquilino,
                                                                @PathVariable long id){

        Optional<InquilinoEntity> optionalInquilino = inquilinoRepository.findById(id);

        if(optionalInquilino.isPresent()){

            InquilinoEntity inquilinoEntity = optionalInquilino.get();

            inquilinoEntity.setAgua(inquilino.getAgua());
            inquilinoEntity.setNombre(inquilino.getNombre());
            inquilinoEntity.setFecha(inquilino.getFecha());
            inquilinoEntity.setLuz(inquilino.getLuz());
            inquilinoEntity.setMensualidad(inquilino.getMensualidad());
            inquilinoEntity.setTotal(inquilino.getTotal());

            inquilinoRepository.save(inquilinoEntity);

            return new ResponseEntity<>(inquilino, HttpStatus.OK);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
        
        }
    }

    @DeleteMapping("/eliminarInquilino/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InquilinoEntity> eliminarInquilino(@PathVariable long id){

        Optional<InquilinoEntity> optionalInquilino = inquilinoRepository.findById(id);

        if(optionalInquilino.isPresent()){

            InquilinoEntity inquilinoEntity = optionalInquilino.get();

            inquilinoRepository.delete(inquilinoEntity);
            
            return new ResponseEntity<>(inquilinoEntity, HttpStatus.OK);

        }else{

            return new ResponseEntity<InquilinoEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCobros")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CobrosEntity> getCobros(){

        List<CobrosEntity> listaCobros = cobrosRepository.findAll();

        return listaCobros;
    }

    @PostMapping("/pagar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> pagar(@PathVariable long id,
                                            @RequestBody InquilinoEntity inquilinoEntity){

        Optional<InquilinoEntity> optionalInquilino = inquilinoRepository.findById(id);
        
        if(optionalInquilino.isPresent()){

            InquilinoEntity inquilinoEntity2 = optionalInquilino.get();

            CobrosEntity cobrosEntity = new CobrosEntity();
            cobrosEntity.setIdInquilino(inquilinoEntity.getId());
            cobrosEntity.setAgua(inquilinoEntity.getAgua());
            cobrosEntity.setFecha(inquilinoEntity.getFecha());
            cobrosEntity.setMensualidad(inquilinoEntity.getMensualidad());
            cobrosEntity.setLuz(inquilinoEntity.getLuz());
            cobrosEntity.setTotal(inquilinoEntity.getTotal());
            cobrosEntity.setNombre(inquilinoEntity.getNombre());

            cobrosRepository.save(cobrosEntity);

            return new ResponseEntity<>("ok", HttpStatus.OK);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
        
        }
        }
}
