package com.valride.dto;

import com.valride.model.Moto;

public class MotoDTO {
    public Long id;
    public String nombre;
    public String descripcion;
    public String imagen;
    public java.math.BigDecimal precio;

    public static MotoDTO fromEntity(Moto m) {
        if (m == null) return null;
        MotoDTO dto = new MotoDTO();
        dto.id = m.getId();
        dto.nombre = m.getNombre();
        dto.descripcion = m.getDescripcion();
        dto.imagen = m.getImagen();
        dto.precio = m.getPrecio();
        return dto;
    }
}
