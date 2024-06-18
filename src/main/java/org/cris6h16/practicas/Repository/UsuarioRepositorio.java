package org.cris6h16.practicas.Repository;

import org.cris6h16.practicas.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    boolean existsByCedula(String cedula);
    boolean existsByCorreo(String correo);
    boolean existsByNumero(String numero);

    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findByCedula(String cedula);
}
