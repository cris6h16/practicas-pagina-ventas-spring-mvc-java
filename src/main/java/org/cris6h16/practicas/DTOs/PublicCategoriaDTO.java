package org.cris6h16.practicas.DTOs;

import jakarta.validation.constraints.NotNull;
import org.cris6h16.practicas.Models.ECategorias;

public class PublicCategoriaDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    private ECategorias nombre;
}
