package main.matriz;

public class NodoDoble {
    
    private final Tripleta t;
    private NodoDoble ligaF;
    private NodoDoble ligaC;

    public NodoDoble(Tripleta t) {
        this.t = t;
    }
    
    public NodoDoble getLigaC() {
        return ligaC;
    }

    public Tripleta getT() {
        return t;
    }

    public NodoDoble getLigaF() {
        return ligaF;
    }

    public void setLigaC(NodoDoble ligaC) {
        this.ligaC = ligaC;
    }

    public void setLigaF(NodoDoble ligaF) {
        this.ligaF = ligaF;
    }
}