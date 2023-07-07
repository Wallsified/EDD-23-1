package Datos;

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
 * @author <a href="mailto:alejandrohmora@ciencias.unam.mx"> Alejandro Hernández
 *         Mora </a>
 * @version 1.1
 */
public class Lista<T> implements Listable<T>, Coleccionable<T> {

    /**
     * Clase interna para construir la estructura
     */
    protected class Nodo {

        /** Nodo anterior */
        protected Nodo anterior;

        /** Nodo Siguiente */
        protected Nodo siguiente;

        /**
         * El elemento que almacena un nodo
         */
        protected T elemento;

        /**
         * Unico constructor de la clase
         * 
         * @param elemento Elemento para el Nodo.
         */
        protected Nodo(T elemento) {
            this.elemento = elemento;
            this.anterior = null;
            this.siguiente = null;
        }

        /**
         * Método que compara dos Nodos de la lista.
         * 
         * @param n Nodo a comparar
         * @return Resultado de la comparación.
         */
        public boolean equals(Nodo n) {
            return this.elemento.equals(n.getElemento());
        }

        /**
         * Método que fija el siguiente elemento
         * 
         * @param sig Siguiente nodo a fijar
         */
        public void setSiguiente(Nodo sig) {
            this.siguiente = sig;
        }

        /**
         * Método que fija el elemento anterior.
         * 
         * @param ant Nodo anterior a fijar.
         */
        public void setAnterior(Nodo ant) {
            this.anterior = ant;
        }

        /**
         * Método que nos da el elemento siguiente.
         * 
         * @return elemento siguiente
         */
        public Nodo getSiguiente() {
            return siguiente;
        }

        /**
         * Método que nos da el elemento anterior.
         * 
         * @return elemento anterior
         */
        public Nodo getAnterior() {
            return anterior;
        }

        /**
         * Método que nos regresa el elemento de un Nodo.
         * 
         * @return elemento.
         */
        public T getElemento() {
            return elemento;
        }

    }

    /**
     * Clase Interna que implementa el Iterador de la Lista.
     */
    protected class IteradorLista implements Iterator<T> {
        /**
         * La lista a recorrer
         */
        protected Lista<T> lista;

        /**
         * Elementos del centinela que recorre la lista
         */
        protected Lista<T>.Nodo anterior, siguiente;

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
                return anterior.elemento;
            }
            return null;

        }

        /**
         * Método (inválido) que remueve un elemento de la lista.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operacion no valida");
        }
    }

    /* Atributos de la lista */

    /**
     * Referencias a los nodos anterior y siguiente
     */
    protected Nodo cabeza, cola;

    /**
     * Longitud de la lista.
     */
    protected int longitud;

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
            T sigElem = it.next();
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
            temp = it.next();
            posición++;
        }
        return temp;

    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * 
     * @return Una copia con la lista l revés.
     */
    public Lista<T> reversa() {
        Lista<T> reverse = new Lista<T>();
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            reverse.agregarAlInicio(it.next());
        }
        return reverse;
    }

    /**
     * Método que devuelve una copi exacta de la lista
     * 
     * @return la copia de la lista.
     */
    public Lista<T> copia() {
        Lista<T> nueva = new Lista<T>();
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            nueva.agregarAlFinal(it.next());
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
     * Método que devuelve una copia de la lista.
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
                primeraMitad.agregarAlFinal(it.next());
            else
                segundaMitad.agregarAlFinal(it.next());
            recorridos++;
        }

        return merge(mergesort(primeraMitad), mergesort(segundaMitad));

    }

    /**
     * Método para unir dos listas en una.
     * 
     * @param l1  Primera Lista
     * @param l2  Segunda Lista
     * @param <T> Posible tipo de Elementos.
     * @return Lista resultado de la unión.
     */
    public static <T extends Comparable<T>> Lista<T> merge(Lista<T> l1, Lista<T> l2) {
        Lista<T> mixed = new Lista<>();
        Iterator<T> itOne = l1.iterator();
        Iterator<T> itTwo = l2.iterator();
        T elem = itOne.next();
        T elem2 = itTwo.next();

        while (true) {
            if (elem.compareTo(elem2) < 0) {
                mixed.agregarAlFinal(elem);
                elem = itOne.next();
            } else {
                mixed.agregarAlFinal(elem2);
                elem2 = itTwo.next();
            }
            if (elem == null || elem2 == null) // hasta que alguno de las dos listas se quede sin elementos.
                break;
        }

        while (elem != null) {
            mixed.agregarAlFinal(elem);
            elem = itOne.next();
        }

        while (elem2 != null) {
            mixed.agregarAlFinal(elem2);
            elem2 = itTwo.next();
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
