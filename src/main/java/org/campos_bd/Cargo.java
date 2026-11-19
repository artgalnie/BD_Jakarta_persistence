package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.*;
import org.utils.Utiles;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cargos")
@NamedQueries({
        @NamedQuery(name = "Cargo.findAll", query = "SELECT nombre FROM cargo")
})
public class Cargo implements Serializable, Funcionalidades {

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

    @Override
    public void anyadir() {
        this.setNombre(Utiles.obtenerString("Ingrese el nombre del cargo:"));
        this.setDescripcion(Utiles.obtenerString("Ingrese la descripición del cargo:"));
        this.setSalario_bonus(
                // He visto que Math.ceil es como Math.round pero hacia arriba. Lo he hecho así porque técnica y legalmente es así.
                (double) Math.ceil(
                        Utiles.obtenerDouble("Ingrese el bonus de salario que supone el cargo: (Ejemplo: 1000.12) (0 si no supone ninguno)")
                )
        );
    }
}