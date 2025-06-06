package com.valride.dto;

import com.valride.model.Reserva;

public class ReservaDTO {
    public Long id;
    public String fecha_inicio;
    public String fecha_fin;
    public boolean activa;
    public MotoDTO moto;

    public static ReservaDTO fromEntity(Reserva r) {
        ReservaDTO dto = new ReservaDTO();
        dto.id = r.getId();
        dto.fecha_inicio = r.getFechaInicio() != null ? r.getFechaInicio().toString() : null;
        dto.fecha_fin = r.getFechaFin() != null ? r.getFechaFin().toString() : null;
        dto.activa = r.isActiva();
        dto.moto = MotoDTO.fromEntity(r.getMoto());
        return dto;
    }
}
