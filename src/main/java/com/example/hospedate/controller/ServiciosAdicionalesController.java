package com.example.hospedate.controller;

import com.example.hospedate.model.ServiciosAdicionales;
import com.example.hospedate.service.IServiciosAdionalesService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedate/servicios")
public class ServiciosAdicionalesController {
    private final IServiciosAdionalesService service;

    public ServiciosAdicionalesController(IServiciosAdionalesService service) {
        this.service = service;
    }

    @PostMapping
    public ServiciosAdicionales crear(@Valid @RequestBody ServiciosAdicionales servicio) {
        return service.crear(servicio);
    }

    @GetMapping
    public List<ServiciosAdicionales> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ServiciosAdicionales buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ServiciosAdicionales actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServiciosAdicionales servicio) {
        return service.actualizar(id, servicio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
