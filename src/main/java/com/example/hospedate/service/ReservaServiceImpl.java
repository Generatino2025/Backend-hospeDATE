package com.example.hospedate.service;

import com.example.hospedate.dto.ReservRequestDTO;
import com.example.hospedate.exception.ResourceNotFoundException;
import com.example.hospedate.model.*;
import com.example.hospedate.repository.HabitacionRepository;
import com.example.hospedate.repository.ReservaRepository;
import com.example.hospedate.repository.ServiciosAdicionalesRepository;
import com.example.hospedate.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaServiceImpl implements IReservaService{
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabitacionRepository habitacionRepository;
    private final ServiciosAdicionalesRepository serviciosAdicionalesRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, HabitacionRepository habitacionRepository, ServiciosAdicionalesRepository serviciosAdicionalesRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.habitacionRepository = habitacionRepository;
        this.serviciosAdicionalesRepository = serviciosAdicionalesRepository;
    }

   @Override
    public Reserva crear(ReservRequestDTO dto) {

        /*Habitacion habitacion = habitacionRepository
                .findById(reserva.getHabitacion().getIdHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitación no existe"));

        Usuario usuario = usuarioRepository
                .findById(reserva.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        reserva.setHabitacion(habitacion);
        reserva.setUsuario(usuario);

        return reservaRepository.save(reserva);*/
       Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
               .orElseThrow(() -> new RuntimeException("Usuario no existe"));

       Habitacion habitacion = habitacionRepository.findById(dto.getIdHabitacion())
               .orElseThrow(() -> new RuntimeException("Habitación no existe"));

       Reserva reserva = new Reserva();
       reserva.setUsuario(usuario);
       reserva.setHabitacion(habitacion);
       reserva.setEstado(Reserva.EstadoReserva.valueOf(dto.getEstado()));
       reserva.setNotas(dto.getNotas());

       FechaReserva fecha = new FechaReserva();
       fecha.setCheck_in(dto.getCheckIn());
       fecha.setCheck_out(dto.getCheckOut());

       long noches = ChronoUnit.DAYS.between(
               dto.getCheckIn().toLocalDate(),
               dto.getCheckOut().toLocalDate()
       );
       fecha.setNoches((int) noches);

       reserva.setFechaReserva(fecha);

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

    @Transactional
    @Override
    public Reserva agregarServicios(Long idReserva, List<Long> idsServicios) {
        Reserva reserva = buscarPorId(idReserva);

        List<ServiciosAdicionales> servicios = serviciosAdicionalesRepository
                .findAllById(idsServicios);

        reserva.setServicios(servicios);
        return reservaRepository.save(reserva);
    }

}
