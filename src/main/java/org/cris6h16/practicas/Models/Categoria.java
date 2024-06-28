package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "categorias")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "productos")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "categorias_id_seq", sequenceName = "categorias_id_seq", allocationSize = 50)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El nombre no puede ser nulo")
    private ECategorias nombre;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Producto.class,
            mappedBy = "categoria",
            orphanRemoval = false // let the existence of a product without a category
    )
    private Set<Producto> productos;
}
