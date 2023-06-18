import java.util.NoSuchElementException;
import datos.PilasAndColas.Lista;
import datos.PilasAndColas.Cola;

/**
 * <p>
 * Clase abstracta para modelar la estructura de datos Arbol Binario
 * </p>
 * <p>
 * Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * </p>
 * <p>
 * Puesto que todos los árboles binarios comparten algunas características
 * similares,
 * esta clase sirve perfectamente para modelarlas. Sin embargo no es lo
 * suficientemente
 * específica para modelar algun árbol completamente. Por lo que la
 * implementación
 * final depende de las clases concretas que hereden de ésta.
 * </p>
 * 
 * @author <a href="mailto:alejandrohmora@ciencias.unam.mx"> Alejandro Hernández
 *         Mora </a>
 * @version 1.0
 */

public abstract class ArbolBinario<T> implements Coleccionable<T> {
    /**
     * Clase interna protegida para nodos.
     * El "A" solo quitaba el error, no hace mucho.
     */
    protected class Nodo<A> {

        /** El elemento del nodo. */
        public T elemento;
        /** Referencia a los nodos padre, e hijos. */
        public Nodo<T> padre, izquierdo, derecho;

        /**
         * Constructor único que recibe un elemento.
         * 
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }

        /**
         * Nos dice si el nodo tiene un padre.
         * 
         * @return <code>true</code> si el nodo tiene padre,
         *         <code>false</code> en otro caso.
         */
        public boolean hayPadre() {
            return this.padre != null;
        }

        /**
         * Nos dice si el nodo tiene un izquierdo.
         * 
         * @return <code>true</code> si el nodo tiene izquierdo,
         *         <code>false</code> en otro caso.
         */
        public boolean hayIzquierdo() {
            return this.izquierdo != null;
        }

        /**
         * Nos dice si el nodo tiene un derecho.
         * 
         * @return <code>true</code> si el nodo tiene derecho,
         *         <code>false</code> en otro caso.
         */
        public boolean hayDerecho() {
            return this.derecho != null;
        }

        /**
         * Regresa la altura del nodo.
         * 
         * @return la altura del nodo.
         */
        public int altura() {
            if (this.izquierdo == null && this.derecho == null)
                return 1;
            else if ((this.izquierdo == null))
                return 1 + derecho.altura();
            else if (this.derecho == null) {
                return 1 + izquierdo.altura();
            } else
                return 1 + Math.max(derecho.altura(), izquierdo.altura());
        }

        /**
         * Regresa el elemento al que apunta el nodo.
         * 
         * @return el elemento al que apunta el nodo.
         */
        public T get() {
            return this.elemento;
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Nodo} deben
         * sobrecargar el método {@link Nodo#equals}.
         * 
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Nodo}, su elemento es igual al elemento de éste
         *         nodo, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false; // por que si no son del mismo tipo, no son iguales.
            }
            @SuppressWarnings("unchecked")
            Nodo<T> nodo = (Nodo<T>) o;
            if (!this.elemento.equals(nodo.elemento))
                return false;

            boolean igualIzquierdo;
            if (this.izquierdo == null) {
                if (nodo.izquierdo == null)
                    igualIzquierdo = true;
                else
                    return false;
            } else
                igualIzquierdo = this.izquierdo.equals(nodo.izquierdo);
            boolean igualDerecho = false;
            if (this.derecho == null) {
                if (nodo.derecho == null)
                    igualDerecho = true;
                else
                    return false;
            } else
                igualDerecho = this.derecho.equals(nodo.derecho);

            return igualDerecho == igualIzquierdo;
        }

        /**
         * Regresa una representación en cadena del nodo.
         * 
         * @return una representación en cadena del nodo.
         */
        public String toString() {
            return elemento.toString();
        }
    }

    /** La raíz del árbol. */
    protected Nodo<T> raiz;
    /** El número de elementos */
    protected int tamanio;

    /**
     * Constructor sin parámetros.
     */
    public ArbolBinario() {
    }

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     * tendrá los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  binario.
     */
    public ArbolBinario(Coleccionable<T> coleccion) {
        for (T elem : coleccion) {
            agregar(elem);
        }
    }

    /**
     * Construye un nuevo nodo, usando una instancia de {@link Nodo}. Para
     * crear nodos se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de nodos.
     * 
     * @param elemento el elemento dentro del nodo.
     * @return un nuevo nodo con el elemento recibido dentro del mismo.
     */
    protected Nodo<T> nuevoNodo(T elemento) {
        return new Nodo<T>(elemento);
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol es la altura de su
     * raíz.
     * 
     * @return la altura del árbol.
     */
    public int altura() {
        if (raiz == null)
            return 0;
        else
            return raiz.altura();
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * 
     * @return el número de elementos en el árbol.
     */
    @Override
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Regresa el nodo que contiene la raíz del árbol.
     * 
     * @return el nodo que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public Nodo<T> raiz() {
        if (esVacia())
            throw new NoSuchElementException();
        return this.raiz;
    }

    /**
     * Nos dice si el árbol es vacío.
     * 
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean esVacia() {
        return this.raiz != null;
    }

    /**
     * Método para eliminar todos los elementos en la colección
     */
    public void vaciar() {
        raiz = null; // en teoria podemos aprovecharnos del recolector de basura no?
        tamanio = 0;
    }

    /**
     * Regresa una Cola con el los elementos en inorden del árbol.
     * 
     * @return Cola con los elementos del arbol.
     */

    public Lista<T> preOrden() {
        Cola<T> test = new Cola<T>();
        preordenAux(raiz, test);
        return test;
    }

    /**
     * Método auxiliar para preorden.
     * 
     * @param n Nodo a ordenar.
     * @param l Lista (Cola) en la que se iran ordenando los nodos.
     */
    private void preordenAux(Nodo<T> n, Lista<T> l) {
        l.agregar(n.elemento);
        if (n.hayIzquierdo()) {
            inordenAux(n.izquierdo, l);
        }
        if (n.hayDerecho()) {
            inordenAux(n.derecho, l);
        }
    }

    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * 
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> postOrden() {
        Cola<T> test = new Cola<T>();
        postordenAux(raiz, test);
        return test;
    }

    /**
     * Método auxiliar para postorden.
     * 
     * @param n Nodo a ordenar.
     * @param l Lista (Cola) en la que se iran ordenando los nodos.
     */
    private void postordenAux(Nodo<T> n, Lista<T> l) {
        if (n.hayIzquierdo()) {
            inordenAux(n.izquierdo, l);
        }
        if (n.hayDerecho()) {
            inordenAux(n.derecho, l);
        }
        l.agregar(n.elemento);
    }

    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * 
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> inOrden() {
        Cola<T> l = new Cola<>();
        inordenAux(raiz, l);
        return l;
    }

    /**
     * Método auxiliar para inorden.
     * 
     * @param n Nodo a ordenar.
     * @param l Lista (Cola) en la que se iran ordenando los nodos.
     */
    private void inordenAux(Nodo<T> n, Lista<T> l) {
        if (n.hayIzquierdo()) {
            inordenAux(n.izquierdo, l);
        }
        l.agregar(n.elemento);
        if (n.hayDerecho()) {
            inordenAux(n.derecho, l);
        }
    }

    /**
     * Compara el árbol con un objeto.
     * 
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false; // por que si no son del mismo tipo, no son iguales.
        }
        @SuppressWarnings("unchecked")
        ArbolBinario<T> arbol = (ArbolBinario<T>) o;
        if (arbol != null && !this.esVacia()) {
            return raiz.equals(arbol.raiz);
        }
        return false;
    }

    /**
     * Regresa una representación en cadena del árbol.
     * 
     * @return una representación en cadena del árbol.
     */
    @Override
    public String toString() {
        if (!esVacia())
            return "arbol vacio";

        boolean[] contador = new boolean[altura() + 1];

        for (int i = 0; i < altura(); i++)
            contador[i] = false;

        return toString(this.raiz, 0, contador);
    }

    /*
     * Método auxiliar para agregar un espacio, o cualquier otro símbolo que
     * represente una rama a nuestra cadena
     * 
     * @param int nivel en el que está el vértice
     * 
     * @param boolean [] binario arreglo para determinar si se debe agregar rama o
     * un espacio
     * 
     * @return String representación de espacios en el árbol.
     */
    private String dibujaEspacios(int nivel, boolean[] binario) {
        String s = "";
        for (int i = 0; i < nivel; i++)
            if (binario[i])
                s += "│  ";
            else
                s += "   ";

        return s;
    }

    /*
     * Método auxiliar que funciona de forma recursiva y se encarga de formar una
     * cadena con los elementos del árbol
     * 
     * @param Vertice raíz del arból con la que trabajará el método
     * 
     * @param int nivel del árbol
     * 
     * @param boolean [] arreglo binario
     * 
     * @return String representación del árbol en <code>String</code>
     */
    private String toString(Nodo<T> raiz, int nivel, boolean[] binario) {
        String s = raiz + "\n";
        binario[nivel] = true;

        if (raiz.izquierdo != null && raiz.derecho != null) {
            s += dibujaEspacios(nivel, binario);
            s += "├─›";
            s += toString(raiz.izquierdo, nivel + 1, binario);
            s += dibujaEspacios(nivel, binario);
            s += "└─»";
            binario[nivel] = false;
            s += toString(raiz.derecho, nivel + 1, binario);
        } else if (raiz.izquierdo != null) {
            s += dibujaEspacios(nivel, binario);
            s += "└─›";
            binario[nivel] = false;
            s += toString(raiz.izquierdo, nivel + 1, binario);
        } else if (raiz.derecho != null) {
            s += dibujaEspacios(nivel, binario);
            s += "└─»";
            binario[nivel] = false;
            s += toString(raiz.derecho, nivel + 1, binario);
        }

        return s;
    }

}
