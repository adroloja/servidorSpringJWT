package com.adroyoyo.ServidorApi.Entity;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "inquilinos")
public class InquilinoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 30)
    private String nombre;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String fecha;

    @NotNull
    private double mensualidad;
     
    @NotNull
    private double luz;

    @NotNull
    private double agua;

    @NotNull
    private double total;
    
}
