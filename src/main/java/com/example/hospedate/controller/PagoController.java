package com.example.hospedate.controller;

import com.example.hospedate.model.Pago;
import com.example.hospedate.service.PagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // POST - Crear pago
    @PostMapping
    public Pago registrar(@RequestBody Pago pago) {
        return pagoService.registrarPago(pago);
    }

    // GET - Listar pagos
    @GetMapping
    public List<Pago> listar() {
        return pagoService.listarPagos();
    }

    // GET - Obtener pago por ID
    @GetMapping("/{id}")
    public Pago obtener(@PathVariable Integer id) {
        return pagoService.obtenerPago(id);
    }
}


