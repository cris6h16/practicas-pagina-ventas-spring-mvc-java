package org.cris6h16.practicas.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CrearFotoDTO {
    @NotBlank(message = "La url no puede estar vacía")
    private String url;
}
