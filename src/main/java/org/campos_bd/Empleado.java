package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido;

    @Column(name = "apellido2", length = 100)
    private String apellido2;

    @Column(name = "cargo", nullable = false)
    private Integer cargo;
}