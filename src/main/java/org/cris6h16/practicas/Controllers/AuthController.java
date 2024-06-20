package org.cris6h16.practicas.Controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.cris6h16.practicas.DTOs.CrearFotoDTO;
import org.cris6h16.practicas.DTOs.CrearUsuarioDTO;
import org.cris6h16.practicas.Models.Foto;
import org.cris6h16.practicas.Models.Usuario;
import org.cris6h16.practicas.Service.FotoServicioImpl;
import org.cris6h16.practicas.Service.Interfaces.UsuarioServicio;
import org.cris6h16.practicas.Service.UsuarioServicioImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UsuarioServicioImpl usuarioServicioImpl;
    UsuarioServicioImpl usuarioServicio;
    FotoServicioImpl fotoServicio;

    public AuthController(UsuarioServicioImpl usuarioServicioImpl, UsuarioServicioImpl usuarioServicio, FotoServicioImpl fotoServicio) {
        this.usuarioServicioImpl = usuarioServicioImpl;
        this.usuarioServicio = usuarioServicio;
        this.fotoServicio = fotoServicio;
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("crear_dto") CrearUsuarioDTO dto,
                               BindingResult result,
                               Model model) {
        boolean correoYaExiste = usuarioServicio.existsByCorreo(dto.getCorreo());
        boolean cedulaYaExiste = usuarioServicio.existsByCedula(dto.getCedula());
        boolean numeroExiste = usuarioServicio.existsByNumero(dto.getNumero());

        if (correoYaExiste)
            result.rejectValue("correo", String.valueOf(HttpStatus.CONFLICT.value()), "Ya existe una cuenta registrada con ese correo electrónico");
        else if (cedulaYaExiste)
            result.rejectValue("cedula", String.valueOf(HttpStatus.CONFLICT.value()), "Ya existe una cuenta registrada con esa cedula");
        else if (numeroExiste)
            result.rejectValue("numero", String.valueOf(HttpStatus.CONFLICT.value()), "Ya existe una cuenta registrada con esa numero de celular");
        if (result.hasErrors()) {
            model.addAttribute("crear_dto", dto);
            return "register";
        }

        try {
            usuarioServicio.crearUsuario(dto);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(violation ->
                    result.rejectValue(violation.getPropertyPath().toString(), String.valueOf(HttpStatus.BAD_REQUEST.value()), violation.getMessage()));
            model.addAttribute("crear_dto", dto);
            return "register";
        } catch (ResponseStatusException e) { // solo lanzo ese exception para  contrasena
            result.rejectValue("contrasena", String.valueOf(e.getStatusCode().value()), e.getReason());
            model.addAttribute("crear_dto", dto);
            return "register";
        } catch (Exception e) {
            log.error("Error inesperado al registrar usuario: {}", e.toString());
            model.addAttribute("crear_dto", dto);
            return "register?error_inesperado";
        }

        return "redirect:/login?registrado";
    }

    @PostMapping(value = "/profile/foto")
    @PreAuthorize("isAuthenticated()")
    public String subirFoto(@Valid @ModelAttribute("fotoDTO") CrearFotoDTO dto,
                            BindingResult result,
                            Model model,
                            Authentication authentication) {

        try {
            fotoServicio.save(
                    Foto.builder().url(dto.getUrl()).build(),
                    usuarioServicioImpl.getByCedula(authentication.getName()).get().getId()
            );
        } catch (Exception e) {
            log.error("Error inesperado al subir foto: {}", e.toString());
            return "redirect:/profile?errorAlSubirFoto";
        }

        Optional<Usuario> op = usuarioServicio.getByCedula(authentication.getName());
        if (op.isEmpty()) return "redirect:/login?error";

        model.addAttribute("usuario", op.get());
        return "profile";
    }
}
