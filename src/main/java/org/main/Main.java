package org.main;
import jakarta.persistence.*;
import org.campos_bd.*;
import org.utils.Utiles;

import java.util.List;

public class Main {

    static void main(String[] args) {
        // 1. Obtener EntityManager

        if (args.length < 2) {
            System.out.println("No se ha proporcionado ningún argumento.");
        } else {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd_empresa_sl");

            EntityManager em = emf.createEntityManager();

            try {
                // 2. Iniciar transacción
                EntityTransaction tx = em.getTransaction();
                tx.begin();

                // 3. Operaciones JPA

                if (args[0].equals("muestra")) {
                    switch (args[1]) {
                        case "Oficina":
                            TypedQuery<Oficina> Q_Oficina = em.createQuery("SELECT o FROM Oficina o", Oficina.class);
                            List<Oficina> lasOficinas = Q_Oficina.getResultList();
                            for (Oficina o : lasOficinas) {
                                System.out.println(o.getId_oficina() + " - " + o.getTipo());
                            }
                            break;

                        case "Empleado":
                            TypedQuery<Empleado> Q_Empleado = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
                            List<Empleado> losEmpleados = Q_Empleado.getResultList();
                            for (Empleado e : losEmpleados) {
                                System.out.println(e.getNombre() + " " + e.getApellido1() + " " + e.getApellido2());
                            }
                            break;

                        case "Departamento":
                            TypedQuery<Departamento> Q_Departamento = em.createQuery("SELECT d FROM Departamento d", Departamento.class);
                            List<Departamento> losDepartamentos = Q_Departamento.getResultList();
                            for (Departamento c : losDepartamentos) {
                                System.out.println(c.getNombre());
                            }
                            break;

                        case "-r":
                            if (args.length != 3) {
                                System.out.println("Cantidad de argumentos incorrecta.");
                            } else {
                                switch ((args[2])) {
                                    case "Oficina":
                                        TypedQuery<Oficina> Q_Oficina_r = em.createQuery("SELECT o FROM Oficina o", Oficina.class);
                                        List<Oficina> lasOficinas_r = Q_Oficina_r.getResultList();
                                        for (Oficina o : lasOficinas_r) {
                                            System.out.println(o.getId_oficina() + " - " + o.getTipo());
                                            System.out.println("\tEmpleado asignado: " + o.getEmpleado());
                                        }
                                        break;
                                    case "Empleado":
                                        TypedQuery<Empleado> Q_Empleado_r = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
                                        List<Empleado> losEmpleados_r = Q_Empleado_r.getResultList();
                                        for (Empleado e : losEmpleados_r) {
                                            System.out.println(e.getNombre() + " " + e.getApellido1() + " " + e.getApellido2());
                                            System.out.println("\tDepartamento de " + e.getDepartamento().getNombre());
                                            System.out.println("\tOficina " + e.getOficina().getId_oficina() + " - " + e.getOficina().getTipo());
                                        }
                                        break;
                                    case "Departamento":
                                        TypedQuery<Departamento> Q_Departamento_r = em.createQuery("SELECT d FROM Departamento d", Departamento.class);
                                        List<Departamento> losDepartamentos_r = Q_Departamento_r.getResultList();
                                        for (Departamento c : losDepartamentos_r) {
                                            System.out.println(c.getNombre());
                                            for (Empleado e : c.getEmpleados()) {
                                                System.out.println("\t" + e.getNombre() + " " + e.getApellido1() + " " + e.getApellido2());
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Comando inválido");
                                        break;
                                }
                                break;
                            }
                        default:
                            System.out.println("Comando inválido");
                            break;
                    }
                }

                if (args[0].equals("añadir")) {
                    switch ((args[1])) {
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

                        case "Departamento":
                            Departamento departamento = new Departamento();
                            departamento.anyadir();
                            em.persist(departamento);
                            break;

                        case "-r":
                            if (args.length != 3) {
                                System.out.println("Cantidad de argumentos incorrecta.");
                            } else {
                                switch ((args[2])) {
                                    case "Oficina":
                                        Oficina oficina_r = new Oficina();
                                        oficina_r.anyadir_r(em);
                                        em.persist(oficina_r);
                                        break;

                                    case "Empleado":
                                        Empleado empleado_r = new Empleado();
                                        empleado_r.anyadir();


                                }
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
}
