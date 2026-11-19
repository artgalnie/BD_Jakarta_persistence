package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "empleados")
@NamedQueries({
//        @NamedQuery(name = "Empleado.findAll", query = "SELECT 'nombre' FROM empleado"),
})
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false, length = 50)
    private Integer id_empleado;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido;

    @Column(name = "apellido2", length = 100)
    private String apellido2;

    @Column(name = "salario_base", nullable = false)
    private Double salario_base;

    @OneToMany(
            mappedBy = "elEmpleado",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Cargo> losCargos;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_empleado",
            referencedColumnName = "id_empleado",
            unique = true,
            foreignKey = @ForeignKey(name = "FK_oficina_empleado")
    )
    private Oficina oficina;
}