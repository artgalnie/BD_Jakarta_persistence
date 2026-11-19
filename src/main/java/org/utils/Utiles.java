package org.utils;

import java.util.Scanner;

public class Utiles {
    static Scanner sc = new Scanner(System.in);
    public static String obtenerString(String pedido) {

        System.out.println(pedido);
        String requisito = sc.nextLine();

        sc.close();
        return requisito;
    }

    public static int obtenerInt(String pedido) {

        boolean esNumero = false;

        int requisito = 0;

        do {
            try {
                System.out.println(pedido);
                requisito = Integer.parseInt(sc.nextLine());
                esNumero = true;
            } catch (NumberFormatException e) {
                System.err.println("No es un numero, prueba de nuevo.");;
            }
        } while (!esNumero);

        return requisito;
    }

    public static double obtenerDouble(String pedido) {

        boolean esNumero = false;

        double requisito = 0;

        do {
            try {
                System.out.println(pedido);
                requisito = Double.parseDouble(sc.nextLine());
                esNumero = true;
            } catch (NumberFormatException e) {
                System.err.println("No es un numero, prueba de nuevo.");;
            }
        } while (!esNumero);

        return requisito;
    }
}
