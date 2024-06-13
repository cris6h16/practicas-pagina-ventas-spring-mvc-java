package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "redes_contacto",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "url_red", name = "url_red_unique"),
        @UniqueConstraint(columnNames = "url_logo", name = "url_logo_unique")
}
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RedesContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "productos_id_seq", sequenceName = "productos_id_seq", allocationSize = 50)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El nombre no puede ser nulo")
    private ERedesContacto nombre;

    @Column(name = "url_red")
    @NotBlank(message = "La url no puede estar vacía")
    private String urlRed;

    @Column(name = "url_logo")
    @NotBlank(message = "La url del logo no puede estar vacía")
    private String urlLogo;

}
