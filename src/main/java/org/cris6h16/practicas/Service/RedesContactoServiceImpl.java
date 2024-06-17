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

import java.util.List;

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

    @Override
    public List<RedesContacto> obtenerTodo() {
        return redesContactoRepositorio.findAll();
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
                    .urlRed("https://github.com")
                    .build());

            this.redesContactoRepositorio.save(RedesContacto.builder()
                    .nombre(ERedesContacto.X)
                    .urlLogo("https://www.lanacion.com.ar/resizer/v2/elon-musk-cambio-el-logo-de-WHTIWEV47VHEHDV4QYC6PRXVBE.jpg?auth=29e4042f48af310deb7e46ab9afc31d8ef98589c3c1c1c29d6be7ce335477df1&width=880&height=586&quality=70&smart=true")
                    .urlRed("https://x.com")
                    .build());

            this.redesContactoRepositorio.save(RedesContacto.builder()
                    .nombre(ERedesContacto.LINKEDIN)
                    .urlLogo("https://store-images.s-microsoft.com/image/apps.31120.9007199266245564.44dc7699-748d-4c34-ba5e-d04eb48f7960.bc4172bd-63f0-455a-9acd-5457f44e4473?h=210")
                    .urlRed("https://www.linkedin.com")
                    .build());

            this.redesContactoRepositorio.save(RedesContacto.builder()
                    .nombre(ERedesContacto.FACEBOOK)
                    .urlLogo("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Facebook_icon_2013.svg/300px-Facebook_icon_2013.svg.png")
                    .urlRed("https://www.facebook.com/")
                    .build());


        } catch (Exception ignored) {
        }
    }
}
