package org.cris6h16.practicas.Service;

import org.cris6h16.practicas.DTOs.CrearUsuarioDTO;
import org.cris6h16.practicas.Models.Usuario;
import org.cris6h16.practicas.Repository.UsuarioRepositorio;
import org.cris6h16.practicas.Service.Interfaces.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    private final PasswordEncoder passwordEncoder;
    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_UNCOMMITTED
    )
    @Override
    public boolean existsByCedula(String cedula) {
        return usuarioRepositorio.existsByCedula(cedula);
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_UNCOMMITTED
    )
    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepositorio.existsByCorreo(correo);
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_UNCOMMITTED
    )
    @Override
    public boolean existsByNumero(String numero) {
        return usuarioRepositorio.existsByNumero(numero);
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public void crearUsuario(CrearUsuarioDTO dto) {
        dto.setApellidos(dto.getApellidos().toLowerCase().trim());
        dto.setContrasena(dto.getContrasena().toLowerCase().trim());
        dto.setCorreo(dto.getCorreo().toLowerCase().trim());
        dto.setCedula(dto.getCedula().toLowerCase().trim());
        dto.setNumero(dto.getNumero().toLowerCase().trim());
        dto.setDireccion(dto.getDireccion().toLowerCase().trim());
        dto.setNombres(dto.getNombres().toLowerCase().trim());

        if (dto.getContrasena().length() < 8) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña debe tener al menos 8 caracteres");

        usuarioRepositorio.save(Usuario.builder()
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .cedula(dto.getCedula())
                .contrasena(passwordEncoder.encode(dto.getContrasena()))
                .correo(dto.getCorreo())
                .direccion(dto.getDireccion())
                .numero(dto.getNumero())
                .build());
    }
}