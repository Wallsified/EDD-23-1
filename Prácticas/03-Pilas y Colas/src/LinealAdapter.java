
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase abstracta para modelar la estructura de datos pila y cola
 * </p>
 * <p>
 * Esta clase implementa una Cola genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * 
 * 
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta cola.
 */
public abstract class LinealAdapter<T> extends Lista<T> {

    /**
     * Método no soportado para eliminar un elemento de la estructura.
     * 
     * @param elemento Elemento a intentar eliminar.
     */
    @Override
    public void eliminar(T elemento) {
        throw new UnsupportedOperationException("Operacion no valida");
    }

    /**
     * Método para eliminar el elemento del inicio de la estructura.
     * 
     * @return Elemento eliminado.
     * @throws NoSuchElementException En caso de ser una estructura vacía.
     */
    protected T eliminarInicio() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Estructura Vacía");
        T aux = cabeza.elemento;
        super.eliminar(cabeza.elemento);

        return aux;
    }

    /**
     * Método que nos deja ver un Nodo consecuente.
     * 
     * @return Nodo en cuestión a revisar.
     * @throws NoSuchElementException En caso de ser una estructura vacía.
     */
    protected Nodo ver() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Estructura Vacía");
        return cabeza;
    }

}