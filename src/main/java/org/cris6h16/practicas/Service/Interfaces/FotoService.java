package org.cris6h16.practicas.Service.Interfaces;

import org.cris6h16.practicas.Models.Foto;

import java.util.Optional;

public interface FotoService {
    Optional<Foto> findByUsuarioId(Long id);
    void save(Foto foto, Long usuarioID);
}
