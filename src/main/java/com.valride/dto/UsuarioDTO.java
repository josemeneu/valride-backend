package com.valride.dto;

import com.valride.model.Usuario;

public class UsuarioDTO {
    public Long id;
    public String username;
    public String rol;

    public static UsuarioDTO fromEntity(Usuario u) {
        if (u == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.id = u.getId();
        dto.username = u.getUsername();
        dto.rol = u.getRol();
        return dto;
    }
}
