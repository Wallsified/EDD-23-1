
/**
 * <p> Clase que modela conjuntos </p> <p>Esta clase sirve para crear
 * conjuntos y hacer operaciones entre ellos</p>
 * 
 * @version 1.0
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que implementa la interfaz Iterable para tener nuestros iteradores.
 */
public class Conjunto<T> implements Iterable<T>, Conjuntable<T> {

    /* Arreglo donde guardaremos los elementos de un conjunto */
    private T[] conjunto;

    /* Número de elementos que tiene el conjunto */
    private int elementos;

    private class IteradorConjunto implements Iterator<T> {
        /*
         * Variable para contar cuantos elementos hay en el conjunto
         * no debe tener repeticiones, ni espacios en blanco para que funcione
         */
        int contador = -1;

        public IteradorConjunto() {
        }

        /**
         * Método que nos dice si llegamos o no al final del arreglo
         * 
         * @return Final o no del arreglo.
         */
        public boolean hasNext() {
            return contador < elementos && conjunto[contador] != null;
        }

        /**
         * Método que nos da el elemento dada una posición en el arreglo.
         * 
         * @return elemento en el arreglo.
         */
        public T next() {
            contador += 1;
            return conjunto[contador];
        }
    }

    /**
     * Constructor Vacio.
     */
    public Conjunto() {
    }

    /**
     * Constructor que revise un Conjunto.
     * 
     * @param c Conjunto en cuestion
     */
    @SuppressWarnings("unchecked")
    public Conjunto(Conjunto<T> c) {
        int i = 0; // Es a nivel local, de como se copia el arreglo, no tanto el contador del
                   // iterador.
        Iterator<T> nuevo = c.iterator();
        T[] arreglo = (T[]) new Object[c.elementos];
        while (nuevo.hasNext()) {
            arreglo[i] = (T) nuevo.next();
            i++;
            elementos++;
        }
        this.conjunto = arreglo;
    }

    /**
     * Constructor que revise un array de generícos para instanciarse.
     * 
     * @param elementosNuevos Array para la construcción.
     */
    public Conjunto(T[] elementosNuevos) {
        this.conjunto = elementosNuevos;
        for (T elemento : elementosNuevos) {
            if (elemento != null) {
                this.elementos++;
            }
        }
    }

    /**
     * Método que nos dice si el conjunto está vacío.
     * 
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     *         en otro caso.
     */
    @Override
    public boolean esVacio() {
        return elementos != 0;
    }

    /**
     * Método para obtener el tamaño de un conjunto
     * 
     * @return número de elementos en el conjunto
     */
    @Override
    public int cardinalidad() {
        return elementos;
    }

    /**
     * Método para eliminar todos los elementos de un conjunto
     */
    @Override

    public void vaciar() {
        for (T elemento : conjunto) {
            eliminar(elemento);
        }
    }

    /**
     * Método para agregar un elemento al conjunto
     * 
     * @param elemento Objeto que se incorporara al conjunto
     */
    @Override
    @SuppressWarnings("unchecked")
    public void agregar(T elemento) {
        /*
         * if (elemento == null) {
         * throw new IllegalArgumentException("No puedes agregar el vacio al conjunto");
         * }
         */

        if (!contiene(elemento)) {
            T[] aux = (T[]) new Object[elementos + 1];
            for (int i = 0; i < elementos; i++) {
                aux[i] = conjunto[i];
            }
            aux[elementos] = elemento;
            conjunto = aux;
            elementos++;
        }
    }

    /**
     * Método para eliminar un <code>elemento</code> del conjunto
     * 
     * @param elemento Objeto que se eliminara del conjunto
     */
    @Override
    @SuppressWarnings("unchecked")
    public void eliminar(T elemento) throws NoSuchElementException {

        if (elemento == null) {
            throw new IllegalArgumentException("No puedes eliminar elementos nulos.");
        }

        if (contiene(elemento)) {
            T[] aux = (T[]) new Object[elementos - 1];
            int posición = index(elemento);
            for (int h = 0; h < (elementos - 1); h++) {
                if (h < posición) {
                    aux[h] = conjunto[h];
                }
                if (h >= posición) {
                    aux[h] = conjunto[h + 1];
                }
            }
            conjunto = aux;
            elementos--;
        } else
            throw new NoSuchElementException();

    }

    /**
     * Método para ver si un elemento pertenece al conjunto
     * 
     * @param elemento Objeto que se va a buscar en el conjunto
     * @return <code>true</code> si el elemento esta en el conjunto,
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        boolean a = false;
        int i = 0;
        while (i < elementos && !a) {
            if (compara(elemento, conjunto[i])) {
                a = true;
            }
            i++;
        }
        return a;
    }

    /**
     * Método que compara dos elementos genéricos. Nos ayuda en contiene();
     * 
     * @param elemento  Elemento 1 de la comparación.
     * @param elemento2 Elemento 2 de la comparación.
     * @return boolean Si ambos elementos son iguales.
     */
    private boolean compara(T elemento, T elemento2) {
        return elemento.equals(elemento2);
    }

    /**
     * Método que nos indica la posición en la que se encuentra un elemento <T>
     * 
     * @param elemento Elemento en cuestión.
     * @return int Posición del elemento.
     */
    private int index(T elemento) {
        int posición = 0;
        boolean found = false;
        while (posición < elementos && !found) {
            if (compara(elemento, conjunto[posición])) {
                found = true;
                posición--;
            }
            posición++;
        }
        return posición;
    }

    /**
     * Método que nos ayuda a quitar repeticiones en los conjuntos.
     * Ayuda principalmente en las operaciones con conjuntos.
     * 
     * @return Conjunto<T> Conjunto sin repeticiones.
     */
    @SuppressWarnings("unchecked")
    private Conjunto<T> filtro() {

        int w = 0, j = 1, i = 0; // Son indicadores unicamente.

        while (j < cardinalidad()) { // cuenta los elementos sin repetir -1
            if (conjunto[i] != conjunto[j]) {
                w++;
            }
            i++;
            j++;
        }

        T[] noDouble = (T[]) new Object[w + 1]; // Creamos un nuevo arrary para el resultado
        int t = 0;
        boolean repetido = false;

        for (int m = 0; m < cardinalidad(); m++) {

            repetido = false;

            for (int n = m + 1; n < cardinalidad(); n++) {

                if (conjunto[m].equals(conjunto[n])) {
                    repetido = true;
                }
            }

            if (!repetido) {
                noDouble[t] = conjunto[m];
                t++;
            }

        }

        return new Conjunto<>(noDouble);

    }

    /**
     * Método para calcular la union de dos conjuntos
     * 
     * @param c conjunto con el que se calculará la unión
     * @return Conjuntable conjunto que contiene la unión
     */

    @Override
    public Conjuntable<T> union(Conjuntable<T> c) {
        Conjunto<T> resultadoUnion = new Conjunto<T>();
        Conjunto<T> change = (Conjunto<T>) c;

        for (T elem : this.conjunto) {
            resultadoUnion.agregar(elem);
        }

        for (T elem : change.conjunto) {
            resultadoUnion.agregar(elem);
        }

        return resultadoUnion.filtro();
    }

    /**
     * Método para calcular la intersección de dos conjuntos
     * 
     * @param c conjunto con el que se calculará la intersección
     * @return Conjuntable que con tiene la intersección
     */
    @Override
    public Conjuntable<T> interseccion(Conjuntable<T> c) {
        Conjunto<T> resultadoIntersección = new Conjunto<T>();
        Conjunto<T> transforma = (Conjunto<T>) c;

        for (T elem : conjunto) {
            if (transforma.contiene(elem)) {
                resultadoIntersección.agregar(elem);
            }
        }

        return resultadoIntersección.filtro();
    }

    /**
     * Método para calcular la diferencia de dos conjuntos
     * 
     * 
     * @param c conjunto con el que se va a calcular la diferencia
     * @return Conjuntable con la diferencia
     */
    @Override
    public Conjuntable<T>diferencia(Conjuntable<T> c) {
        Conjunto<T> resultadoDiferencia = new Conjunto<T>();
        Conjunto<T> transforma = (Conjunto<T>) c;

        for (T elem : conjunto) {
            if (!transforma.contiene(elem)) {
                resultadoDiferencia.agregar(elem);
            }
        }

        return resultadoDiferencia.filtro();
    }

    /**
     * Reverso de Diferencia. En conjuntos, la diferencia se ve como
     * x en A tal que x no existe en B. Este hace lo inverso, x en B tal que
     * x no está en B
     * 
     * @param conjunto Conjunto a diferenciar.
     * @return Conjunto diferenciado.
     */
    private Conjuntable<T> reverseDifSim(Conjuntable<T> conjunto) {
        Conjunto<T> Diferencia = new Conjunto<>();
        Conjunto<T> exit = (Conjunto<T>) conjunto;

        for (T elem : exit.conjunto) {
            if (!contiene(elem)) {
                Diferencia.agregar(elem);
            }
        }

        return Diferencia.filtro();
    }

    /**
     * Método para calcular la diferencia simétrica de dos conjuntos
     * 
     * @param c conjunto con el que se va a calcular la diferencia simétrica
     * @return Conjuntable con la diferencia simétrica
     */
    @Override
    public Conjuntable<T> diferenciaSimetrica(Conjuntable<T> c) {
        Conjunto<T> aMenosB = (Conjunto<T>) diferencia(c);
        Conjunto<T> bMenosA = (Conjunto<T>) reverseDifSim(c);
        Conjunto<T> resultado = (Conjunto<T>) aMenosB.union(bMenosA);
        return (Conjunto<T>) resultado.filtro();
    }

    /**
     * Método para determinar si un conjunto esta contenido en otro
     * 
     * @param c conjunto en se va a probar si el que llama es subconjunto
     * @return boolean true si el conjunto que llama a este metodo es
     *         subconjunto del parametro y false en otro caso
     */
    @Override
    public boolean subconjunto(Conjuntable<T> c) {
        Conjunto<T> subConjuntoNuevo = (Conjunto<T>) c;
        boolean indeedSub = false; // indicador
        // consideramos cardinalidad, si es mayor que el conjunto, no es subconjunto.
        if (cardinalidad() <= subConjuntoNuevo.cardinalidad()) {
            indeedSub = true;
            // Comparamos elementos.
            for (int i = 0; i < elementos && indeedSub; i++) {
                if (!subConjuntoNuevo.contiene(conjunto[i])) {
                    indeedSub = false;
                }
            }
        }

        return indeedSub;
    }

    /**
     * Método para imprimir el arreglo.
     * 
     * @return String Cadena del arreglo.
     */
    @Override
    public String toString() {
        String start = "";

        if (elementos > 0) {
            start = start + "[";
            for (int i = 0; i < elementos; i++) {
                if ((i + 1) == elementos) {
                    start = start + conjunto[i] + "]\n";
                } else {
                    start = start + conjunto[i] + ",";
                }
            }
        }
        return start;
    }

    /**
     * Método para crear un iterador sobre un conjunto
     * 
     * @return Iterator iterador sobre el conjunto.
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return new IteradorConjunto();
    }

}
