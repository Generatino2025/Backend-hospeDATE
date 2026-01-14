package com.example.hospedate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long id;

    @NotBlank
    private String numero;

    @NotBlank
    private String tipo;

    @NotNull
    private Integer capacidad;

    @NotNull
    private BigDecimal precio_por_noche;
//
//    @OneToMany(mappedBy = "id_habitacion")
//    private List<Reserva> reservas;


    // getters y setters

    public Long getId_habitacion() {
        return id;
    }

    public void setId_habitacion(Long id_habitacion) {
        this.id = id_habitacion;
    }

    public BigDecimal getPrecio_por_noche() {
        return precio_por_noche;
    }

    public void setPrecio_por_noche(BigDecimal precio_por_noche) {
        this.precio_por_noche = precio_por_noche;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//    public List<Reserva> getReservas() {
//        return reservas;
//    }
//
//    public void setReservas(List<Reserva> reservas) {
//        this.reservas = reservas;
//    }
}

