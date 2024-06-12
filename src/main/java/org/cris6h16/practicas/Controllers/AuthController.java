//package org.cris6h16.practicas.Controllers;
//
//import jakarta.validation.Valid;
//import org.cris6h16.practicas.DTOs.UseDTO;
//import org.cris6h16.practicas.Service.UserServiceImpl;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//    UserServiceImpl userService;
//
//    public AuthController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/login")
//    @PreAuthorize("permitAll()")
//    public String tabla() {
//        return "/login";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model){
//        model.addAttribute("user", new UseDTO());
//        return "register";
//    }
//
//
//
//
//    @PostMapping("/register/save")
//    public String registration(@Valid @ModelAttribute("user") UseDTO userDto,
//                               BindingResult result,
//                               Model model){
//        byte existentEmail = (byte) userService.countByEmail(userDto.getEmail());
//        byte existentUsername = (byte) userService.countByUsername(userDto.getUsername());
//
//        if(existentEmail > 0){
//            result.rejectValue("email", String.valueOf(HttpStatus.CONFLICT.value()),
//                    "Ya existe una cuenta registrada con ese email");
//        } else if (existentUsername > 0 ){
//            result.rejectValue("username", String.valueOf(HttpStatus.CONFLICT.value()),
//                    "Ya existe una cuenta registrada con ese nombre de usuario");
//        }
//
//        if(result.hasErrors()){
//            model.addAttribute("user", userDto);
//            return "register";
//        }
//
//        userService.create(userDto);
//        return "redirect:/login?registrado";
//    }
//}
