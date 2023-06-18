import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

/**
 * <p>
 * Clase concreta para modelar la estructura de datos Lista
 * </p>
 * <p>
 * Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * 
 * @author <a href="mailto:alejandrohmora@ciencias.unam.mx">Alejandro Hernández
 *         Mora</a>
 * @version 1.1
 */
public class Lista<T> implements Listable<T>, Coleccionable<T> {

    /** Clase interna para construir la estructura */
    private class Nodo {
        /** Referencias a los nodos anterior y siguiente */
        public Nodo anterior, siguiente;
        /** El elemento que almacena un nodo */
        public T elemento;

        /** Unico constructor de la clase */
        public Nodo(T elemento) {
            this.elemento = elemento;
            this.anterior = null;
            this.siguiente = null;
        }

        /**
         * Setter para el siguiente nodo.
         * 
         * @param sig Siguiente Nodo.
         */
        public void setSiguiente(Nodo sig) {
            this.siguiente = sig;
        }

        /**
         * Setter para el nodo anterior.
         * 
         * @param ant Nodo anterior.
         */
        public void setAnterior(Nodo ant) {
            this.anterior = ant;
        }

        /**
         * Getter del nodo siguiente
         * 
         * @return Nodo siguiente
         */
        public Nodo getSiguiente() {
            return siguiente;
        }

        /**
         * Getter del elemento del nodo.
         * 
         * @return Elemento del nodo
         */
        public T getElemento() {
            return elemento;
        }
    }

    private class IteradorLista implements Iterator<T> {
        /* La lista a recorrer */
        private Lista<T> lista;
        /* Elementos del centinela que recorre la lista */
        private Lista<T>.Nodo anterior, siguiente;

        /**
         * Constructor de IteradorLista
         * 
         * @param lista La lista sobre la cual se iterará.
         */
        public IteradorLista(Lista<T> lista) {
            this.lista = lista;
            siguiente = lista.cabeza;
            anterior = null;
        }

        /**
         * Método para saber si existe un elemento siguiente en la lista.
         * 
         * @return Si tiene o no un elemento siguiente.
         */
        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        /**
         * Método que regresa el siguiente elemento en la lista.
         * 
         * @return El elemento en cuestión.
         */
        @Override
        public T next() {
            if (this.hasNext()) {
                this.anterior = this.siguiente;
                this.siguiente = this.siguiente.getSiguiente();
                return anterior.getElemento();
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operacion no valida");
        }
    }

    /* Atributos de la lista */
    private Nodo cabeza, cola;
    private int longitud;

    /**
     * Constructor Lista vacia.
     */
    public Lista() {
        this.cabeza = null;
        this.cola = null;
        longitud = 0;
    }

    /**
     * Constructor que recibe un elemento de inicio.
     * 
     * @param elem El elemento para inicializar.
     */
    public Lista(T elem) {
        Nodo nuevo = new Nodo(elem);
        this.cabeza = nuevo;
        this.cola = nuevo;
        this.longitud = 1;
    }

    /**
     * Constructor que recibe un nodo inicial.
     * 
     * @param cabeza Nodo cabeza para iniciar la lista.
     */
    protected Lista(Nodo cabeza) {
        this.cabeza = cabeza;
        this.cola = cabeza;
        longitud = 1;
    }

    /**
     * Constructor que recibe un nodo inicial y uno final.
     * 
     * @param cabeza Nodo inicial.
     * @param cola   Nodo final.
     */
    protected Lista(Nodo cabeza, Nodo cola) {
        this.cabeza = cabeza;
        this.cola = cola;
        this.longitud = 2;
    }

    /**
     * Método que nos dice si las lista está vacía.
     * 
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     *         en otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar() {
        cabeza = null;
        cola = null;
        longitud = 0;
    }

    /**
     * Método para obtener el tamaño de la lista
     * 
     * @return tamanio Número de elementos de la lista.
     **/
    public int getTamanio() {
        return this.longitud;
    }

    /**
     * Método para agregar un elemento a la lista.
     * 
     * @param elemento Objeto que se agregará a la lista.
     */
    public void agregar(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException();
        agregarAlFinal(elemento);
    }

    /**
     * Método para agregar al inicio un elemento a la lista.
     * 
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlInicio(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (esVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        longitud++;
    }

    /**
     * Método para agregar al final un elemento a la lista.
     * 
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (esVacia()) {
            cola = nuevo;
            cabeza = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
        longitud++;
    }

    /**
     * Método para verificar si un elemento pertenece a la lista.
     * 
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro
     *         caso.
     */
    public boolean contiene(T elemento) {
        boolean bandera = false;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            T sigElem = (T) it.next();
            if (sigElem.equals(elemento)) {
                bandera = true;
                return true;
            }
        }
        return bandera;
    }

    /**
     * Método auxiliar que busca un nodo en la lista dado un elemento.
     * 
     * @param elem El elemento que debe de tener el nodo.
     * @return Nodo con el elemento buscado.
     */
    private Nodo buscaNodo(T elem) {
        if (elem == null)
            return null;
        Nodo aux = cabeza;
        while (aux != null && !aux.elemento.equals(elem))
            aux = aux.siguiente;
        return aux;
    }

    /**
     * Método para eliminar un elemento de la lista.
     * 
     * @param elemento Objeto que se eliminara de la lista.
     */
    public void eliminar(T elemento) throws NoSuchElementException {
        if (esVacia() || elemento == null)
            return;
        Nodo aux = buscaNodo(elemento); // Divide y vencerás i guess..
        if (getTamanio() == 1)
            vaciar();
        else if (aux == cabeza) {
            aux.siguiente.anterior = null;
            cabeza = aux.siguiente;
        } else if (aux == cola) {
            aux.anterior.siguiente = null;
            cola = aux.anterior;
        } else {
            aux.anterior.siguiente = aux.siguiente;
            aux.siguiente.anterior = aux.anterior;
        }

        longitud--;
    }

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * 
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en
     *         ésta.
     */
    public int indiceDe(T elemento) {
        int index = 0;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            if (elemento.equals(it.next()))
                return index;
            index++;
        }
        return -1;
    }

    /**
     * Método que nos dice en qué posición está un elemento en la lista
     * 
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     *         <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es menor a 0 o mayor a
     *                                   longitud()
     */
    public T getElemento(int i) throws IndexOutOfBoundsException {
        if (longitud <= i)
            throw new IndexOutOfBoundsException(); // meter esto en un try-catch?
        Iterator<T> it = this.iterator();
        int posición = 0;
        T temp = null;
        while (posición < i + 1) {
            temp = (T) it.next();
            posición++;
        }
        return temp;

    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * 
     * @return Una copia con la lista alrevés.
     */
    public Lista<T> reversa() {
        Lista<T> reverse = new Lista<T>();
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            reverse.agregarAlInicio((T) it.next());
        }
        return reverse;
    }

    /**
     * Método que devuelve una copia exacta de la lista
     * 
     * @return la copia de la lista.
     */
    public Lista<T> copia() {
        Lista<T> nueva = new Lista<T>();
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            nueva.agregarAlFinal((T) it.next());
        }
        return nueva;
    }

    /**
     * Método que nos dice si una lista es igual que otra.
     * 
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        Iterator<T> it = this.iterator();
        boolean tryThis = false;
        while (it.hasNext()) {
            if (!o.equals(it.next())) {
                tryThis = false;
            } else
                tryThis = true;
        }
        return tryThis;
    }

    /**
     * Método que devuelve un iterador sobre la lista
     * 
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return new IteradorLista(this);
    }

    /**
     * Algoritmo MergeSort.
     * 
     * @param <T> Debe ser un tipo que extienda Comparable, para poder distinguir
     *            el orden de los elementos en la lista.
     * @param l   La lista de elementos comparables.
     * @return copia de la lista ordenada.
     */
    public static <T extends Comparable<T>> Lista<T> mergesort(Lista<T> l) {

        if (l.getTamanio() == 0 || l.getTamanio() == 1) {
            return l.copia(); // ordenada al ser 0 o 1 elemento.
        }

        Lista<T> primeraMitad = new Lista<>();
        Lista<T> segundaMitad = new Lista<>();
        Iterator<T> it = l.iterator();
        int lenght = l.getTamanio(), recorridos = 0;

        while (it.hasNext()) {
            if (recorridos < (lenght / 2))
                primeraMitad.agregarAlFinal((T) it.next());
            else
                segundaMitad.agregarAlFinal((T) it.next());
            recorridos++;
        }

        return merge(mergesort(primeraMitad), mergesort(segundaMitad));
    }

    /**
     * Método para unir dos listas en una.
     * 
     * @param l1 Primera Lista
     * @param l2 Segunda Lista
     * @return Lista resultado de la unión.
     */
    public static <T extends Comparable<T>> Lista<T> merge(Lista<T> l1, Lista<T> l2) {
        Lista<T> mixed = new Lista<>();
        Iterator<T> itOne = l1.iterator();
        Iterator<T> itTwo = l2.iterator();
        T elem = (T) itOne.next();
        T elem2 = (T) itTwo.next();

        while (true) {
            if (elem.compareTo(elem2) < 0) {
                mixed.agregarAlFinal(elem);
                elem = (T) itOne.next();
            } else {
                mixed.agregarAlFinal(elem2);
                elem2 = (T) itTwo.next();
            }
            if (elem == null || elem2 == null) // hasta que alguno de las dos listas se quede sin elementos.
                break;
        }

        while (elem != null) {
            mixed.agregarAlFinal(elem);
            elem = (T) itOne.next();
        }

        while (elem2 != null) {
            mixed.agregarAlFinal(elem2);
            elem2 = (T) itTwo.next();
        }

        return mixed;
    }

    /**
     * Método para imprimir nuestra Lista en la terminal.
     * 
     * @return Lista en forma cadena.
     */
    @Override
    public String toString() {
        String start = "";
        if (longitud > 0) {
            start = start + "["; // estilizar
            for (int i = 0; i < getTamanio(); i++) {
                if ((i + 1) == getTamanio()) {
                    start += getElemento(i) + "]\n";
                } else {
                    start += getElemento(i) + "] <-> [";
                }
            }
        }
        return start;
    }
}
