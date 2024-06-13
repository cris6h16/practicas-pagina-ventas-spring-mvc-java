package org.cris6h16.practicas.Repository;

import org.cris6h16.practicas.Models.BannerImagenes;
import org.cris6h16.practicas.Models.ECategorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface BannerImagenesRepositorio extends JpaRepository<BannerImagenes, Long> {
    List<BannerImagenes> findByCategoria(ECategorias categoria);
    boolean existsByUrl(String url);
}
