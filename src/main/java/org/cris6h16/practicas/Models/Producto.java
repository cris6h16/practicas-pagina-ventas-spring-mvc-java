package org.cris6h16.practicas.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "productos_id_seq", sequenceName = "productos_id_seq", allocationSize = 50)
    private Long id;

    @Size(min = 3, max = 50, message = "El código debe tener entre 3 y 50 caracteres")
    @NotBlank(message = "El código no puede ser nulo o vacío")
    private String codigo;

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank(message = "La descripción no puede ser nula o vacía")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private BigDecimal precio;

    @NotBlank(message = "La url de la imagen no puede ser nula o vacía")
    private String urlImagen;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;



    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Venta.class,
            mappedBy = "producto",
            orphanRemoval = true
    )
    private Set<Venta> venta = new HashSet<>();

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Categoria.class
    )
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}

