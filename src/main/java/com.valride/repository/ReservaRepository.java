package com.valride.repository;

import com.valride.model.Reserva;
import com.valride.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Busca reservas activas de una moto que solapen con un rango de fechas
    @org.springframework.data.jpa.repository.Query("SELECT r FROM Reserva r WHERE r.moto.id = :motoId AND r.activa = true AND (r.fechaInicio <= :fechaFin AND r.fechaFin >= :fechaInicio)")
    java.util.List<Reserva> findReservasSolapadas(Long motoId, java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin);
    List<Reserva> findByUsuario(Usuario usuario);
    List<Reserva> findByUsuarioAndActivaTrue(Usuario usuario);
}
