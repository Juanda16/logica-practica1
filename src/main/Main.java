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

    public static final String AUTHORS =
        "\t" + "[*] AUTHORS:" + "\n" +
        "\t" + "[*] Brian - Cristian - Juan - Laura - Leonardo - Maria" + "\n" +
        "============================================================";

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
                    mostrar();
                    break;
                case '3':
                    multiplicarMatrices();
                    break;
                case '4':
                    multiplicarMatrizPorEscalar();
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

    public static void mostrar() {
        char select;
        do {
            System.out.print(   
                "\n"   + "[~] Seleccione alguna opcion!"
              + "\n"   + "[!] n = [1,2,3,4....)"
              + "\n\n" + "[0] - Mostrar todas las matrices."
              + "\n"   + "[n] - Mostrar la n-esimo matriz."
              + "\n"   + "[.] - Inicio."
              + "\n"   + "choice: ");
            select = read.next().charAt(0);
            switch (select) {
                case '0':
                    mostrarTodo();
                    break;
                default:
                    int identifier = Character.getNumericValue(select);
                    mostrarById(identifier);
                    break;
            }
        } while (select != '.');
    }

    public static void mostrarTodo() {
        for (int i = 0; i < matrices.size(); i++) {
            System.out.print(
                "\n" + "ID: " + (i+1) + " <<<<<" + "\n" +
                "============================================================\n");
            matrices.get(i).mostrarMatrizEnTripletaPorPantallaTexto();
            System.out.print("============================================================\n");
        }
    }

    public static void mostrarById(int identifier) {
        try {
            identifier--;
            System.out.print("\n============================================================\n");
            matrices.get(identifier).mostrarMatrizEnTripletaPorPantallaTexto();
            System.out.print("============================================================\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("\n" + "[!] Esta matriz no existe!" + "\n");
        }
    }

    public static void multiplicarMatrices() {
        int a, b;
        System.out.print(
              "\n" + "[~] Seleccione una matriz por su ID!"
            + "\n" + "choice: ");
        a = read.nextInt() - 1;

        System.out.print(
              "\n" + "[~] Seleccione una matriz por su ID!"
            + "\n" + "choice: ");
        b = read.nextInt() - 1;

        Matriz c = matrices.get(a);
        Matriz d = matrices.get(b);

        System.out.print("\n" + "[~] Matriz resultado: " + "\n");
        System.out.print("\n============================================================\n");
        Matriz.multiplicar(c, d).mostrarMatrizEnTripletaPorPantallaTexto();
        System.out.print("============================================================\n");
    }

    public static void multiplicarMatrizPorEscalar() {
        int a, escalar;
        System.out.print(
              "\n" + "[~] Seleccione una matriz por su ID!"
            + "\n" + "choice: ");
        a = read.nextInt() - 1;

        System.out.print(
              "\n" + "[~] Ingrese el escalar!"
            + "\n" + "choice: ");
        escalar = read.nextInt();

        System.out.print("\n" + "[~] Matriz resultado: " + "\n");
        System.out.print("\n============================================================\n");
        Matriz.multiplicarPorEscalar(escalar, matrices.get(a)).mostrarMatrizEnTripletaPorPantallaTexto();
        System.out.print("============================================================\n");
    }
}