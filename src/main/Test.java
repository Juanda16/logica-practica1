package main;


import matriz.Tripleta;
import matriz.Matriz;

public class Test {


    public static void main(String[] args) {
        Matriz a = new Matriz(2, 3);
        a.insertar(new Tripleta(1,1,1));
        a.insertar(new Tripleta(1,2,2));
        a.insertar(new Tripleta(1,3,3));
        a.insertar(new Tripleta(2,1,4));
        a.insertar(new Tripleta(2,2,5));
        a.insertar(new Tripleta(2,3,6));

        Matriz b = new Matriz(3, 2);
        b.insertar(new Tripleta(1,1,5));
        b.insertar(new Tripleta(1,2,-1));
        b.insertar(new Tripleta(2,1,1));
        b.insertar(new Tripleta(3,1,-2));
        b.insertar(new Tripleta(3,2,3));

        Matriz resultado = Matriz.multiplicar(a, b);
        resultado.mostrarMatrizEnTripletaPorPantallaTexto();
    }
}