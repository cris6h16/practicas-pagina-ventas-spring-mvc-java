package org.cris6h16.practicas.Service.Interfaces;

import org.cris6h16.practicas.DTOs.CrearUsuarioDTO;

public interface UsuarioServicio {
    boolean existsByCedula(String cedula);
    boolean existsByCorreo(String correo);
    boolean existsByNumero(String numero);

    void crearUsuario(CrearUsuarioDTO dto);
}
