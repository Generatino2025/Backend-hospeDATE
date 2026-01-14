package com.example.hospedate.service;

import com.example.hospedate.exception.ResourceNotFoundException;
import com.example.hospedate.model.Reserva;
import com.example.hospedate.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaServiceImpl implements IReservaService{
    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva crear(Reserva reserva) {
       reserva.setCreada_en(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Esta reserva  no se encontro"));
    }

    @Override
    public Reserva actualizar(Long id, Reserva reserva) {
        Reserva existente = buscarPorId(id);
        existente.setEstado(reserva.getEstado());
        existente.setNotas(reserva.getNotas());
        return reservaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.delete(buscarPorId(id));
    }
}
