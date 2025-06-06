package com.valride.dto;

import com.valride.model.Reserva;

public class ReservaAdminDTO extends ReservaDTO {
    public UsuarioDTO usuario;

    public static ReservaAdminDTO fromEntity(Reserva r) {
        ReservaAdminDTO dto = new ReservaAdminDTO();
        ReservaDTO base = ReservaDTO.fromEntity(r);
        dto.id = base.id;
        dto.fecha_inicio = base.fecha_inicio;
        dto.fecha_fin = base.fecha_fin;
        dto.activa = base.activa;
        dto.moto = base.moto;
        dto.usuario = UsuarioDTO.fromEntity(r.getUsuario());
        return dto;
    }
}
