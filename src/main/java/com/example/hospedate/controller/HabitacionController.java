package com.example.hospedate.controller;

import com.example.hospedate.model.Habitacion;
import com.example.hospedate.service.IHabitacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedate/habitaciones")
public class HabitacionController {

    private final IHabitacionService habitacionService;

    public HabitacionController(IHabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping
    public Habitacion crear(@RequestBody Habitacion habitacion) {
        return habitacionService.crear(habitacion);
    }

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listar();
    }

    @GetMapping("/{id}")
    public Habitacion buscar(@PathVariable Long id) {
        return habitacionService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Habitacion actualizar(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return habitacionService.actualizar(id, habitacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
    }
}

