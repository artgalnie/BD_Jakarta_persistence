package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

import lombok.*;
import org.hibernate.sql.Insert;
import org.utils.Utiles;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "oficinas")

@NamedQueries({
        @NamedQuery(name = "Oficina.findAll", query = "SELECT id_oficina, tipo FROM Oficina")
})

public class Oficina implements Serializable, Funcionalidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficina", nullable = false)
    private Integer idOficina;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "piso", nullable = false)
    private Integer piso;

    public void add () {

    }

    @Override
    public void anyadir() {
        this.setTipo(Utiles.obtenerString("¿Qué tipo de oficina es? Ejemplos: Cubículo, Habitación"));
        this.setPiso(Utiles.obtenerInt("¿En que piso se encuentra? (Número. PB = 0)"));
    }
}