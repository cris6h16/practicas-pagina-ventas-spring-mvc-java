package org.cris6h16.practicas.Repository;

import org.cris6h16.practicas.Models.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FotoRepositorio extends JpaRepository<Foto, Long> {
    Optional<Foto> findByUsuarioId(Long id);
}
