package main;


import java.util.ArrayList;
import java.util.Scanner;

import main.matriz.Tripleta;
import main.matriz.Matriz;

public class Main {

    private static Scanner read = new Scanner(System.in);
    private static ArrayList<Matriz> matrices = new ArrayList<Matriz>();
    
    public static final String LOGO = 
        "\n                         $$\\               $$\\               " + "\n" +
        "                         $$ |              \\__|                 " + "\n" +
        "$$$$$$\\$$$$\\   $$$$$$\\ $$$$$$\\    $$$$$$\\  $$\\ $$$$$$$$\\  " + "\n" +
        "$$  _$$  _$$\\  \\____$$\\\\_$$  _|  $$  __$$\\ $$ |\\____$$  |  " + "\n" +
        "$$ / $$ / $$ | $$$$$$$ | $$ |    $$ |  \\__|$$ |  $$$$ _/        " + "\n" +
        "$$ | $$ | $$ |$$  __$$ | $$ |$$\\ $$ |      $$ | $$  _/          " + "\n" +
        "$$ | $$ | $$ |\\$$$$$$$ | \\$$$$  |$$ |      $$ |$$$$$$$$\\      " + "\n" +
        "\\__| \\__| \\__| \\_______|  \\____/ \\__|      \\__|\\________|" + "\n";

    public static final String OPTIONS = 
           "\n" + "[~] Seleccione alguna opcion!"  + "\n\n"
        +  "[1] - Crear matriz."                   + "\n"
        +  "[2] - Mostrar matriz."                 + "\n"
        +  "[3] - Multiplicar matrices ."          + "\n"
        +  "[4] - Multiplicar escalar por matriz." + "\n"
        +  "[.] - Salir."                          + "\n"
        +  "\n" + "choice: ";
    
    public static void main(String[] args) {
        index();
    }

    private static void index() {
        System.out.print(LOGO);
        char select;
        do {
            System.out.print(OPTIONS);
            select = read.next().charAt(0);
            switch (select) {
                case '1':
                    ingresarMatriz();
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
                    break;
                default:
                    break;
            }
        } while (select != '.');
    }

    public static void ingresarMatriz() {
        int filas, columnas;

        System.out.print(
              "\n" + "[~] Ingrese el numero de filas que tendrá la matriz!" 
            + "\n" + "choice: ");
        filas = read.nextInt();

        System.out.print(
              "\n" + "[~] Ingrese el numero de columnas que tendrá la matriz!" 
            + "\n" + "choice: ");
        columnas = read.nextInt();

        Matriz matriz = new Matriz(filas, columnas);
        Tripleta tripleta;
        while ((tripleta = ingresarTripleta()) != null) {
            matriz.insertar(tripleta);
        }
        matrices.add(matriz);
    }

    private static Tripleta ingresarTripleta() {
        System.out.print(
              "\n" + "[~] Ingrese la tripleta separa por comas (,)!" 
            + "\n" + "[~] Ejemplo: 2,1,55  <----" 
            + "\n" + "[?] El primer valor corresponde a la posicion de fila."  
            + "\n" + "[?] El segundo valor corresponde a la posicion de columna."
            + "\n" + "[?] El tercer valor corresponde al valor que guardará la matriz."  
            + "\n" + "[?] Puedes parar ingresando una tripleta de ceros."  
            + "\n" + "[~] Ejemplo: 0,0,0   <----" 
            + "\n" + "tripleta: ");

        String datos[] = read.next().split(",");
        int fila    = Integer.valueOf(datos[0]),
            columna = Integer.valueOf(datos[1]),
            valor   = Integer.valueOf(datos[2]);

        Tripleta tripleta = null;
        if (!(fila == 0 || columna == 0 || valor == 0)) {
            tripleta = new Tripleta(fila, columna, valor);
        }
        return tripleta;
    }
}