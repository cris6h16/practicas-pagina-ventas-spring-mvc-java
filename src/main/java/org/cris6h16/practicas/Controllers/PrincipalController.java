package org.cris6h16.practicas.Controllers;

import org.cris6h16.practicas.Models.BannerImagenes;
import org.cris6h16.practicas.Models.ECategorias;
import org.cris6h16.practicas.Repository.BannerImagenesRepositorio;
import org.cris6h16.practicas.Service.BannerImagenesServicioImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PrincipalController {

    private BannerImagenesServicioImpl b;

    public PrincipalController(BannerImagenesServicioImpl b) {
        this.b = b;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public String home(Model model) {
        List<String> bannerCelulares = b.findByCategoria(ECategorias.CELULARES);
        List<String> redes_footer = b.findByCategoria(ECategorias.CELULARES);
        model.addAttribute("banner_celulares", bannerCelulares);
        return "home";
    }


}
