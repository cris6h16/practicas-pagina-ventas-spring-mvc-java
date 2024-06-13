package org.cris6h16.practicas.Service;

import org.cris6h16.practicas.Models.BannerImagenes;
import org.cris6h16.practicas.Models.ECategorias;
import org.cris6h16.practicas.Repository.BannerImagenesRepositorio;
import org.cris6h16.practicas.Service.Interfaces.BannerImagenesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BannerImagenesServicioImpl implements BannerImagenesServicio {
    private final BannerImagenesRepositorio bannerImagenesRepositorio;

    public BannerImagenesServicioImpl(BannerImagenesRepositorio bannerImagenesRepositorio) {
        this.bannerImagenesRepositorio = bannerImagenesRepositorio;
        insertarDatosInicialesDePrueba();
    }

    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public List<String> findByCategoria(ECategorias c) {
        return bannerImagenesRepositorio.findByCategoria(c).stream()
                .map(BannerImagenes::getUrl)
                .toList();
    }

    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public void save(BannerImagenes b) {
        bannerImagenesRepositorio.save(b);
    }

    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public boolean existsByUrl(String url) {
        return bannerImagenesRepositorio.existsByUrl(url);
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public void insertarDatosInicialesDePrueba() {
        try {
            this.bannerImagenesRepositorio.save(BannerImagenes.builder()
                    .categoria(ECategorias.CELULARES)
                    .url("https://img.gadgethacks.com/img/42/06/63841773513519/0/best-smartphones-buy-2024.1280x600.jpg")
                    .build());

            this.bannerImagenesRepositorio.save(BannerImagenes.builder()
                    .categoria(ECategorias.CELULARES)
                    .url("https://images.ctfassets.net/u7dr43bszaws/75QjZYn4Vzd91A02Gs8Jq5/c37220e5f1cb1281f01522223913abbb/celulares-con-mejor-camara.webp")
                    .build());

            this.bannerImagenesRepositorio.save(BannerImagenes.builder()
                    .categoria(ECategorias.CELULARES)
                    .url("https://cdn.mos.cms.futurecdn.net/NXrS2yEjhTwAcJhuGPuruQ-650-80.png.webp")
                    .build());
        } catch (Exception ignored) {
        }
    }


}
