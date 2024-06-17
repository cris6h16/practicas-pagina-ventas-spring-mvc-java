package org.cris6h16.practicas.Controllers;

import org.cris6h16.practicas.Models.ECategorias;
import org.cris6h16.practicas.Models.RedesContacto;
import org.cris6h16.practicas.Service.BannerImagenesServicioImpl;
import org.cris6h16.practicas.Service.RedesContactoServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PrincipalController {

    private BannerImagenesServicioImpl bannerImagenesServicio;
    private RedesContactoServiceImpl redesContactoService;

    public PrincipalController(BannerImagenesServicioImpl b, RedesContactoServiceImpl r) {
        this.bannerImagenesServicio = b;
        this.redesContactoService = r;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public String home(Model model) {
        List<String> bannerCelulares = bannerImagenesServicio.findByCategoria(ECategorias.CELULARES);
        List<RedesContacto> redesContacto = this.redesContactoService.obtenerTodo();
        model.addAttribute("banner_celulares", bannerCelulares);
        model.addAttribute("redes_contactos", redesContacto );
        return "home";
    }

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login(Model model) {
        List<RedesContacto> redesContacto = this.redesContactoService.obtenerTodo();
        model.addAttribute("redes_contactos", redesContacto );
        return "login";
    }


}
