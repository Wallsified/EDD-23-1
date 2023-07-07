import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase concreta para modelar la estructura de datos Cola
 * </p>
 * <p>
 * Esta clase implementa una Cola genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * 
 * 
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta cola.
 */
public class Cola<T> extends LinealAdapter<T> implements Encolable<T> {

    /**
     * Clase privada para el iterador sobre Colas.
     */
    private class IteradorCola implements Iterator<T> {
        private Nodo siguiente;

        private IteradorCola() {

        }

        /**
         * Nos dice si hay un elemento siguiente.
         * 
         * @return Resultado booleano de si hay siguiente o no
         */
        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        /**
         * Nos da el elemento siguiente
         * 
         * @return Elemento siguiente.
         */
        @Override
        public T next() {
            return peek();
        }

        /**
         * Método (invalido) que hereda del remove del iterador original.
         */
        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }

    /**
     * Constructor por omisión de la clase.
     */
    public Cola() {
        // Aqui no hay que hacer nada,
        // ya que los valores por default nos sirven al crear un objeto.
    }

    /**
     * Constructor que recibe un arreglo de elementos de tipo <code>T</code>.
     * Crea una cola con los elementos del arreglo.
     * 
     * @param elementos El arreglo que se recibe como parámetro.
     */
    public Cola(T[] elementos) {
        for (T hello : elementos) {
            queue(hello);
        }
    }

    /**
     * Constructor que recibe una colección de tipo {@link Coleccionable}
     * de elementos de tipo <code>T</code> y los agrega a la nueva cola.
     * 
     * @param elementos La colección de elementos a agregar.
     */
    public Cola(Coleccionable<T> elementos) {
        Iterator<T> hello = elementos.iterator();
        while (hello.hasNext()) {
            queue(hello.next());
        }
    }

    /**
     * Agrega un elemento en el rabo de la Cola.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void queue(T elemento) throws IllegalArgumentException {
        super.agregarAlFinal(elemento);

    }

    /**
     * Elimina el elemento del inicio de la Cola y lo regresa.
     * 
     * @return Elemento a eliminar.
     * @throws NoSuchElementException si la cola es vacía
     */
    public T dequeue() throws NoSuchElementException {
        return super.eliminarInicio();

    }

    /**
     * Nos permite ver el elemento en el inicio de la Cola
     *
     * @return el elemento en un extremo de la estructura.
     */
    public T peek() {
        return super.ver().getElemento();

    }

    /**
     * Método que genera una cadena de la Cola.
     * 
     * @return Visualización en cadena de la Cola
     */
    @Override
    public String toString() {
        String start = "";
        if (longitud > 0) {
            start = start + "["; // estilizar
            for (int i = 0; i < super.getTamanio(); i++) {
                if ((i + 1) == super.getTamanio()) {
                    start += super.getElemento(i) + "]\n";
                } else {
                    start += super.getElemento(i) + "] <- [";
                }
            }
        }
        return start;
    }

}
