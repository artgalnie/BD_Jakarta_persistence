package org.main;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.campos_bd.Cargo;
import org.campos_bd.Empleado;
import org.campos_bd.Oficina;
import org.utils.*;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static void main() {
        // 1. Obtener EntityManager
        EntityManager em = JpaUtil.getEntityManager();

        try {
            System.out.println("Start:");

            String comando = sc.nextLine();

            // 2. Iniciar transacción
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // 3. Operaciones JPA
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
                        // Umm...
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
