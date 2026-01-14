package com.example.hospedate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name="servicios_adicionales")
public class ServiciosAdicionales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_servicio;

    @NotBlank
    private String nombre;

    @NotNull
    private BigDecimal precio;
}
