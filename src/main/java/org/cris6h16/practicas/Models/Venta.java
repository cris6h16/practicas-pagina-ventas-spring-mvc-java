package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "ventas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "fotos_id_seq", sequenceName = "fotos_id_seq", allocationSize = 50)
    private Long id;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad = 0;

    @Min(value = 0, message = "El instante no puede ser negativo")
    private Long instante = System.currentTimeMillis();


    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            },
            targetEntity = Factura.class,
            optional = true
    )
    private Factura factura;

}
