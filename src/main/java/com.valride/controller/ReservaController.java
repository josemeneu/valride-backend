package com.valride.controller;

import com.valride.model.Reserva;
import com.valride.model.Usuario;
import com.valride.model.Moto;
import com.valride.service.ReservaService;
import com.valride.service.MotoService;
import com.valride.repository.UsuarioRepository;
import com.valride.dto.ReservaDTO;
import com.valride.dto.ReservaAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private MotoService motoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/mis-reservas")
    public List<ReservaDTO> getMisReservas(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        return reservaService.findByUsuario(usuario).stream().map(ReservaDTO::fromEntity).toList();
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Map<String, Object> body, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Long motoId = Long.valueOf(body.get("motoId").toString());
        Optional<Moto> motoOpt = motoService.findById(motoId);
        if (motoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Moto no encontrada");
        }
        Moto moto = motoOpt.get();
        Reserva reserva = new Reserva();
        // Forzamos el id a null para evitar problemas de duplicidad
        reserva.setId(null);
        reserva.setFechaInicio(java.time.LocalDate.parse(body.get("fechaInicio").toString()));
        reserva.setFechaFin(java.time.LocalDate.parse(body.get("fechaFin").toString()));
        try {
            Reserva nueva = reservaService.crearReserva(moto, usuario, reserva);
            return ResponseEntity.ok(ReservaDTO.fromEntity(nueva));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<ReservaAdminDTO> getAllReservas() {
        return reservaService.findAll().stream().map(ReservaAdminDTO::fromEntity).toList();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean activa = body.getOrDefault("activa", false);
        Reserva reserva = reservaService.cambiarEstadoReserva(id, activa);
        return ResponseEntity.ok(ReservaAdminDTO.fromEntity(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Optional<Reserva> optReserva = reservaService.findById(id);
        if (optReserva.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Reserva reserva = optReserva.get();
        if (!reserva.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).body("No tienes permiso para eliminar esta reserva");
        }
        reservaService.eliminarReserva(id);
        return ResponseEntity.ok().body("Reserva eliminada correctamente");
    }
}
