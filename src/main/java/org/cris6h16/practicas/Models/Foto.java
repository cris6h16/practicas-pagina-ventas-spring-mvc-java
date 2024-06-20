package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "fotos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "fotos_id_seq", sequenceName = "fotos_id_seq", allocationSize = 50)
    private Long id;

    @NotBlank(message = "La url no puede estar vacía")
    @Column(columnDefinition = "TEXT")
    private String url;


    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST
            },
            targetEntity = Usuario.class,
            optional = true,
            mappedBy = "foto" // The field that owns the relationship. This element is only specified on the inverse (non-owning) side of the association.
    )
    private Usuario usuario;
}
