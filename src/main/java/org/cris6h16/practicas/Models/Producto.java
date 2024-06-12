package org.cris6h16.practicas.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cedula", name = "cedula_unique")
        })
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


    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Venta.class,
            mappedBy = "producto",
            orphanRemoval = true
    )
    private Set<Venta> venta = new HashSet<>();
}

