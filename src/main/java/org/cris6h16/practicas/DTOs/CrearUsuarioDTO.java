package org.cris6h16.practicas.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrearUsuarioDTO {

    @NotBlank(message = "Nombres no puede estar en blanco")
    private String nombres;

    @NotBlank(message = "Contrasena no puede estar en blanco")
    private String contrasena;

    @NotBlank(message = "Apellidos no puede estar en blanco")
    private String apellidos;

    @NotBlank(message = "numero no puede estar en blanco")
    private String numero;

    @NotBlank(message = "cedula no puede estar en blanco")
    private String cedula;

    @NotBlank(message = "direccion no puede estar en blanco")
    private String direccion;

    @NotBlank(message = "Email no puede estar en blanco")
    private String correo;

}
