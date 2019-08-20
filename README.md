# MATRIZ LISTA LIGADA FORMA 1 #

## Test : : multiplicar dos matrices ##
```java
public static void main(String[] args) {
    Matriz a = new Matriz(2, 3);
    a.insertar(new Tripleta(1,1,1));
    a.insertar(new Tripleta(1,2,2));
    a.insertar(new Tripleta(1,3,3));
    a.insertar(new Tripleta(2,1,4));
    a.insertar(new Tripleta(2,2,5));
    a.insertar(new Tripleta(2,3,6));
    System.out.print("---------------------------\n");
    a.mostrarMatrizEnTripletaPorPantallaTexto();

    Matriz b = new Matriz(3, 2);
    b.insertar(new Tripleta(1,1,5));
    b.insertar(new Tripleta(1,2,-1));
    b.insertar(new Tripleta(2,1,1));
    b.insertar(new Tripleta(3,1,-2));
    b.insertar(new Tripleta(3,2,3));
    System.out.print("---------------------------\n");
    b.mostrarMatrizEnTripletaPorPantallaTexto();

    Matriz resultado = Matriz.multiplicar(a, b);
    if (resultado != null) {
        System.out.print("---------------------------\n");
        resultado.mostrarMatrizEnTripletaPorPantallaTexto();
    }
}

public static void main(String[] args) {
    Matriz a = new Matriz(5, 3);
    a.insertar(new Tripleta(1,1,1));
    a.insertar(new Tripleta(1,2,2));
    a.insertar(new Tripleta(1,3,2));
    a.insertar(new Tripleta(2,1,3));
    a.insertar(new Tripleta(2,2,4));
    a.insertar(new Tripleta(2,3,4));
    a.insertar(new Tripleta(3,1,5));
    a.insertar(new Tripleta(3,2,6));
    a.insertar(new Tripleta(3,3,6));
    a.insertar(new Tripleta(4,1,7));
    a.insertar(new Tripleta(4,2,9));
    a.insertar(new Tripleta(4,3,9));
    a.insertar(new Tripleta(5,2,9));
    a.insertar(new Tripleta(5,3,2));
    
    System.out.print("---------------------------\n");
    a.mostrarMatrizEnTripletaPorPantallaTexto();
    
    Matriz b = new Matriz(3, 3);
    b.insertar(new Tripleta(1,1,1));
    b.insertar(new Tripleta(1,2,2));
    b.insertar(new Tripleta(1,3,2));
    b.insertar(new Tripleta(2,1,3));
    b.insertar(new Tripleta(2,2,1));
    b.insertar(new Tripleta(2,3,22));
    b.insertar(new Tripleta(3,1,2));
    b.insertar(new Tripleta(3,2,4));
    b.insertar(new Tripleta(3,3,5));
    System.out.print("---------------------------\n");
    b.mostrarMatrizEnTripletaPorPantallaTexto();
    
    Matriz resultado = Matriz.multiplicar(a, b);
    if (resultado != null) {
        System.out.print("---------------------------\n");
        resultado.mostrarMatrizEnTripletaPorPantallaTexto();
    }
}
```
