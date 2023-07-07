import java.util.Random;

/**
 * Clase principal para probar árboles binarios de búsqueda y AVL.
 * 
 * @version 1.0
 * @author Wallsified
 */
public class PruebasArbol {

  /**
   * Método principal a ejecutar.
   * 
   * @param args No Aplican argumentos de inicio en este caso.
   */
  public static void main(String[] args) {

    ArbolBinarioBusqueda<Integer> hello = new ArbolBinarioBusqueda<>();
    Random randal = new Random();

    System.out.print(" \n-------- Pruebas Árboles Binarios de Búsqueda --------\n");
    System.out.print(" \n-------- Pruebas de Agregación de elementos --------\n" +
        "\nAgregamos valores aleatorios al árbol del 1 al 35\n\n");
    hello.agregar(8);
    for (int i = 0; i < 10; i++) {
      hello.agregar(randal.nextInt(35));
    }
    System.out.println(hello + "\nAgregamos un elemento fijo, 100: \n");
    hello.agregar(100);
    System.out.println(hello +
        "\n -------- Pruebas de Contención -------- \n\nEl elemento 100 se encuentra en el arbol?: "
        + hello.contiene(100));
    System.out.println("\nEl elemento 1 se encuentra en el arbol?: " + hello.contiene(1));
    System.out.println("\n-------- Pruebas de eliminación --------\n\nEliminamos el elemento 100:\n");
    hello.eliminar(100);
    System.out.print(hello);
    System.out.println("\nEliminamos la raiz:" + hello.raiz.elemento + "\n");
    hello.eliminar(hello.raiz.elemento);
    System.out.print(hello);

    System.out.print("\n -------- Pruebas de Tamaño --------  \n\nSegún la propiedad de tamaño hay: " + hello.tamanio
        + " elementos\n");

    System.out.println("\n -------- Pruebas de Rotación --------\n" + "\nRotamos a la izquierda: \n ");
    hello.giraIzquierda(hello.raiz);
    System.out.print(hello);
    System.out.print("\nRotamos el arbol a la derecha: \n");
    hello.giraDerecha(hello.raiz);
    System.out.print(hello);

    System.out.println("\n -------- Pruebas de Ordenes --------\n\n" + "Árbol en inorden : " + hello.inOrden() +
        "\n\nArbol en preorden: " + hello.preOrden() + "\n\nArbol en postorden: " + hello.postOrden());

    System.out.print(" \n\n-------- Pruebas Árboles AVL --------\n\n");
    ArbolAVL<Integer> helloAVL = new ArbolAVL<>();
    System.out.print("\nGeneramos un árbol AVL de valores aleatorios que se autobalancea: \n");

    for (int i = 0; i < 10; i++) {
      helloAVL.agregar(randal.nextInt(100));
    }
    System.out.println(helloAVL);

    System.out.println("\nAgregamos un elemento fijo, 15: \n");
    helloAVL.agregar(15);
    System.out.println(helloAVL);

    System.out.println("\n -------- Pruebas de Contención -------- \n\nEl elemento 15 se encuentra en el arbol?: "
        + helloAVL.contieneAVL(15));
    System.out.println("\nEl elemento 1 se encuentra en el arbol?: " + helloAVL.contieneAVL(1));
    System.out
        .println("\n-------- Pruebas de eliminación --------\n\nEliminamos la raiz:" + helloAVL.raiz.elemento + "\n");
    helloAVL.eliminar(helloAVL.raiz.elemento);
    System.out.print(helloAVL);

    System.out.println("\n -------- Pruebas de Ordenes --------\n\n" + "Árbol en inorden : " + helloAVL.inOrden() +
        "\n\nArbol en preorden: " + helloAVL.preOrden() + "\n\nArbol en postorden: " + helloAVL.postOrden());

  }

}
