package org.campos_bd;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "oficinas")

@NamedQueries({
        @NamedQuery(name = "Oficina.findAll", query = "SELECT id_oficina, tipo FROM Oficina"),

})

public class Oficina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficina", nullable = false)
    private Integer idOficina;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "piso", nullable = false)
    private Integer piso;
}