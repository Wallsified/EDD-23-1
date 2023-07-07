import java.util.Arrays;

/**
 * Clase Prueba de Arreglo Dinámico
 * 
 * @author wallsified
 * @version 1.0
 */

public class PruebaAD {

    /**
     * Main de la clase PruebaAD
     * 
     * @param args Este programa no lee argumentos del usario
     */
    public static void main(String[] args) {
        ArregloDinamico<Integer> arregloUno = new ArregloDinamico<>(1);
        ArregloDinamico<Integer> arregloDos = new ArregloDinamico<>(1);

        double arrayTemp[] = { 1.2, 5.8, 9.9 };
        double arrayTempTwo[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

        double arrayOneAdd[] = { 2.52, 3.84, 4.98, 5.74, 6.777 };
        double arrayTwoAdd[] = { 55.999, 7.2584516, 69871.25, 788888101540.2, 0.5 };

        System.out.println("\nCreando arregloUno. ¿El arreglo está vacio?");

        if (arregloUno.isEmpty() == true)
            System.out.println("\nSi\n");
        else
            System.out.println("\nNo\n");

        System.out.println("Añadiendo información predefinida: " + Arrays.toString(arrayTemp) + "\n");
        for (int i = 0; i < arrayTemp.length; i++) {
            arregloUno.add(arrayTemp[i]);
        }

        arregloUno.toString();
        System.out.println("El arreglo es de tamaño " + arregloUno.size() + " actualmente\n");
        System.out.println("Añadimos elementos al arreglo: " + Arrays.toString(arrayOneAdd) + "\n");

        for (int i = 0; i < arrayOneAdd.length; i++) {
            arregloUno.add(arrayOneAdd[i]);
            System.out.println("Tamaño actual: " + arregloUno.size());
        }

        arregloUno.toString();

        System.out.println("Borremos unos datos y revisemos\n");

        for (int i = arregloUno.size() - 1; i >= 4; i--) {
            arregloUno.remove(i);
            System.out.println("Tamaño actual: " + arregloUno.size() + "\n");
        }

        System.out.println("¿El arreglo está vacio?\n");

        if (arregloUno.isEmpty() == true)
            System.out.println("\nSi\n");
        else
            System.out.println("No\n");

        arregloUno.toString();
       
        // Part Two

        System.out.println("\nCreando arregloDos. ¿El arreglo está vacio?\n");

        if (arregloDos.isEmpty() == true)
            System.out.println("Si\n");
        else
            System.out.println("No");

        System.out.println("Añadiendo información predefinida: " + Arrays.toString(arrayTempTwo) + "\n");

        for (int i = 0; i < arrayTempTwo.length; i++) {
            arregloDos.add(arrayTempTwo[i]);
        }

        arregloDos.toString();
        System.out.println("El arreglo es de tamaño " + arregloDos.size() + " actualmente\n");
        System.out.println("Añadimos elementos al arreglo: " + Arrays.toString(arrayTwoAdd) + "\n");

        for (int i = 0; i < arrayTwoAdd.length; i++) {
            arregloDos.add(arrayTwoAdd[i]);
            System.out.println("Tamaño actual: " + arregloDos.size() + "\n");
        }

        arregloDos.toString();

        System.out.println("Borremos unos datos y revisemos\n");

        for (int i = arregloDos.size() - 1; i >= 9; i--) {
            arregloDos.remove(i);
            System.out.println("Tamaño actual: " + arregloDos.size() + "\n");
        }

        System.out.println("¿El arreglo está vacio?\n");
        if (arregloDos.isEmpty() == true)
            System.out.println("\nSi\n");
        else
            System.out.println("No\n");

        arregloDos.toString();

        arregloDos.removeByElement(77.0);
        arregloDos.toString();
        
}}