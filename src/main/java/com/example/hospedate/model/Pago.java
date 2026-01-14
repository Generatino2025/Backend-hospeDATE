package com.example.hospedate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "pago")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    private String metodo;

    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @Column(name = "saldo_pendiente")
    private BigDecimal saldoPendiente;

    private String moneda;

    // RELACIÓN CON RESERVA
    @ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;
}


