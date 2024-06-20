package org.cris6h16.practicas.Repository;

import org.cris6h16.practicas.Models.ERoles;
import org.cris6h16.practicas.Models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Optional<Object> findByNombre(ERoles nombre);
}
