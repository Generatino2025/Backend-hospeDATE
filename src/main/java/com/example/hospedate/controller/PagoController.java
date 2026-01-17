package com.example.hospedate.controller;

import com.example.hospedate.dto.PagoRequestDTO;
import com.example.hospedate.dto.PagoResponseDTO;
import com.example.hospedate.service.PagoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospedate/pagos")
public class PagoController {
    private final PagoServiceImpl pagoService;

    public PagoController(PagoServiceImpl pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> pagar(@RequestBody PagoRequestDTO dto) {
        return ResponseEntity.ok(pagoService.registrarPago(dto));
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<PagoResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.obtenerPorReserva(id));
    }
}
