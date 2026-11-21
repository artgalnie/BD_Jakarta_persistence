package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.*;
import org.hibernate.sql.Insert;
import org.utils.Utiles;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "oficinas")
public class Oficina implements Serializable, Funcionalidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficina", nullable = false)
    private Integer id_oficina;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "piso", nullable = false)
    private Integer piso;

    @OneToOne(mappedBy = "oficina")
    private Empleado empleado;

    @Override
    public void anyadir() {
        this.setTipo(Utiles.obtenerString("¿Qué tipo de oficina es? Ejemplos: Cubículo, Habitación"));
        this.setPiso(Utiles.obtenerInt("¿En que piso se encuentra? (Número. PB = 0)"));
    }

    @Override
    public void anyadir_r(EntityManager em) {
        anyadir();

        int contador = 0;

        TypedQuery<Empleado> Q_Empleado = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
        List<Empleado> losEmpleados = Q_Empleado.getResultList();

        for (Empleado e : losEmpleados) {
            contador++;
            System.out.println("\t" + contador + " - " + e.getNombre() + " " + e.getApellido1() + " " + e.getApellido2());
        }

        this.setEmpleado(losEmpleados.get(Utiles.obtenerInt("Selecciona el empleado que va a ir en esta oficina: (por número)") - 1));
    }
}