import java.util.Iterator;
import java.util.NoSuchElementException;
import datos.PilasAndColas.Cola;

/**
 * <p>
 * Clase para árboles AVL.
 * </p>
 *
 * <p>
 * Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.
 * </p>
 */
public class ArbolAVL<T extends Comparable<T>>
        extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase
     * para la altura del nodo.
     */
    protected class NodoAVL extends ArbolBinario<T>.Nodo<T> {

        /** La altura del nodo. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * 
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento) {
            super(elemento);
        }

        /**
         * Método equals para los NodosAVL
         * 
         * @param v  Primer nodo a comparar.
         * @param v2 Segundo nodo a comparar.
         * @return <code>true</code> si son el mismo nodo.
         *         <code>false</code> en caso contrario.
         */
        private boolean equals(NodoAVL v, NodoAVL v2) {
            if (v.elemento.equals(v2.elemento))
                return true;
            return false;

        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         * 
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link NodoAVL}, su elemento es igual al elemento de éste
         *         nodo, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            @SuppressWarnings("unchecked")
            NodoAVL nodo = (NodoAVL) o;
            boolean resultado = equals(this, nodo);
            return resultado;
        }

        public String toString() {
            return elemento.toString();
        }
    }

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos en BFS in-order. */
        private Cola<Nodo<T>> cola;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
            cola = new Cola<Nodo<T>>();
            Nodo<T> base = raiz;
            while (base != null) {
                cola.queue(base);
                base = base.izquierdo;
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            return !cola.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override
        public T next() {
            if (cola.esVacia())
                throw new NoSuchElementException("Cola Vacia!");
            Nodo<T> n = cola.dequeue();
            Nodo<T> derecho = n.derecho;
            while (derecho != null) {
                cola.queue(derecho);
                derecho = derecho.izquierdo;
            }
            return n.elemento;
        }
    }

    /**
     * Constructor que recibe algo de tipo {@link Coleccionable}
     * 
     * @param coleccion Coleccion a agregar al árbol.
     */
    public ArbolAVL(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    /**
     * Constructor vacio por omisión {@link ArbolBinario}.
     */
    public ArbolAVL() {
    }

    /**
     * Nos dice si un elemento está contenido en el arbolAVL.
     * 
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     *         <code>false</code> en otro caso.
     */
    public boolean contieneAVL(T elemento) {
        boolean existe = false;
        Iterator<T> hi = this.iterator();
        while (hi.hasNext()) {
            existe = elemento.equals(hi.next());
            if (existe == true) {
                break;
            }
        }
        return existe;
    }

    /**
     * Método para actualizar la altura del árbol AVL
     * 
     * @param v Nodo desde el cual actualizar la altura.
     */
    private void actualizaAltura(NodoAVL v) {
        if (v == null)
            return;
        int izq = (v.izquierdo != null) ? (nodoAVL(v.izquierdo)).altura : 0; // andamos finos con los ifs terciarios eh?
        int der = (v.derecho != null) ? (nodoAVL(v.derecho)).altura : 0;
        int max = Math.max(izq, der) + 1;
        v.altura = max;
    }

    /**
     * Método para rebalancear el aŕbol AVL
     * 
     * @param nodo Nodo desde el cual rebalancear el árbol.
     */
    private void rebalancea(NodoAVL nodo) {
        if (nodo != null) {
            NodoAVL izq = (NodoAVL) nodo.izquierdo, der = (NodoAVL) nodo.derecho;
            int balance = getAltura(izq) - getAltura(der);
            if (balance == 2) {
                if (getAltura(izq.izquierdo) - getAltura(izq.derecho) == -1) {
                    super.giraIzquierda(izq);
                    actualizaAltura((NodoAVL) izq);
                }
                super.giraDerecha(nodo);
            }

            if (balance == -2) {
                if (getAltura(der.izquierdo) - getAltura(der.derecho) == 1) {
                    super.giraDerecha(der);
                    actualizaAltura((NodoAVL) der);
                }
                super.giraIzquierda(nodo);
            }
            actualizaAltura(nodo);
            rebalancea((NodoAVL) nodo.padre);
        }
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioBusqueda#agregar}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agregar(T elemento) {
        NodoAVL nuevo = new NodoAVL(elemento);
        if (raiz == null)
            this.raiz = nuevo;
        else {
            agregarNodo(raiz, nuevo);
        }

        rebalancea((NodoAVL) nuevo);
        actualizaAltura(nuevo);
    }

    /**
     * Método para regresar el nodo máximo en un subárbol AVL.
     * 
     * @param n Nodo desde el cual se busca.
     * @return Nodo máximo en el árbol.
     */
    private Nodo<T> maximoEnSubarbol(Nodo<T> n) {
        return super.maximoEnSubarbolIzquierdo(n);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     * 
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override
    public void eliminar(T elemento) {
        NodoAVL encontrado = (NodoAVL) buscaNodo(raiz, elemento);
        if (encontrado != null) {
            NodoAVL nuevo = (NodoAVL) maximoEnSubarbol(encontrado);
            // NodoAVL copia = encontrado;
            eliminarNodo(encontrado);
            // NodoAVL nuevo = (NodoAVL) maximoEnSubarbol(encontrado);
            actualizaAltura(nuevo);
            rebalancea(nuevo);
        } else
            throw new NoSuchElementException("No existe el elemento en el arbol.");

    }

    /**
     * Regresa la altura del nodo AVL.
     * 
     * @param nodo el nodo del que queremos la altura.
     * @return la altura del nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *                            NodoAVL}.
     */
    public int getAltura(Nodo<T> nodo) {
        if (nodo != null) {
            NodoAVL avl = (NodoAVL) nodo;
            if (nodo instanceof ArbolAVL.NodoAVL)
                return avl.altura;
            throw new ClassCastException();
        }
        return 0;
    }

    /**
     * Convierte el nodo (visto como instancia de {@link
     * Nodo}) en nodo (visto como instancia de {@link
     * NodoAVL}). Método auxililar para hacer este cast en un único
     * lugar.
     * 
     * @param nodo el nodo de árbol binario que queremos como nodo AVL.
     * @return el nodo recibido visto como nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *                            NodoAVL}.
     */
    protected NodoAVL nodoAVL(Nodo<T> nodo) {
        return (NodoAVL) nodo;
    }

    @Override
    /**
     * Método toString del árbol AVL
     * 
     * @return Árbol AVL visto en forma de cadena.
     */
    public String toString() {
        return super.toString();
    }

    /**
     * Regresa un iterador para iterar el árbolAVL. El árbol se itera en orden.
     * 
     * @return un iterador para iterar el árbolAVL.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }
}
