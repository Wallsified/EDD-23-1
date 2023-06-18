import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase concreta para modelar la estructura de datos Pila
 * </p>
 * <p>
 * Esta clase implementa una Pila genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * 
 * @author <a href="mailto:alejandrohmora@ciencias.unam.mx"> Alejandro Hernández
 *         Mora </a>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta pila.
 */
public class Pila<T> extends LinealAdapter<T> implements Apilable<T> {

    /**
     * Constructor por omisión de la clase;
     */
    public Pila() {
    }

    /**
     * Constructor que recibe un arreglo de elementos de tipo <code>T</code>.
     * Crea una pila donde el primer elemento del arreglo es el que queda al
     * fondo de la pila, el último elemento del arreglo queda en el tope
     * de la pila.
     * 
     * @param elementos Elementos para construir la Pila.
     */
    public Pila(T[] elementos) {
        for (T hello : elementos)
            push(hello);
    }

    /**
     * Constructor que recibe una colección de tipo {@link Coleccionable}
     * de elementos de tipo <code>T</code>.
     * Crea una pila donde el primer elemento de la colección es el que queda al
     * fondo de la pila, el último elemento de la colección queda en el tope
     * de la pila.
     * 
     * @param elementos La colección de elementos a agregar.
     */
    public Pila(Coleccionable<T> elementos) {
        Iterator<T> hello = elementos.iterator();
        while (hello.hasNext()) {
            push((T) hello.next());
        }
    }

    /**
     * Constructor de la clase, que recibe una pila y regresa una copia
     * exacta de ésta.
     * 
     * @param pila La pila que se va a copiar.
     */
    public Pila(Pila<T> pila) {
        super.copia();
    }

    /**
     * Agrega un elemento al tope de la pila.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void push(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException("No puedes meter nulls a la Pila");
        super.agregarAlInicio(elemento);
    }

    /**
     * Elimina el elemento del tope de la pila y lo regresa.
     * 
     * @throws NoSuchElementException si la pila es vacía.
     */
    public T pop() throws NoSuchElementException {
        return super.eliminarInicio();
    }

    /**
     * Nos permite ver el elemento en el tope de la pila
     *
     * @return el elemento en un extremo de la estructura.
     */
    public T top() {
        return super.ver().getElemento();
    }

    /**
     * Método que genera una cadena de la pila.
     * 
     * @return Visualización en cadena de la Pila
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
                    start += super.getElemento(i) + "] -> [";
                }
            }
        }
        return start;
    }

}
