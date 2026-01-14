package com.example.hospedate.service;

import com.example.hospedate.model.Reserva;

import java.util.List;

public interface IReservaService {
    Reserva crear(Reserva reserva);
    List<Reserva> listar();
    Reserva buscarPorId(Long id);
    Reserva actualizar(Long id, Reserva reserva);
    void eliminar(Long id);

}
