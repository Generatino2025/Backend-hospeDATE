package com.example.hospedate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.DataAmount;

import java.time.LocalDate;
@Entity
@Table(name= "fecha_reserva")
public class FechaReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fechas;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private Reserva id_reserva;

    @NotNull
    private LocalDate check_in;

    @NotNull
    private LocalDate check_out;

    private Integer noches;
}
