package org.cris6h16.practicas.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CrearProductoDTO {

    @NotBlank(message = "El código no puede ser nulo o vacío")
    private String codigo;

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank(message = "La descripción no puede ser nula o vacía")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal precio;

    @NotBlank(message = "La url de la imagen no puede ser nula o vacía")
    private String urlImagen;

    @NotNull(message = "El stock no puede ser nulo")
    private Integer stock;

    @NotNull(message = "La categoria no puede ser nula")
    private PublicCategoriaDTO categoria;
}
