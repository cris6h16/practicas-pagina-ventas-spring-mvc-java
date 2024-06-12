package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "categorias")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "categorias_id_seq", sequenceName = "categorias_id_seq", allocationSize = 50)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El nombre no puede ser nulo")
    private ECategorias nombre;
}
