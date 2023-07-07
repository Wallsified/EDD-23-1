package estacionamiento;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Clase de Pruebas para Estacionamiento.
 *
 * @author walls
 * @author gentle_earthquake
 */
public class PruebaEstacionarse {

    /**
     * @param args Nada en particular en este caso.
     */
    public static void main(String[] args) {

        Random test = new Random(System.currentTimeMillis());
        int testing2 = test.nextInt(10) + 1;
        int testing = test.nextInt(10) + 1;

        Estacionamiento a = new Estacionamiento(testing, testing2);

        Automovil v1 = new Automovil(2003, 841165, "Negro", "Pasos");
        Automovil v2 = new Automovil(2006, 4532, "Axul", "Osos");
        Automovil v3 = new Automovil(2006, 4532, "Axul", "Osos");
        Motocicleta v4 = new Motocicleta(2010, 12311, "Blanca", "Italika");
        Motocicleta v5 = new Motocicleta(2023, 5423242, "Café Obscuro", "KIA");

        try {
            a.entraVehiculo(v1);
            // v1.activarPension();
        } catch (ElementAlreadyExistsException e) {
        }

        try {
            a.entraVehiculo(v2);
        } catch (ElementAlreadyExistsException e) {
        }

        try {
            a.saleVehiculo(v1);
            v1.activarPension();
        } catch (NoSuchElementException e) {
        }

        try {
            a.entraVehiculo(v3);
        } catch (ElementAlreadyExistsException e) {
        }

        try {
            a.entraVehiculo(v4);
        } catch (ElementAlreadyExistsException e) {
        }

        try {
            a.saleVehiculo(v4);
        } catch (NoSuchElementException e) {

        }

        try {
            a.entraVehiculo(v5);
        } catch (ElementAlreadyExistsException e) {

        }

        try {
            a.saleVehiculo(v2);
        } catch (NoSuchElementException e) {
        }

        try {
            a.entraVehiculo(v1);
        } catch (ElementAlreadyExistsException e) {

        }

        try {
            a.saleVehiculo(v1);
        } catch (NoSuchElementException e) {
        }
    }

}
