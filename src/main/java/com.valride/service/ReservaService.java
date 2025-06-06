package com.valride.service;

import com.valride.model.Reserva;
import com.valride.model.Usuario;
import com.valride.model.Moto;
import com.valride.repository.ReservaRepository;
import com.valride.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private MotoRepository motoRepository;

    public List<Reserva> findByUsuario(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario);
    }

    public boolean usuarioTieneReservaActiva(Usuario usuario) {
        return !reservaRepository.findByUsuarioAndActivaTrue(usuario).isEmpty();
    }

    public Reserva crearReserva(Moto moto, Usuario usuario, Reserva reserva) {
        // Comprobar solapamiento de fechas con reservas activas
        java.util.List<Reserva> solapadas = reservaRepository.findReservasSolapadas(
            moto.getId(), reserva.getFechaInicio(), reserva.getFechaFin()
        );
        if (!solapadas.isEmpty()) {
            throw new RuntimeException("La motocicleta ya está reservada en esas fechas.");
        }
        // NO marcar como alquilada ni guardar ese flag
        reserva.setUsuario(usuario);
        reserva.setMoto(moto);
        reserva.setActiva(true);
        reserva.setPrecio(moto.getPrecio());
        return reservaRepository.save(reserva);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva cambiarEstadoReserva(Long id, boolean activa) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        reserva.setActiva(activa);
        // Si se desactiva la reserva, liberar la moto
        // Al desactivar la reserva, no es necesario modificar el estado de la moto
        // (el flag alquilada ya no se usa para la lógica de reservas)
        
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            Moto moto = reserva.getMoto();
            if (moto != null) {
                moto.setAlquilada(false);
                motoRepository.save(moto);
            }
        }
        reservaRepository.deleteById(id);
    }
}
