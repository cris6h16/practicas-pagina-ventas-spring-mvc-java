package org.cris6h16.practicas.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UseDTO {
    @NotBlank(message = "Nombre de usuario debe tener almenos 1 caracter que no sea un espacio en blanco")
    private String username;
    @Email(message = "email invalido")
    @NotBlank(message = "email no puede ser nulo")
    private String email;
    @Length(min = 8, message = "Contrasena debe tener almenos 8 caracteres")
    private String password;
}
