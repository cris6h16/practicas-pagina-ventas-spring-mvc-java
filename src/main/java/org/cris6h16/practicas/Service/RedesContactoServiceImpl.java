package org.cris6h16.practicas.Service;

import org.cris6h16.practicas.Models.BannerImagenes;
import org.cris6h16.practicas.Models.ECategorias;
import org.cris6h16.practicas.Models.ERedesContacto;
import org.cris6h16.practicas.Models.RedesContacto;
import org.cris6h16.practicas.Repository.RedesContactoRepositorio;
import org.cris6h16.practicas.Service.Interfaces.RedesContactoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedesContactoServiceImpl implements RedesContactoService {

    private final RedesContactoRepositorio redesContactoRepositorio;

    public RedesContactoServiceImpl(RedesContactoRepositorio redesContactoRepositorio) {
        this.redesContactoRepositorio = redesContactoRepositorio;
        insertarDatosInicialesDePrueba();
    }

    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public RedesContacto buscarPorNombre(ERedesContacto nombre) {
        return redesContactoRepositorio.findByNombre(nombre);
    }

    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public void guardar(RedesContacto redesContacto) {
        redesContactoRepositorio.save(redesContacto);
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_COMMITTED
    )
    public void insertarDatosInicialesDePrueba() {
        try {
            this.redesContactoRepositorio.save(RedesContacto.builder()
                    .nombre(ERedesContacto.GITHUB)
                    .urlLogo("https://camo.githubusercontent.com/6859b81bad9211632c09ba0ba5aff3ce23d87f38bd199a05cfdd67b70d8ef58e/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f6769746875622e737667")
                    .urlRed("https://github.com/cris6h16")
                    .build());

            this.redesContactoRepositorio.save(RedesContacto.builder()
                    .nombre(ERedesContacto.FACEBOOK)
                    .urlLogo("https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Facebook_logo_%28square%29.png/600px-Facebook_logo_%28square%29.png")
                    .urlRed("
                    .build());



        } catch (Exception ignored) {
        }
    }
}
