package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cargos")
@NamedQueries({
//        @NamedQuery(name = "Cargo.findAll", query = "SELECT 'nombre' FROM cargo")
})
public class Cargo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "salario_bonus")
    private Double salario_bonus;

    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "id_empleado",
            foreignKey = @ForeignKey(name = "FK_cargo_empleado")
    )
    private Empleado elEmpleado;
}