package org.cris6h16.practicas.Service.Interfaces;

import org.cris6h16.practicas.Models.BannerImagenes;
import org.cris6h16.practicas.Models.ECategorias;

import java.util.List;

public interface BannerImagenesServicio {
    List<String> findByCategoria(ECategorias c);
    void save(BannerImagenes b);
    boolean existsByUrl(String url);
}
