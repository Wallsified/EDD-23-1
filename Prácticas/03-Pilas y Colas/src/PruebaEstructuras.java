/**
 * Clase de Prueba para Pila y Cola
 * 
 * @author Wallsified
 * @version 1.0
 */
public class PruebaEstructuras {

    /**
     * Método main de la Clase
     * 
     * @param args N/A
     */
    public static void main(String[] args) {

        System.out.println("\n ----- PRUEBA DE PILA ----- \n" + "\nAñadimos elementos a la Pila y la imprimimos: \n");

        Pila<Double> unaPila = new Pila<Double>();
        for (double i = 1.0; i < 10.0; i++) {
            unaPila.push(i);
        }
        System.out.println(unaPila.toString() + "\nHacemos pop() y eliminamos el primer elemento:\n ");
        unaPila.pop();
        System.out.println(unaPila.toString() + "\nHacemos push() y agregamos un elemento nuevo\n");
        unaPila.push(123456.01);
        System.out.println(unaPila.toString());
        //
        System.out.println("Vemos el primer elemento de la Pila usando top(): " + unaPila.top());

        System.out.println("\n ----- PRUEBA DE COLA ----- \n" + "\nAñadimos elementos a la Cola y la imprimimos: \n");

        Cola<Double> unaCola = new Cola<Double>();
        for (double i = 1; i < 20.0; i++) {
            unaCola.queue(i);
        }
        System.out.println(unaCola.toString() + "\nHacemos dequeue() y eliminamos el primer elemento:\n");
        unaCola.dequeue();
        System.out.println(unaCola.toString() + "\nHacemos queue() y agregamos un elemento nuevo\n");
        unaCola.queue(140.00);
        System.out.println(unaCola.toString());
        System.out.println("Vemos el primer elemento de la Cola usando peek(): " + unaCola.peek());
    }
}
