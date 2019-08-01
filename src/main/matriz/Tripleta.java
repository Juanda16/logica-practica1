package main.matriz;


public class Tripleta {
    
    private int f,c;
    Object v;

    public Tripleta(int f, int c, Object v) {
        this.f = f;
        this.c = c;
        this.v = v;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Object getV() {
        return v;
    }

    public void setV(Object v) {
        this.v = v;
    }
}