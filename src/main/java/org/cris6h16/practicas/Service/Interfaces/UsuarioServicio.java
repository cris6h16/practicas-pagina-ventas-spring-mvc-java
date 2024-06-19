package org.cris6h16.practicas.Service.Interfaces;

import org.cris6h16.practicas.DTOs.CrearUsuarioDTO;
import org.cris6h16.practicas.Models.Usuario;

import java.util.Optional;

public interface UsuarioServicio {
    boolean existsByCedula(String cedula);
    boolean existsByCorreo(String correo);
    boolean existsByNumero(String numero);

    void crearUsuario(CrearUsuarioDTO dto);

    Optional<Usuario> getByCedula(String cedula);
}
