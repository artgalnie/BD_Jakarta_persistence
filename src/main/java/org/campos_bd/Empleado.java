package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

import lombok.*;
import org.utils.Utiles;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "empleados")
@NamedQueries({
        @NamedQuery(name = "Empleado.findAll", query = "SELECT nombre FROM empleado")
})
public class Empleado implements Serializable, Funcionalidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false, length = 50)
    private Integer id_empleado;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido1;

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

    @Override
    public void anyadir() {
        this.setNombre(Utiles.obtenerString("Ingrese el nombre del empleado:"));
        this.setApellido1(Utiles.obtenerString("Ingrese el primer apellido del empleado:"));
        this.setApellido2(Utiles.obtenerString("Ingrese el segundo apellido del empleado: (si no tiene presione Enter)"));
        this.setSalario_base(
                // He visto que Math.ceil es como Math.round pero hacia arriba. Lo he hecho así porque técnica y legalmente es así.
                (double) Math.ceil(
                    Utiles.obtenerDouble("Ingrese el salario del empleado: (Ejemplo: 1000.00)")
                )
        );
    }
}