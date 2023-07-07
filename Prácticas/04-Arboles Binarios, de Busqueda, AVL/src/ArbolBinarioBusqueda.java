import java.util.Iterator;
import datos.PilasAndColas.Pila;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase para modelar árboles binarios de búsqueda genéricos.
 * </p>
 *
 * <p>
 * Un árbol instancia de esta clase siempre cumple que:
 * </p>
 * <ul>
 * <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 * descendientes por la izquierda.</li>
 * <li>Cualquier elemento en el árbol es menor o igual que todos sus
 * descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {

  /**
   * Clase privada para iteradores de árboles binarios ordenados.
   */
  private class Iterador implements Iterator<T> {

    /**
     * Pila para recorrer los nodos en DFS in-order.
     */
    private Pila<Nodo<T>> pila;

    /**
     * Construye un iterador con el nodo recibido.
     */
    public Iterador() {
      pila = new Pila<Nodo<T>>();
      Nodo<T> base = raiz;
      while (base != null) {
        pila.push(base);
        base = base.izquierdo;
      }
    }

    /**
     * Nos dice si hay un elemento siguiente.
     */
    @Override
    public boolean hasNext() {
      return !pila.esVacia();
    }

    /**
     * Regresa el siguiente elemento en orden DFS in-order.
     */
    @Override
    public T next() {
      if (pila.esVacia())
        throw new NoSuchElementException("Pila Vacía!");
      Nodo<T> n = pila.pop();
      Nodo<T> derecho = n.derecho;
      while (derecho != null) {
        pila.push(derecho);
        derecho = derecho.izquierdo;
      }
      return n.elemento;
    }
  }

  /**
   * Constructor que no recibe parámeteros.
   */
  public ArbolBinarioBusqueda() {

  }

  /**
   * Construye un árbol binario ordenado a partir de una colección. El árbol
   * binario ordenado tiene los mismos elementos que la colección recibida.
   * 
   * @param coleccion la colección a partir de la cual creamos el árbol
   *                  binario ordenado.
   */

  public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
    super(coleccion);
  }

  /**
   * Método auxiliar para agregar a los árboles.
   * De cierta forma, es la parte recursiva del método.
   * 
   * @param n     Nodo desde el cual se agrega.
   * @param nuevo Nodo a agregar al árbol
   */
  protected void agregarNodo(Nodo<T> n, Nodo<T> nuevo) {
    if (n.elemento.compareTo(nuevo.elemento) > 0) {
      if (n.hayIzquierdo())
        agregarNodo(n.izquierdo, nuevo);
      else {
        n.izquierdo = nuevo;
        nuevo.padre = n;
      }
    } else {
      if (n.hayDerecho())
        agregarNodo(n.derecho, nuevo);
      else {
        n.derecho = nuevo;
        nuevo.padre = n;
      }
    }
  }

  /**
   * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
   * 
   * @param elemento el elemento a agregar.
   */
  @Override
  public void agregar(T elemento) {
    Nodo<T> nuevo = nuevoNodo(elemento);
    if (raiz == null) {
      raiz = nuevo;
    } else
      agregarNodo(raiz, nuevo);

    tamanio++;

  }

  /**
   * Método auxiliar para eliminar un nodo, esto considerando una
   * referencia previa al nodo en cuestión.
   * 
   * @param n Nodo previamente con referencia a eliminar.
   * @return Nodo a eliminar.
   */
  protected Nodo<T> eliminarNodo(Nodo<T> n) {
    if (n == null || this.raiz == null)
      return null;
    Nodo<T> max = maximoEnSubarbolIzquierdo(n);
    intercambio(max, n);
    if (max.padre == null) {
      this.raiz = null;
      tamanio = 0;
      return null;
    }
    max.padre.derecho = max.izquierdo;
    if (max.izquierdo != null) {
      max.izquierdo.padre = max.padre;
    }
    tamanio--;
    return max;

  }

  /**
   * Método auxiliar que intercambia los elementos de los nodos.
   * Es un swap en escencia.
   * 
   * @param n Primer nodo en el intercambio.
   * @param m Segundo nodo en el intercambio.
   */
  private void intercambio(Nodo<T> n, Nodo<T> m) {
    T aux = n.elemento;
    m.elemento = n.elemento;
    n.elemento = aux;
  }

  /**
   * Método que encuentra el elemento máximo en el subárbol izquierdo
   * 
   * @param n Nodo desde el cual parte la búsqueda.
   * @return Nodo con el elemento máximo.
   */
  protected Nodo<T> maximoEnSubarbolIzquierdo(Nodo<T> n) {
    if (n.izquierdo == null)
      return n;
    Nodo<T> aux = n.izquierdo;
    while (aux.derecho != null) {
      aux = aux.derecho;
    }
    return aux;
  }

  /**
   * Método que busca un a elemento en el árbol desde el nodo n
   * 
   * @param n        Nodo desde el cual se busca.
   * @param elemento elemento a buscar en el árbol.
   * @return Nodo encontrado (o no).
   */
  protected Nodo<T> buscaNodo(Nodo<T> n, T elemento) {
    if (n == null)
      return null;
    if (n.elemento.compareTo(elemento) == 0) {
      return n;
    } else if (n.elemento.compareTo(elemento) > 0) {
      return buscaNodo(n.izquierdo, elemento);
    }
    return buscaNodo(n.derecho, elemento);
  }

  /**
   * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
   * está varias veces, elimina el primero que encuentre (in-order). El árbol
   * conserva su orden in-order.
   * 
   * @param elemento el elemento a eliminar.
   */
  @Override
  public void eliminar(T elemento) {

    if (elemento != null) {
      Nodo<T> n = buscaNodo(raiz, elemento);
      if (n != null)
        eliminarNodo(n);
      else
        System.out.println("No existe el elemento en el árbol");
    }
  }

  /**
   * Nos dice si un elemento está contenido en el arbol.
   * 
   * @param elemento el elemento que queremos verificar si está contenido en
   *                 la arbol.
   * @return <code>true</code> si el elemento está contenido en el arbol,
   *         <code>false</code> en otro caso.
   */
  @Override
  public boolean contiene(T elemento) {
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
   * Gira el árbol a la derecha sobre el nodo recibido. Si el nodo no
   * tiene hijo izquierdo, el método no hace nada.
   * 
   * @param nodo el nodo sobre el que vamos a girar.
   */
  public void giraDerecha(Nodo<T> nodo) {
    if (nodo.izquierdo == null)
      return;
    Nodo<T> izq = nodo.izquierdo;
    Nodo<T> abu = nodo.padre;
    Nodo<T> sob = izq.derecho;

    nodo.izquierdo = sob;
    if (sob != null) {
      sob.padre = nodo;
    }
    izq.derecho = nodo;
    if (esHijoIzquierdo(nodo)) {
      abu.izquierdo = izq;
      izq.padre = abu;
    } else if (esHijoDerecho(nodo)) {
      abu.derecho = izq;
      izq.padre = abu;
    } else {
      this.raiz = izq;
      izq.padre = null;
    }
    nodo.padre = izq;
  }

  /**
   * Gira el árbol a la izquierda sobre el nodo recibido. Si el nodo no
   * tiene hijo derecho, el método no hace nada.
   * 
   * @param nodo el nodo sobre el que vamos a girar.
   */
  public void giraIzquierda(Nodo<T> nodo) {

    if (nodo.derecho == null)
      return;
    Nodo<T> der = nodo.derecho;
    Nodo<T> abu = nodo.padre;
    Nodo<T> sob = der.izquierdo;

    nodo.derecho = sob;
    if (sob != null)
      sob.padre = nodo;
    der.izquierdo = nodo;
    if (esHijoDerecho(nodo)) {
      abu.derecho = der;
      der.padre = abu;
    } else if (esHijoIzquierdo(nodo)) {
      abu.izquierdo = der;
      der.padre = abu;
    } else {
      this.raiz = der;
      der.padre = null;
    }
    nodo.padre = der;

  }

  /**
   * Método auxiliar para verificar el hijo izquierdo de un nodo
   * 
   * @param n Nodo desde el que se hace la comparación.
   * @return <code>true</code> si es el hijo izquierdo,
   *         <code>false</code> en otro caso.
   */
  private boolean esHijoIzquierdo(Nodo<T> n) {
    return n.padre != null && n.padre.izquierdo == n;
  }

  /**
   * Método auxiliar para verificar el hijo derecho de un nodo
   * 
   * @param n Nodo desde el que se hace la comparación.
   * @return <code>true</code> si es el hijo derecho,
   *         <code>false</code> en otro caso.
   */
  private boolean esHijoDerecho(Nodo<T> n) {
    return n.padre != null && n.padre.derecho == n;
  }

  /**
   * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
   * 
   * @return un iterador para iterar el árbol.
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterador();
  }

  /**
   * Método toString sobrecargando la herencia.
   * 
   * @return Cadena del Aŕbol Binario de Búsqueda.
   */
  @Override
  public String toString() {
    return super.toString();
  }

}
