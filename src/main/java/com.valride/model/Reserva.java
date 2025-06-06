package com.valride.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Moto moto;
    @ManyToOne
    private Usuario usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activa;
    private java.math.BigDecimal precio;
}
