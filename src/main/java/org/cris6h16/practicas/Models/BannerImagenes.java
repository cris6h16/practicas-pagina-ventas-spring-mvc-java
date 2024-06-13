package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "banner_imagenes",
        uniqueConstraints = {
        @UniqueConstraint(name = "banner_imagenes_url_unique", columnNames = "url")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BannerImagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "banner_imagenes_id_seq", sequenceName = "banner_imagenes_id_seq", allocationSize = 50)
    private Long id;

    @NotBlank(message = "La URL no puede estar vacia")
    private String url;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "La categoria del banner no puede estar vacia")
    private ECategorias categoria; // banner para carousels, etc
}
