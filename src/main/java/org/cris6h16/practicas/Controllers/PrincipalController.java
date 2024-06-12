package org.cris6h16.practicas.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController {
    @GetMapping
    @PreAuthorize("permitAll()")
    public String home(){
        return "home";
    }
}
