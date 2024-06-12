package org.cris6h16.practicas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero", name = "numero_unique"),
                @UniqueConstraint(columnNames = "correo", name = "correo_unique"),
                @UniqueConstraint(columnNames = "cedula", name = "cedula_unique")
        })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", allocationSize = 50)
    private Long id;

    @Size(min = 3, max = 50, message = "Los nombres en total, deben tener entre 3 y 50 caracteres")
    @NotBlank(message = "Nombres no puede estar en blanco")
    private String nombres;

    @Size(min = 8, message = "La contrasena debe tener al menos 8 caracteres")
    @NotBlank(message = "Contrasena no puede estar en blanco")
    private String contrasena;

    @Size(min = 3, max = 50, message = "Los apellidos en total, deben tener entre 3 y 50 caracteres")
    @NotBlank(message = "Apellidos no puede estar en blanco")
    private String apellidos;

    @NotBlank(message = "numero no puede estar en blanco")
    @Size(max = 13, message = "El numero de telefono debe tener maximo 13 caracteres")
    private String numero;

    @NotBlank(message = "cedula no puede estar en blanco")
    @Size(min = 10, max = 10, message = "La cedula debe tener 10 caracteres")
    private String cedula;

    @NotBlank(message = "direccion no puede estar en blanco")
    @Size(max = 255, message = "La direccion debe tener maximo 255 caracteres")
    private String direccion;

    @Email(message = "Email invalido")
    @NotBlank(message = "Email no puede estar en blanco")
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            },
            targetEntity = Rol.class,
            optional = true
    )
    @JoinColumn(name = "roles_id")
    private Rol rol;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            targetEntity = Foto.class,
            optional = true,
            orphanRemoval = true
    )
    @JoinColumn(name = "foto_id", foreignKey = @ForeignKey(name = "fk_usuario_foto_id"))
    private Foto foto;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Factura.class,
            mappedBy = "usuario",
            orphanRemoval = true
    )
    private Set<Factura> facturas;
}
