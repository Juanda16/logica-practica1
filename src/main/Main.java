package main;

import java.util.Scanner;

import main.matriz.Tripleta;
import main.matriz.Matriz;

public class Main {

    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Ingrese las filas: ");
        int filas = read.nextInt();

        System.out.print("Ingrese las columnas: ");
        int columnas = read.nextInt();

        Matriz matriz = new Matriz(filas, columnas);
        Tripleta tripleta;

        while ((tripleta = ingresarTripletaPorPantalla()) != null) {
            matriz.insertar(tripleta);
        }

        matriz.mostrarMatrizEnTripletaPorPantallaTexto();
    }

    private static Tripleta ingresarTripletaPorPantalla() {
        System.out.println("Ingrese la tripleta separada por coma");
        String datos[] = read.next().split(",");
        Tripleta tripleta = null;

        int f = Integer.valueOf(datos[0]);
        int c = Integer.valueOf(datos[1]);
        int v = Integer.valueOf(datos[2]);

        if (!(f == 0 || c == 0 || v == 0)) {
            tripleta = new Tripleta(f, c, v);
        }
        return tripleta;
    }
}