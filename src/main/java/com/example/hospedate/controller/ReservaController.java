package com.example.hospedate.controller;

import com.example.hospedate.model.Reserva;
import com.example.hospedate.service.IReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/hospedate/reservas")

public class ReservaController {
    private final IReservaService reservaService;

    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.crear(reserva));
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public Reserva buscar(@PathVariable Long id) {
        return reservaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Reserva actualizar(@PathVariable Long id, @Valid @RequestBody Reserva reserva) {
        return reservaService.actualizar(id, reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
