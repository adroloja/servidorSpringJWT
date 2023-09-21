package com.adroyoyo.ServidorApi.Entity;

import jakarta.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cobros")
public class CobrosEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    private long idInquilino;

    @NotBlank
    private String nombre;

    @NotNull
    private double luz;

    @NotNull
    private double agua;

    @NotNull
    private double mensualidad;

    @NotNull
    private double total;

    @NotBlank
    private String fecha;

}
