package main.matriz;

public class Matriz {

    NodoDoble nodoCabezaMatriz;

    public static Matriz multiplicar(Matriz a, Matriz b) {
        NodoDoble nodoCabezaA = a.getNodoCabezaMatriz(),
                  nodoCabezaB = b.getNodoCabezaMatriz();
        
        Tripleta configA = nodoCabezaA.getT(),
                configB = nodoCabezaB.getT();

        int filasA = configA.getF(),
            filasB = configB.getF(),
            columnasA = configA.getC(),
            columnasB = configB.getC();

        if (columnasA != filasB || columnasB != filasA) {
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
        Matriz resultado = new Matriz(filasA, columnasB);
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
                if (columna > columnasB) {
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
     * Operacion elemental, intercambiar filas
     * @param fila1 fila n
     * @param fila2 fila m
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
     * Tiene mas ceros actual que siguiente (?)
     */
    public static boolean tieneMasCeros(NodoDoble actual, NodoDoble siguiente, Tripleta config) {
        boolean response = false;
        if (cerosIzquierda(actual, config) > cerosIzquierda(siguiente, config)) {
            response = true;
        }
        return response;
    }

    public static int cerosIzquierda(NodoDoble nodoCabezaFila, Tripleta config) {
        int columnas = config.getC(),
            columna = nodoCabezaFila.getLigaF().getT().getC(),
            ceros = 0;

        for (int i = 0; i < columnas; i++) {
            if (i == (columna - 1)) {
                ceros = i;
            }
        }
        return ceros;
    }

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
     * Constructor de la matriz sin elementos
     *
     * @param numeroFilas cantidad de filas de la matriz
     * @param numeroColumnas cantidad de columnas de la matriz
     */
    public Matriz(int numeroFilas, int numeroColumnas) {
        construyeNodosCabeza(numeroFilas, numeroColumnas);
    }

    private void construyeNodosCabeza(int numeroFilas, int numeroColumnas) {
        Tripleta tripletaConfiguracion = new Tripleta(numeroFilas, numeroColumnas, null);
        nodoCabezaMatriz = new NodoDoble(tripletaConfiguracion);

        // Depende de las f y c
        int max = (numeroFilas > numeroColumnas) ? numeroFilas : numeroColumnas;

        // Creo los nodos Cabeza de las listas de filas y columas
        // Estas a su vez hacen parte de la lista circular de nodos cabeza
        NodoDoble ultimo = nodoCabezaMatriz;
        for (int i = 1; i <= max; i++) {
            NodoDoble nuevoNodoRegistroCabeza = new NodoDoble(new Tripleta(i, i, null));
            // Estoy creando la referencia circular inicial para la lista de columnas(la oreja)
            nuevoNodoRegistroCabeza.setLigaC(nuevoNodoRegistroCabeza);
            // Estoy creando la referencia circular inicial para la lista de filas(la oreja)
            nuevoNodoRegistroCabeza.setLigaF(nuevoNodoRegistroCabeza);
            // Liga del ultimo con el nuevo
            setLigaNodoCabeza(ultimo, nuevoNodoRegistroCabeza);
            // Este es el nuevo ultimo
            ultimo = nuevoNodoRegistroCabeza;
        }
        // Establezco la referencia de la lista circular
        setLigaNodoCabeza(ultimo, nodoCabezaMatriz);
    }

    /**
     * Crea la liga en los nodos cabeza, se reutiliza el Object de la tripleta
     * del Nodo.
     *
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
     * Retorna el nodo cabeza de la matriz
     *
     * @return
     */
    public NodoDoble getNodoCabezaMatriz() {
        return nodoCabezaMatriz;
    }

    private Matriz(NodoDoble nc) {
        this.nodoCabezaMatriz = nc;
    }

    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz
     *
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
     * matriz
     *
     * @param t
     */
    public void insertar(Tripleta t) {

        // Creo el NodoDoble con los valores a ingresar
        NodoDoble nuevoNodoRegistro = new NodoDoble(t);

        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza
        NodoDoble nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaMatriz);

        // Buscar el nodo cabeza correspondiente a la Fila del registro que 
        // estamos insertando y cuando lo encuentra inserta el registro en la lista
        // de esa fila
        while (nodoCabezaDeRecorridoLocalizado != nodoCabezaMatriz && nodoCabezaDeRecorridoLocalizado != null) {
            if (nodoCabezaDeRecorridoLocalizado.getT().getF() == t.getF()) {
                // Eureka, encontre el Nodo cabeza de la fila
                conectaPorFilas(nodoCabezaDeRecorridoLocalizado, nuevoNodoRegistro);
                break;
            }
            nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaDeRecorridoLocalizado);
        }

        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza
        nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaMatriz);
        // Buscar el nodo cabeza correspondiente a la Columna del registro que
        // estamos insertando y cuando lo encuentra inserta el registro en la lista
        // por columna
        while (nodoCabezaDeRecorridoLocalizado != nodoCabezaMatriz && nodoCabezaDeRecorridoLocalizado != null) {
            if (nodoCabezaDeRecorridoLocalizado.getT().getC() == t.getC()) {
                conectaPorColumnas(nodoCabezaDeRecorridoLocalizado, nuevoNodoRegistro);
                break;
            }
            nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaDeRecorridoLocalizado);
        }
    }

    /**
     * Método para conectar un nuevo nodo por las filas
     *
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
     * Método para conectar un nuevo nodo por las columnas
     *
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

    public void mostrarMatrizEnTripletaPorPantallaTexto() {
        // Obtengo la configuración de la matriz, fr y cr y la cantidadValores
        Tripleta configuracion = nodoCabezaMatriz.getT();
        int fr = configuracion.getF();
        int cr = configuracion.getC();
        // Imprimir una línea con encabezado de las columnas
        System.out.print("\t");
        for (int i = 1; i <= cr; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        NodoDoble nodoRecorridoCabeza = getLigaNodoCabeza(nodoCabezaMatriz);

        // Recorrido por una matriz virtual m x n
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

    public Matriz obtenerIdentidad() {
        Tripleta configuracion = nodoCabezaMatriz.getT();
        int filas = configuracion.getF();
        int cols = configuracion.getC();
        Matriz mI = crearIdentidad(filas, cols);
        return mI;
    }

    public static Matriz crearIdentidad(int f, int c) {
        Tripleta tripletaConfiguracion = new Tripleta(f, c, f);
        NodoDoble nc;
        nc = new NodoDoble(tripletaConfiguracion);
        Matriz mI = new Matriz(nc);

        // Creo los nodos Cabeza de las listas de filas y columnas
        // Estas a su vez hacen parte de la lista circular de nodos cabeza
        NodoDoble ultimo = nc;
        for (int i = 1; i <= f; i++) {
            // Este es el nodo cabeza de la lista circular de fila y de columna
            NodoDoble nca = new NodoDoble(new Tripleta(i, i, 0));
            // Creo el NodoDoble con los valores 1 a ingresar
            NodoDoble nnuevo = new NodoDoble(new Tripleta(1, 1, 1));
            // Creo la lista circular tanto para la final como para la columna
            // Para la columna
            nca.setLigaC(nnuevo);
            nnuevo.setLigaC(nca);
            // Para la fila
            nca.setLigaF(nnuevo);
            nnuevo.setLigaF(nca);

            //Lista circular de cabezas
            setLigaNodoCabeza(ultimo, nca);

            ultimo = nca;
        }
        // Establezco la referencia de la lista circular
        setLigaNodoCabeza(ultimo, nc);

        return mI;
    }

    public int getFilas() {
        return nodoCabezaMatriz.getT().getF();
    }

    /**
     * Busca el valor de una celda i,j de la matriz o 0 en caso contrario
     *
     * @param i
     * @param j
     * @return
     */
    public int getCelda(int i, int j) {
        int valor = 0;

        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza
        NodoDoble nCDeRecorrido = getLigaNodoCabeza(nodoCabezaMatriz);

        /**
         * Buscar el nodo cabeza correspondiente a la Fila del registro que
         * estamos buscando y cuando lo encuentra buscar el registro en la lista
         * de columnas de esa fila
         */
        while (nCDeRecorrido != nodoCabezaMatriz && nCDeRecorrido != null) {
            // Cuando localice la fila busco la columna
            if (nCDeRecorrido.getT().getF() == i) {
                NodoDoble nodoRecorrido = nCDeRecorrido.getLigaF();
                NodoDoble cabezaRecorrido = (NodoDoble) nCDeRecorrido;
                while (nodoRecorrido != null && nodoRecorrido != cabezaRecorrido) {
                    if (j > nodoRecorrido.getT().getC()) {
                        nodoRecorrido = nodoRecorrido.getLigaF();
                    } else {
                        // Cuando no es mayor valido si estoy en la columna
                        if (j == nodoRecorrido.getT().getC()) {
                            valor = (int) nodoRecorrido.getT().getV();
                        }
                        break;
                    }
                }
            }

            nCDeRecorrido = getLigaNodoCabeza(nCDeRecorrido);
        }

        return valor;

    }

    public int getColumnas() {
        return nodoCabezaMatriz.getT().getC();
    }

    public static NodoDoble getCopiaListaFila(NodoDoble nCDeRecorrido) {

        NodoDoble copiaFila = new NodoDoble(nCDeRecorrido.getT().clonar());
        copiaFila.setLigaF(copiaFila);
        NodoDoble ultimoNodoDeFilaCopia = (NodoDoble) copiaFila;

        NodoDoble nodoRecorrido = nCDeRecorrido.getLigaF();
        NodoDoble cabezaRecorrido = (NodoDoble) nCDeRecorrido;
        while (nodoRecorrido != null && nodoRecorrido != cabezaRecorrido) {
            Tripleta tripletaCopia = nodoRecorrido.getT().clonar();
            NodoDoble nuevoNodocopia = new NodoDoble(tripletaCopia);
            ultimoNodoDeFilaCopia.setLigaF(nuevoNodocopia);
            ultimoNodoDeFilaCopia = nuevoNodocopia;
            nodoRecorrido = nodoRecorrido.getLigaF();
        }
        return copiaFila;
    }

    public static void multiplicarFila(int x, NodoDoble nCDeRecorrido) {
        NodoDoble nodoRecorrido = nCDeRecorrido.getLigaF();
        NodoDoble cabezaRecorrido = (NodoDoble) nCDeRecorrido;
        while (nodoRecorrido != null && nodoRecorrido != cabezaRecorrido) {
            Tripleta t = nodoRecorrido.getT();
            int nv = (int) t.getV() * x;
            t.setV(nv);
            nodoRecorrido = nodoRecorrido.getLigaF();
        }
    }

    
    public NodoDoble getFila(int i) {
        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza

        NodoDoble nCDeRecorrido = getLigaNodoCabeza(nodoCabezaMatriz);

        /**
         * Buscar el nodo cabeza correspondiente a la Fila del registro que
         * estamos buscando y cuando lo encuentra clonar
         */
        while (nCDeRecorrido != nodoCabezaMatriz && nCDeRecorrido != null) {
            // Cuando localice la fila 
            if (nCDeRecorrido.getT().getF() == i) {
                return nCDeRecorrido;
            }
        }
        return nCDeRecorrido;
    }

}