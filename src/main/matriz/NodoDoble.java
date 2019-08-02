/*
 * Define el objeto NodoDoble.
 */
package main.matriz;

/**
 *
 * @author we
 */
public class NodoDoble {
    
    private final Tripleta t;
    private NodoDoble ligaF;
    private NodoDoble ligaC;

    /**
     * Constructor NodoDoble.
     * @param t tripleta
     */
    public NodoDoble(Tripleta t) {
        this.t = t;
    }
    
    /**
     * Obtiene la liga columna del NodoDoble.
     * @return liga columna
     */
    public NodoDoble getLigaC() {
        return ligaC;
    }

    /**
     * Obtiene la tripleta del NodoDoble.
     * @return tripleta
     */
    public Tripleta getT() {
        return t;
    }

    /**
     * Obtiene la liga fila del NodoDoble.
     * @return liga fila
     */
    public NodoDoble getLigaF() {
        return ligaF;
    }

    /**
     * Cambia la liga columna del NodoDoble.
     */
    public void setLigaC(NodoDoble ligaC) {
        this.ligaC = ligaC;
    }

    /**
     * Cambia la liga fila del NodoDoble.
     */
    public void setLigaF(NodoDoble ligaF) {
        this.ligaF = ligaF;
    }
}