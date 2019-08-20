/*
 * Define el objeto matriz.
 */
package main.matriz;

/**
 *
 * @author we
 */
public class Matriz {

    NodoDoble nodoCabezaMatriz;

    public Tripleta getConfig() {
        return this.nodoCabezaMatriz.getT();
    }

    /**
     * Retorna la matriz resultante despues de multiplcar la 
     * matriz a por la matriz b.
     * @param a matriz a
     * @param b matriz b
     * @return matriz resultado
     */
    public static Matriz multiplicar(Matriz a, Matriz b) {
        NodoDoble nodoCabezaA = a.getNodoCabezaMatriz(),
                  nodoCabezaB = b.getNodoCabezaMatriz();
        
        Tripleta configA = nodoCabezaA.getT(),
                 configB = nodoCabezaB.getT();

        int colA = configA.getC(),
            filA = configA.getF(),
            colB = configB.getC(),
            filB = configB.getF();

        if (colA != filB) {
            return null;
        }
        
        NodoDoble nodoRecorridoA = (NodoDoble) nodoCabezaA.getT().getV();
        NodoDoble nodoRecorridoFilaA = nodoRecorridoA.getLigaF();
        
        NodoDoble nodoRecorridoB = (NodoDoble) nodoCabezaB.getT().getV();
        NodoDoble nodoRecorridoFilaB = nodoRecorridoB.getLigaF();
        NodoDoble nodoRecorridoColumnaB = nodoRecorridoB.getLigaF();
        
        int suma = 0;
        int fila = 1;
        int columna = 1;
        Matriz resultado = new Matriz(filA, colB);
        while (nodoRecorridoA != null && nodoRecorridoA != nodoCabezaA) {
            while (nodoRecorridoFilaB != null && nodoRecorridoFilaB != nodoRecorridoB) {
                while (nodoRecorridoFilaA != null && nodoRecorridoFilaA != nodoRecorridoA && nodoRecorridoColumnaB != null) {
                    if (nodoRecorridoFilaA.getT().getC() < nodoRecorridoColumnaB.getT().getF()) {
                        nodoRecorridoFilaA = nodoRecorridoFilaA.getLigaF();
                    }
                    if (nodoRecorridoFilaA.getT().getC() > nodoRecorridoColumnaB.getT().getF()) {
                        nodoRecorridoColumnaB = nodoRecorridoColumnaB.getLigaC();
                    }
                    if (nodoRecorridoFilaA.getT().getC() == nodoRecorridoColumnaB.getT().getF()) {
                        suma += ((int)nodoRecorridoFilaA.getT().getV() * (int)nodoRecorridoColumnaB.getT().getV());

                        nodoRecorridoFilaA = nodoRecorridoFilaA.getLigaF();
                        nodoRecorridoColumnaB = nodoRecorridoColumnaB.getLigaC();
                    }
                }

                Tripleta nueva = new Tripleta(fila, columna, suma);
                resultado.insertar(nueva);
                suma = 0;

                columna = columna + 1;
                if (columna > colB) {
                    columna = 1;
                    fila = fila + 1;
                }
                nodoRecorridoFilaA = nodoRecorridoA.getLigaF();

                nodoRecorridoFilaB = nodoRecorridoFilaB.getLigaF();
                nodoRecorridoColumnaB = nodoRecorridoFilaB;
                
            }
            nodoRecorridoA = (NodoDoble) nodoRecorridoA.getT().getV();
            nodoRecorridoFilaA = nodoRecorridoA.getLigaF();

            nodoRecorridoFilaB = nodoRecorridoB.getLigaF();
            nodoRecorridoColumnaB = nodoRecorridoFilaB;
        }
        
        return resultado;
    }

    /**
     * Retorna la matriz resultante despues de multiplcar el 
     * escalar lambda por la matriz  matriz.
     * @param lambda escalar entero
     * @param matriz matriz
     * @return matriz resultado
     */
    public static Matriz multiplicarPorEscalar(int lambda, Matriz matriz) {
        NodoDoble nodoCabeza = matriz.getNodoCabezaMatriz();

        NodoDoble config = nodoCabeza;
        NodoDoble nodoRecorrido = (NodoDoble) nodoCabeza.getT().getV();
        NodoDoble nodoRecorridoFila = nodoRecorrido.getLigaF();

        int filas = config.getT().getF();
        int columnas = config.getT().getC();

        Matriz matrizResultado = new Matriz(filas, columnas);

        while (nodoRecorrido != nodoCabeza && nodoRecorrido != null) {
            while (nodoRecorridoFila != null && nodoRecorridoFila != nodoRecorrido) {
                Tripleta tripleta = nodoRecorridoFila.getT();

                int fila = tripleta.getF();
                int columna = tripleta.getC();
                int valor = (Integer) tripleta.getV() * lambda;

                Tripleta t = new Tripleta(fila, columna, valor);

                matrizResultado.insertar(t);
                nodoRecorridoFila = nodoRecorridoFila.getLigaF();
            }
            nodoRecorrido = (NodoDoble) nodoRecorrido.getT().getV();
            nodoRecorridoFila = nodoRecorrido.getLigaF();
        }
        return matrizResultado;
    }

    /**
     * Operacion elemental, intercambiar fila 1 por 
     * fila 2 de la matriz que lo invoque.
     * @param fila1 fila 1
     * @param fila2 fila 2
     */
    public void intercambiarFilas(int fila1, int fila2) {
        if (fila2 < fila1) {
            int temp = fila2;
            fila2 = fila1;
            fila1 = temp;
        }

        NodoDoble ant1 = null;
        NodoDoble ant2 = null;

        NodoDoble nodoCabeza = getNodoCabezaMatriz();
        NodoDoble pasado = (NodoDoble) nodoCabeza;
        NodoDoble presente = (NodoDoble) pasado.getT().getV();

        while (presente != nodoCabeza && presente != null) {
            if (presente.getT().getF() == fila1) {
                ant1 = pasado;
            }
            if (presente.getT().getF() == fila2) {
                ant2 = pasado;
            }
            pasado = (NodoDoble) presente;
            presente = (NodoDoble) presente.getT().getV();
        }

        NodoDoble actual1 = (NodoDoble) ant1.getT().getV(),
                    actual2 = (NodoDoble) ant2.getT().getV(),
                    siguiente1 = (NodoDoble) actual1.getT().getV(),
                    siguiente2 = (NodoDoble) actual2.getT().getV();

        
        actual1.getT().setF(fila2);
        actual2.getT().setF(fila1);

        int diferencia = (fila1 - fila2);
        diferencia = diferencia > 0 ? diferencia : (diferencia * -1);

        if (diferencia == 1) {
            ant1.getT().setV(actual2);
            actual2.getT().setV(actual1);
            actual1.getT().setV(siguiente2);
        } else {
            ant1.getT().setV(actual2);
            ant2.getT().setV(actual1);
            actual1.getT().setV(siguiente2);
            actual2.getT().setV(siguiente1);
        }

        NodoDoble nodoRecorriFila1 = actual1.getLigaF();
        while (nodoRecorriFila1 != actual1 && nodoRecorriFila1 != null) {
            int fila = fila2;
            nodoRecorriFila1.getT().setF(fila);
            nodoRecorriFila1 = nodoRecorriFila1.getLigaF();
        }

        NodoDoble nodoRecorriFila2 = actual2.getLigaF();
        while (nodoRecorriFila2 != actual2 && nodoRecorriFila2 != null) {
            int fila = fila1;
            nodoRecorriFila2.getT().setF(fila);
            nodoRecorriFila2 = nodoRecorriFila2.getLigaF();
        }
    }

    /**
     * Operacion elemental, suma la fila m en la fila n
     * de la matriz que lo invoque.
     * @param m fila 1
     * @param n fila 2
     */
    public void sumarDosFilas(int m, int n) {
        NodoDoble nodoCabeza = getNodoCabezaMatriz();
        NodoDoble nodoRecorrido = (NodoDoble) nodoCabeza.getT().getV();

        NodoDoble nodoCabezaM = null;
        NodoDoble nodoCabezaN = null;

        while (nodoCabeza != nodoRecorrido && nodoRecorrido != null) {
            if (nodoRecorrido.getT().getF() == n) {
                nodoCabezaN = nodoRecorrido;
                break;
            }
            nodoRecorrido = (NodoDoble) nodoRecorrido.getT().getV();
        }

        nodoCabeza = getNodoCabezaMatriz();
        nodoRecorrido = (NodoDoble) nodoCabeza.getT().getV();
        while (nodoCabeza != nodoRecorrido && nodoRecorrido != null) {
            if (nodoRecorrido.getT().getF() == m) {
                nodoCabezaM = nodoRecorrido;
                break;
            }
            nodoRecorrido = (NodoDoble) nodoRecorrido.getT().getV();
        }

        NodoDoble nodoRecorridoM = nodoCabezaM.getLigaF();
        NodoDoble nodoRecorridoN = nodoCabezaN.getLigaF();

        while (nodoRecorridoM != null && nodoRecorridoN != null && nodoRecorridoN != nodoCabezaN && nodoRecorridoM != nodoCabezaM) {
            if (nodoRecorridoN.getT().getC() > nodoRecorridoM.getT().getC()) {
                Tripleta t = nodoRecorridoM.getT();
                t.setF(n);
                insertar(t);
                nodoRecorridoM = nodoRecorridoM.getLigaF();
            }
            
            if (nodoRecorridoM.getT().getC() > nodoRecorridoN.getT().getC()) {
                nodoRecorridoN = nodoRecorridoN.getLigaF();
            }

            if (nodoRecorridoM.getT().getC() == nodoRecorridoN.getT().getC()) {
                int tm = (int) nodoRecorridoM.getT().getV();
                int tn = (int) nodoRecorridoN.getT().getV();
                int suma = tm + tn;

                nodoRecorridoN.getT().setV(suma);

                nodoRecorridoN = nodoRecorridoN.getLigaF();
                nodoRecorridoM = nodoRecorridoM.getLigaF();
            }
        } 
    }

    /**
     * Retorna verdadero si la matriz a y la matriz b
     * son iguales.
     * @param a matriz a
     * @param b matriz b
     * @return true o false
     */
    public static boolean equals(Matriz a, Matriz b) {
        Tripleta configA = a.getNodoCabezaMatriz().getT(),
                 configB = b.getNodoCabezaMatriz().getT();
        
        if (configA.getF() != configB.getF() && configA.getC() != configB.getC()){
            return false;
        }

        NodoDoble nodoCabezaA = a.getNodoCabezaMatriz(),
                  nodoRecorridoA = (NodoDoble) nodoCabezaA.getT().getV(),
                  nodoRecorridoFilaA = (NodoDoble) nodoRecorridoA.getLigaF();

        NodoDoble nodoCabezaB = b.getNodoCabezaMatriz(),
                  nodoRecorridoB = (NodoDoble) nodoCabezaB.getT().getV(),
                  nodoRecorridoFilaB = (NodoDoble) nodoRecorridoB.getLigaF();

        boolean response = true;

        while (nodoRecorridoA != nodoCabezaA && nodoRecorridoA != null && nodoRecorridoB != nodoCabezaB && nodoRecorridoB != null && response) {
            while (nodoRecorridoFilaA != null && nodoRecorridoFilaA != nodoRecorridoA && nodoRecorridoFilaB != null && nodoRecorridoFilaB != nodoRecorridoB && response) {
                if (nodoRecorridoFilaA.getT().getC() != nodoRecorridoFilaB.getT().getC()) {
                    response = false;
                    break;
                }
                nodoRecorridoFilaA = nodoRecorridoFilaA.getLigaF();
                nodoRecorridoFilaB = nodoRecorridoFilaB.getLigaF();
            }

            nodoRecorridoA = (NodoDoble) nodoRecorridoA.getT().getV();
            nodoRecorridoB = (NodoDoble) nodoRecorridoB.getT().getV();
            nodoRecorridoFilaA = nodoRecorridoA.getLigaF();
            nodoRecorridoFilaB = nodoRecorridoB.getLigaF();
        }

        return response;
    }

    /**
     * Multiplica lambda por la fila, 
     * modifica la matriz que lo invoque.
     * @param lambda escalar 
     * @param fila fila
     */
    public void multiplicarFilaPorEscalar(int lambda, int fila) {
        NodoDoble nodoCabeza = getNodoCabezaMatriz();
        NodoDoble nodoRecorrido = (NodoDoble) nodoCabeza.getT().getV();

        NodoDoble nodoCabezaFila = null;
        NodoDoble nodoRecorridoFila = null;

        while (nodoCabeza != nodoRecorrido && nodoRecorrido != null) {
            if (nodoRecorrido.getT().getF() == fila) {
                nodoCabezaFila = nodoRecorrido;
                nodoRecorridoFila = nodoCabezaFila.getLigaF();
                break;
            }
            nodoRecorrido = (NodoDoble) nodoRecorrido.getT().getV();
        }

        while (nodoRecorridoFila != null && nodoRecorridoFila != nodoCabezaFila) {
            Tripleta tripleta = nodoRecorridoFila.getT();
            int mult = (int) tripleta.getV() * lambda;
            nodoRecorridoFila.getT().setV(mult);

            nodoRecorridoFila = nodoRecorridoFila.getLigaF();
        }
    }

    public static Matriz crearMatrizIdentidad(int filas) {
        Matriz identidad = new Matriz(filas, filas);
        for (int i = 1; i <= filas; i++) {
            identidad.insertar(i, i, 1);
        }
        return identidad;
    }

    /**
     * Constructor de la matriz sin elementos.
     * @param numeroFilas cantidad de filas de la matriz
     * @param numeroColumnas cantidad de columnas de la matriz
     */
    public Matriz(int numeroFilas, int numeroColumnas) {
        construyeNodosCabeza(numeroFilas, numeroColumnas);
    }

    /**
     * Construye los nodos cabeza de la matriz.
     * @param numeroFilas cantidad de filas de la matriz
     * @param numeroColumnas cantidad de columnas de la matriz
     */
    private void construyeNodosCabeza(int numeroFilas, int numeroColumnas) {
        Tripleta tripletaConfiguracion = new Tripleta(numeroFilas, numeroColumnas, null);
        nodoCabezaMatriz = new NodoDoble(tripletaConfiguracion);
        int max = (numeroFilas > numeroColumnas) ? numeroFilas : numeroColumnas;

        NodoDoble ultimo = nodoCabezaMatriz;
        for (int i = 1; i <= max; i++) {
            NodoDoble nuevoNodoRegistroCabeza = new NodoDoble(new Tripleta(i, i, null));
            nuevoNodoRegistroCabeza.setLigaC(nuevoNodoRegistroCabeza);
            nuevoNodoRegistroCabeza.setLigaF(nuevoNodoRegistroCabeza);
            setLigaNodoCabeza(ultimo, nuevoNodoRegistroCabeza);
            ultimo = nuevoNodoRegistroCabeza;
        }
        setLigaNodoCabeza(ultimo, nodoCabezaMatriz);
    }

    /**
     * Crea la liga en los nodos cabeza, se reutiliza el Object de la tripleta
     * del Nodo.
     * @param a
     * @param b
     */
    private static void setLigaNodoCabeza(NodoDoble a, NodoDoble b) {
        a.getT().setV(b);
    }

    private NodoDoble getLigaNodoCabeza(NodoDoble a) {
        return (NodoDoble) a.getT().getV();
    }

    /**
     * Retorna el nodo cabeza de la matriz.
     * @return
     */
    public NodoDoble getNodoCabezaMatriz() {
        return nodoCabezaMatriz;
    }

    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz.
     * @param fila fila donde se encuentra el dato
     * @param columna columnas donde se encuentra el dato
     * @param valor valor
     */
    public void insertar(int fila, int columna, int valor) {
        Tripleta nuevoTripletaRegistro = new Tripleta(fila, columna, valor);
        insertar(nuevoTripletaRegistro);
    }

    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz.
     * @param t tripleta
     */
    public void insertar(Tripleta t) {
        NodoDoble nuevoNodoRegistro = new NodoDoble(t);
        NodoDoble nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaMatriz);
        
        while (nodoCabezaDeRecorridoLocalizado != nodoCabezaMatriz && nodoCabezaDeRecorridoLocalizado != null) {
            if (nodoCabezaDeRecorridoLocalizado.getT().getF() == t.getF()) {
                conectaPorFilas(nodoCabezaDeRecorridoLocalizado, nuevoNodoRegistro);
                break;
            }
            nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaDeRecorridoLocalizado);
        }

        nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaMatriz);
        while (nodoCabezaDeRecorridoLocalizado != nodoCabezaMatriz && nodoCabezaDeRecorridoLocalizado != null) {
            if (nodoCabezaDeRecorridoLocalizado.getT().getC() == t.getC()) {
                conectaPorColumnas(nodoCabezaDeRecorridoLocalizado, nuevoNodoRegistro);
                break;
            }
            nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaDeRecorridoLocalizado);
        }
    }

    /**
     * Método para conectar un nuevo nodo por las filas.
     * @param nodoCabezaDeRecorridoLocalizado
     * @param nnuevo
     */
    private void conectaPorFilas(NodoDoble nodoCabezaDeRecorridoLocalizado, NodoDoble nuevoNodoRegistro) {
        NodoDoble nodoRecorridoEnLaFila = nodoCabezaDeRecorridoLocalizado.getLigaF();
        NodoDoble ultimoNodoDeFila = nodoCabezaDeRecorridoLocalizado;
        while (nodoRecorridoEnLaFila != null && nodoRecorridoEnLaFila != nodoCabezaDeRecorridoLocalizado) {
            if (nuevoNodoRegistro.getT().getC() > nodoRecorridoEnLaFila.getT().getC()) {
                ultimoNodoDeFila = nodoRecorridoEnLaFila;
                nodoRecorridoEnLaFila = nodoRecorridoEnLaFila.getLigaF();
            } else {
                break;
            }
        }
        nuevoNodoRegistro.setLigaF(nodoRecorridoEnLaFila);
        ultimoNodoDeFila.setLigaF(nuevoNodoRegistro);
    }

    /**
     * Método para conectar un nuevo nodo por las columnas.
     * @param nodoCDeRecorrido
     * @param nnuevo
     */
    private void conectaPorColumnas(NodoDoble nodoCabezaDeRecorridoLocalizado, NodoDoble nuevoNodoRegistro) {
        NodoDoble s = nodoCabezaDeRecorridoLocalizado.getLigaC();
        NodoDoble ultimoNodoDeColumna = nodoCabezaDeRecorridoLocalizado;
        while (s != null && s != nodoCabezaDeRecorridoLocalizado) {
            if (nuevoNodoRegistro.getT().getF() > s.getT().getF()) {
                ultimoNodoDeColumna = s;
                s = s.getLigaC();
            } else {
                break;
            }
        }
        nuevoNodoRegistro.setLigaC(s);
        ultimoNodoDeColumna.setLigaC(nuevoNodoRegistro);
    }

    /**
     * Muestra la matriz por pantalla.
     */
    public void mostrarMatrizEnTripletaPorPantallaTexto() {
        Tripleta configuracion = nodoCabezaMatriz.getT();
        int fr = configuracion.getF();
        int cr = configuracion.getC();

        System.out.print("\t");
        for (int i = 1; i <= cr; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        NodoDoble nodoRecorridoCabeza = getLigaNodoCabeza(nodoCabezaMatriz);

        for (int fv = 1; fv <= fr; fv++) {
            System.out.print(fv + "\t");
            if (nodoRecorridoCabeza != null && nodoRecorridoCabeza != nodoCabezaMatriz) {
                NodoDoble nodoRecorridoCeldas = nodoRecorridoCabeza.getLigaF();
                for (int cv = 1; cv <= cr; cv++) {
                    if (nodoRecorridoCeldas != null && nodoRecorridoCeldas != nodoRecorridoCabeza) {
                        Tripleta triMo = nodoRecorridoCeldas.getT();
                        int ft = triMo.getF();
                        int ct = triMo.getC();
                        if (fv == ft) {
                            if (cv < ct) {
                                System.out.print("0\t");
                            } else if (cv == ct) {
                                Object vt = triMo.getV();
                                if (vt != null) {
                                    System.out.print(vt + "\t");
                                } else {
                                    System.out.print("ERROR x COLUMNAS!!!!");
                                }
                                nodoRecorridoCeldas = nodoRecorridoCeldas.getLigaF();
                            }
                        } else {
                            System.out.print("ERROR x FILAS !!!!");
                        }
                    } else {
                        System.out.print("0\t");
                    }
                }
            }
            nodoRecorridoCabeza = getLigaNodoCabeza(nodoRecorridoCabeza);
            System.out.println("");
        }
    }
}