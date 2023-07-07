/**
 * Práctica 0: Arreglo Dinámico.
 * En esta clase está configurados los comportamientos del arreglo dinámico
 * 
 * @author wallsified
 * @version 1.0
 */
public class ArregloDinamico<T> {

    private double[] arreglo;
    private int tam;

    /**
     * Constructor para ArregloDinámico
     * 
     * @param elem Un elemento para crear el arreglo y que no esté vació.
     * 
     */
    public ArregloDinamico(double elem) {
        arreglo = new double[1];
        arreglo[0] = elem;
    }

    /**
     * Método que agrega al arreglo. Por default, agregamos a la izquierda.
     * Esto es, al siguiente punto en el array.
     * 
     * @param f El elemento a añadir.
     */
    public void add(double f) {
        if (arreglo.length == tam) { // Si el arreglo está lleno. For the record, me dijeron que unicamente aqui era
                                     // válido.
            double nuevoArreglo[] = new double[2 * tam]; // Creamos uno nuevo del doble de tamaño
            for (int i = 0; i < tam; i++) { // y lo que estaba en el anterior
                nuevoArreglo[i] = arreglo[i]; // se va al nuevo
            }
            arreglo = nuevoArreglo; // y el nuevo es el de tamaño x2
        }
        arreglo[tam] = f;
        tam++;
        System.out.println(f + " fue agregado a la lista\n");
    }

    /**
     * Método que dice si el arreglo está vacio.
     * 
     * @return boolean Si el elemento tiene o no al menos un elemento.
     */
    public boolean isEmpty() {
        return tam <= 0;
    }

    /**
     * Método que ajusta el tamaño del arreglo para no tener espacios vacios.
     */
    private void ajustaTamaño() {
        double temp[] = new double[tam]; // Creamos un nuevo arreglo temporal
        int nuevoTamaño = 0; // con un nuevo tamaño temporal
        for (int i = 0; i < tam; i++) { // iteramos
            temp[i] = arreglo[i]; // cremamos una copia al nuevo arreglo.
        }
        arreglo = temp; // y lo reemplazamos.
        nuevoTamaño = tam;
        tam = nuevoTamaño;

    }

    /**
     * Método que remueve un elemento del arreglo dada su posición.
     * 
     * @param f La posición del arreglo a borrar.
     * @return double Regresa esa posición a un estado vacio (0.0).
     */
    public double remove(double f) {
        if (f > tam || tam <= 0) {
            System.out.println("No existe un elemento en esa posición");
        } else {
            for (int i = (int) f; i < tam - 1; i++) { // no existen posciones medias, entonces la pasamos a int
                                                      // momentaneamente
                arreglo[i] = arreglo[i + 1]; // movemos una posición
            }
            arreglo[tam - 1] = 0;
            if (tam < (arreglo.length / 2))
                ajustaTamaño();
            tam--; // y quitamos uno al conteo de tamaño
            System.out.println("El elemento en la posición " + (int) (f + 1) + " fue eliminado\n");
        }

        return 0.0;
    }

    /**
     * Método que borra la primera incidencia de un elemento.
     * 
     * @param elemento El elemento en cuestión a borrar
     * @return double algo aqui en lo mientras.
     * 
     */
    public double removeByElement(double elemento) {
        boolean noExiste = true;
        if (tam == 0)
            System.out.println("El arreglo está vacio. No se puede eliminar " + elemento);
        // Iteramos en el arreglo, encontramos el indice de la comparación y borramos.
        for (int i = 0; i < tam; i++) {
            if (arreglo[i] == elemento) {
                remove(i);
                noExiste = false;
                break; // Realmente sin el break quitamos todo lo repetido.
            }
        }

        if (noExiste == true)
            System.out.println("El elemento " + elemento + " no existe en el arreglo\n");
        return 0.0;
    }

    /**
     * Método que regresa el tamaño del arreglo.
     * 
     * @return int
     */
    public int size() {
        return tam;
    }

    /**
     * Método que imprime el contenido que tenemos en el arreglo.
     * 
     * @return String
     */
    @Override
    public String toString() {
        System.out.println("Los elementos en el arreglo son: ");
        for (int i = 0; i < tam; i++) { // iteramos y unimos.
            System.out.print(arreglo[i] + " ");
        }
        System.out.println("\n");
        return " \n";

    }
}
