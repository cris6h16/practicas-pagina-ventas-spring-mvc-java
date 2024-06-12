package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles",
uniqueConstraints = @UniqueConstraint(
        name = "nombre_unique",
        columnNames = "nombre"
))
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", allocationSize = 50)
    private Long id;

    @Column(name = "nombre")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "El nombre del rol no puede ser nulo")
    private ERoles nombre;

    @OneToMany(
            mappedBy = "rol",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            },
            targetEntity = Usuario.class,
            orphanRemoval = false
    )
    private Set<Usuario> usuarios = new HashSet<Usuario>();
}
