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
    @Column(name = "id_servicio")
    private Long idServicio;

    @NotBlank
    private String nombre;

    @NotNull
    private BigDecimal precio;

    public Long getIdServicio() {
        return idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
