package com.example.hospedate.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
@Entity
@Table(name="reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

  @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_habitacion", nullable = false)
    private Habitacion id_habitacion;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    private String notas;
    private LocalDateTime creada_en;

    public enum EstadoReserva {
        activa, pendiente, confirmada,  cancelada, finalizada
    }
    //constructor vacio
    public Reserva(){

    }

    // Crear los getters y setters

    public Long getId_reserva() {return id_reserva;}

    public void setId_reserva(Long id_reserva) {
        this.id_reserva = id_reserva;
    }

  public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Habitacion getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(Habitacion id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreada_en() {
        return creada_en;
    }

    public void setCreada_en(LocalDateTime creada_en) {
        this.creada_en = creada_en;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
