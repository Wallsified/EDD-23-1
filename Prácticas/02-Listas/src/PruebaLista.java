import java.util.Random;

/**
 * Clase para probar las implementaciones de Lista.
 * 
 * @version 1.0
 * @author wallsified
 * 
 */
public class PruebaLista {

    /**
     * Método main para las pruebas.
     * 
     * @param args N/A
     */
    public static void main(String[] args) {

        Random rand = new Random();
        int picked;

        // Prueba #1: Agregar y quitar elementos a una lista. Imprimimos resultados.

        System.out.println("\nPrueba #1: Propiedades básicas de la lista: Añadir, Eliminar, tamaño, indice...:");
        System.out.println("\nLista #0: ");
        Lista<Double> pruebas = new Lista<Double>();
        System.out.println(pruebas);
        System.out.println("Agregamos unos cuantos elementos.");

        pruebas.agregar(7946.00);
        System.out.println(pruebas);
        pruebas.agregar(7852.25852);
        System.out.println(pruebas);
        pruebas.agregar(0.2);
        System.out.println(pruebas);

        System.out.println("Tamaño de la lista: " + pruebas.getTamanio());
        System.out.println("\nIndice de un elemento, 7946.00: " + pruebas.indiceDe(7946.00));
        System.out.println("\nElemento en la posición 0: " + pruebas.getElemento(0));
        System.out.println("\nAgregamos al inicio un elemento:");
        pruebas.agregarAlInicio(484848.2);
        System.out.println(pruebas);
        System.out.println("Agregamos al final un elemento:");
        pruebas.agregarAlFinal(4848.2);
        System.out.println(pruebas);
        System.out.println("Eliminamos elementos:");
        pruebas.eliminar(0.2);
        System.out.println(pruebas);
        pruebas.eliminar(7946.00);
        System.out.println(pruebas);
        System.out
                .println("Revisamos si un elemento existe en un la lista, por ejemplo, 4.0: " + pruebas.contiene(4.0));

        // Prueba #2: Revisamos copia, reversa, merge, listas iguales.

        System.out.println("\nPrueba #2: Copia, Reversa, Merge, Listas iguales. Damos dos lista de prueba:");
        Lista<Integer> hello = new Lista<>();
        for (int i = 1; i < 15; i++) {
            hello.agregar(i);
        }
        System.out.println("\nLista #1: " + hello);

        Lista<Integer> helloAgain = new Lista<>();
        for (int i = 20; i < 35; i++) {
            helloAgain.agregar(i);
        }
        helloAgain.agregarAlInicio(0);
        System.out.println("Lista #2: " + helloAgain);

        Lista<Integer> olleh = hello.copia(); // hello, al reves. Lista 3
        System.out.println("Sea la lista #3 la copia de Lista 1: " + olleh);

        Lista<Integer> niagAolleh = helloAgain.reversa(); // helloAgain, en reversa. Lista #4
        System.out.println("Sea la lista #4 la reversa de Lista 2: " + niagAolleh);

        Lista<Integer> noice = Lista.merge(hello, helloAgain);
        System.out.println("Sea la lista #5 la combinacion (\"merge\") de lista 1 y 2 " + noice);

        System.out.println("Veamos ahora igualdad y desigualdad de listas.\n\n¿Lista 3 es igual a Lista 1?: "
                + hello.equals(olleh));
        System.out.println("\n¿Lista 3 es igual a Lista 2?: " + hello.equals(helloAgain));

        // Prueba #3: MergeSort

        Lista<Integer> warever = new Lista<>();
        for (int i = 0; i < 10; i++) {
            picked = rand.nextInt(80);
            warever.agregar(picked);
        }

        System.out.println("\nSea la lista #6 una lista de aleatorios:  " + warever);
        System.out.println("Usamos mergeSort() para ordenar la lista.");
        Lista<Integer> wareverTwo = Lista.mergesort(warever);
        System.out.println(" \nLista Acomodada: " + wareverTwo);

    }
}
