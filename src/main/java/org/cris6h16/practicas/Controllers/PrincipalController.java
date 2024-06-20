package org.cris6h16.practicas.Controllers;

import org.cris6h16.practicas.DTOs.CrearFotoDTO;
import org.cris6h16.practicas.DTOs.CrearUsuarioDTO;
import org.cris6h16.practicas.Models.ECategorias;
import org.cris6h16.practicas.Models.RedesContacto;
import org.cris6h16.practicas.Models.Usuario;
import org.cris6h16.practicas.Service.BannerImagenesServicioImpl;
import org.cris6h16.practicas.Service.RedesContactoServiceImpl;
import org.cris6h16.practicas.Service.UsuarioServicioImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PrincipalController {

    private final UsuarioServicioImpl usuarioServicioImpl;
    private BannerImagenesServicioImpl bannerImagenesServicio;
    private RedesContactoServiceImpl redesContactoService;

    public PrincipalController(BannerImagenesServicioImpl b, RedesContactoServiceImpl r, UsuarioServicioImpl usuarioServicioImpl) {
        this.bannerImagenesServicio = b;
        this.redesContactoService = r;
        this.usuarioServicioImpl = usuarioServicioImpl;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public String home(Model model) {
        List<String> bannerCelulares = bannerImagenesServicio.findByCategoria(ECategorias.CELULARES);
        List<RedesContacto> redesContacto = this.redesContactoService.obtenerTodo();
        model.addAttribute("banner_celulares", bannerCelulares);
        model.addAttribute("redes_contactos", redesContacto);
        return "home";
    }

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login(Model model) {
        List<RedesContacto> redesContacto = this.redesContactoService.obtenerTodo();
        model.addAttribute("redes_contactos", redesContacto);
        return "login";
    }

    @GetMapping("/register")
    @PreAuthorize("permitAll()")
    public String register(Model model) {
        List<RedesContacto> redesContacto = this.redesContactoService.obtenerTodo();
        model.addAttribute("redes_contactos", redesContacto);
        model.addAttribute("crear_dto", new CrearUsuarioDTO());
        return "register";
    }

    @GetMapping("/profile")
    @PreAuthorize("permitAll()")
    public String profile(Model model, Authentication authentication) {
        if (authentication == null) return "redirect:/login?error";
        User user = (User) authentication.getPrincipal();

        Optional<Usuario> usr = usuarioServicioImpl.getByCedula(user.getUsername());
        if (usr.isEmpty()) return "redirect:/login?error";

        List<RedesContacto> redesContacto = this.redesContactoService.obtenerTodo();

        model.addAttribute("redes_contactos", redesContacto);
        model.addAttribute("usuario", usr.get());
        model.addAttribute("fotoDTO", new CrearFotoDTO());
        return "profile";
    }


}
