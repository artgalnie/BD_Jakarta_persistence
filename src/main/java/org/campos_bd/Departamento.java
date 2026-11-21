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
@Table(name = "departamentos")
public class Departamento implements Serializable, Funcionalidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_departamento;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "id_empleado",
            foreignKey = @ForeignKey(name = "FK_cargo_empleado")
    )
    private Set<Empleado> empleados;

    @Override
    public void anyadir() {
        this.setNombre(Utiles.obtenerString("Ingrese el nombre del departamento:"));
        this.setDescripcion(Utiles.obtenerString("Ingrese la descripición del departamento:"));
    }

    @Override
    public void anyadir_r(EntityManager em) {

    }
}