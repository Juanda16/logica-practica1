/*
 * Define el objeto Tripleta.
 */
package main.matriz;

/**
 *
 * @author we
 */
public class Tripleta {
    
    private int f,c;
    Object v;

    /**
     * Constructo Tripleta.
     * @param f entero que representa la posicion de fila
     * @param c entero que representa la posicion de columna
     * @param v valor que almacena la matriz
     */
    public Tripleta(int f, int c, Object v) {
        this.f = f;
        this.c = c;
        this.v = v;
    }

    /**
     * Obtiene el entero que representa la posicion fila.
     * @return entero que representa la posicion de fila
     */
    public int getF() {
        return f;
    }

    /**
     * Cambia el entero que representa la  la posicion fila.
     * @param f fila
     */
    public void setF(int f) {
        this.f = f;
    }

    /**
     * Obtiene el entero que representa la posicion columna.
     * @return entero que representa la posicion de columna
     */
    public int getC() {
        return c;
    }

    /**
     * Cambia el entero que representa la posicion de columna.
     * @param c columna
     */
    public void setC(int c) {
        this.c = c;
    }

    /**
     * Obtiene el valor.
     * @return valor
     */
    public Object getV() {
        return v;
    }

    /**
     * Modifica el valor.
     * @param v valor
     */
    public void setV(Object v) {
        this.v = v;
    }
}