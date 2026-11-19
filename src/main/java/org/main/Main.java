package org.main;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.campos_bd.*;
import org.utils.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        // 1. Obtener EntityManager
        EntityManager em = JpaUtil.getEntityManager();

        try {
            System.out.println("Start:");

            String comando = sc.nextLine();

            // 2. Iniciar transacción
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // 3. Operaciones JPA

            if (comando.startsWith("muestra ")) {
                switch (comando.split(" ")[1]) {
                    case "Oficina":
                        TypedQuery<Oficina> Q_Oficina = em.createQuery("SELECT o FROM Oficina o", Oficina.class);
                        List<Oficina> lasOficinas = Q_Oficina.getResultList();
                        for (Oficina o : lasOficinas) {
                            System.out.println(o.getIdOficina() + " - " + o.getTipo());
                        }
                        break;

                    case "Empleado":
                        TypedQuery<Empleado> Q_Empleado = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
                        List<Empleado> losEmpleados = Q_Empleado.getResultList();
                        for (Empleado e : losEmpleados) {
                            System.out.println(e.getNombre() + " " + e.getApellido1() + " " + e.getApellido2());
                        }
                        break;

                    case "Cargo":
                        TypedQuery<Cargo> Q_Cargo = em.createQuery("SELECT e FROM Empleado e", Cargo.class);
                        List<Cargo> losCargos = Q_Cargo.getResultList();
                        for (Cargo c : losCargos) {
                            System.out.println(c.getNombre());
                        }
                        break;

                    case "-r":
                        switch (comando.split(" ")[2]) {
                            case "Empleado":
                                TypedQuery<Object[]> rQ_CargosEmpleado = em.createQuery(
                                        "SELECT c.nombre, SIZE(c.cargos) FROM Cargo c", Object[].class);
                                List<Object[]> results = rQ_CargosEmpleado.getResultList();
                                for (Object[] result : results) {
                                    System.out.println("Alumno: " + result[0] + " tiene " + result[1] + " cargos.");
                                }
                        }
                        break;
                    default:
                        System.out.println("Comando invalido");
                        break;
                }
            }

            if (comando.startsWith("añadir ")) {
                switch (comando.split(" ")[1]) {
                    case "Oficina":
                        Oficina oficina = new Oficina();
                        oficina.anyadir();
                        em.persist(oficina);
                        break;

                    case "Empleado":
                        Empleado empleado = new Empleado();
                        empleado.anyadir();
                        em.persist(empleado);
                        break;

                    case "Cargo":
                        Cargo cargo = new Cargo();
                        cargo.anyadir();
                        em.persist(cargo);
                        break;

                    case "-r":
                        TypedQuery<Oficina> q = em.createQuery("SELECT a FROM Oficina a", Oficina.class);
                        List<Oficina> lasOficinas = q.getResultList();
                        for (Oficina o : lasOficinas) {
                            System.out.println(o.getTipo());
                        }

                }
            }

            // 4. Confirmar transacción
            tx.commit();

        } finally {
            // 5. CERRAR SIEMPRE el EntityManager
            em.close();
        }
    }
}
