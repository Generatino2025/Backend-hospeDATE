package com.example.hospedate.service;

import com.example.hospedate.model.Pago;
import com.example.hospedate.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    // CREAR PAGO
    public Pago registrarPago(Pago pago) {

        if (pago.getReserva() == null) {
            throw new IllegalArgumentException("El pago debe estar asociado a una reserva");
        }

        if (pago.getMontoTotal() == null) {
            throw new IllegalArgumentException("El monto total es obligatorio");
        }

        if (pago.getMontoPagado() == null) {
            pago.setMontoPagado(BigDecimal.ZERO);
        }

        BigDecimal saldo =
                pago.getMontoTotal().subtract(pago.getMontoPagado());

        pago.setSaldoPendiente(saldo);

        return pagoRepository.save(pago);
    }

    // LISTAR PAGOS
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    // OBTENER PAGO POR ID
    public Pago obtenerPago(Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }
}
