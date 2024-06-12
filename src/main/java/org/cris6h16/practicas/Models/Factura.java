package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "facturas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "facturas_id_seq", sequenceName = "facturas_id_seq", allocationSize = 50)
    private Long id;

    @Column(name = "total", scale = 2, precision = 10)
    @Min(value = 0, message = "El total no puede ser negativo")
    BigDecimal total = BigDecimal.valueOf(0);

    @Column(name = "instante")
    @Min(value = 0, message = "El instante no puede ser negativo")
    Long instante = System.currentTimeMillis();

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            },
            targetEntity = Usuario.class,
            optional = true)
    @JoinColumn(
            name = "usuario_id",
            foreignKey = @ForeignKey(name = "usuario_id_fk"))
    private Usuario usuario;

    @OneToMany(
            mappedBy = "factura",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            targetEntity = Venta.class)
    private Set<Venta> ventas;

}
