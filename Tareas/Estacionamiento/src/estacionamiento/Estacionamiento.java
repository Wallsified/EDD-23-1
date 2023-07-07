package estacionamiento;

import java.util.NoSuchElementException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Clase Estacionamiento con el funcionamiento principal del mismo.
 *
 * @author wallsified
 * @author gentle_earthquake
 * @version 1.0
 */
public class Estacionamiento {

    // Variable aleatoria para añadir tiempos aleatorios al reloj.
    Random test = new Random(System.currentTimeMillis());
    int testing2 = test.nextInt(180) + 1;

    // Total de lugares del Estacionamiento
    private final int lugaresTotales;

    // Arreglo del estacionamiento.
    private final Vehiculo[][] estacionamiento;

    // Lugares ocupados en el estacionamiento.
    private int lugaresOcupados;

    // Cantidad de filas y columnas.
    private final int n;
    private final int m;

    // Número de Ticket.
    private int contadorTicket = 1;

    /**
     * Constructor de Estacionamiento.
     *
     * @param n Cantidad de Filas
     * @param m Cantidad de Columnas
     */
    public Estacionamiento(int n, int m) {
        this.n = n;
        this.m = m;
        lugaresTotales = n * m;
        estacionamiento = new Vehiculo[n][m];
        lugaresOcupados = 0;
    }

    /**
     * Método para ingresar vehiculos al estacionamiento.
     *
     * @param veh Vehiculo a ingresar.
     */
    public void entraVehiculo(Vehiculo veh) throws ElementAlreadyExistsException {
        if (lugaresOcupados == lugaresTotales) {
            System.out.println("¡No hay espacio suficiente!\n");
        } else {

            if (!buscaVehiculo(veh)) {

                boolean a = true;
                int i = 0;
                while (a && i < n) {
                    int j = 0;
                    while (a && j < m) {
                        if (estacionamiento[i][j] == null) {
                            estacionamiento[i][j] = veh;
                            a = false;
                        }
                        j++;
                    }
                    i++;
                }
                lugaresOcupados++;
                System.out.println("El vehículo de placas " + veh.getPlacas() + " ha ingresado \n");
                imprimeEstacionamiento();
                System.out.println(regresaHora());

            } else {
                throw new ElementAlreadyExistsException(
                        "El carro con placas " + veh.getPlacas() + " ya está dentro del estacionamiento");
            }
        }
    }

    /**
     * Método para retirar vehículos del estacionamiento.
     *
     * @param veh Vehiculo a retirar
     * @throws NoSuchElementException En caso de que no exista el coche en el
     *                                estacionamiento.
     */
    public void saleVehiculo(Vehiculo veh) throws NoSuchElementException {

        if (buscaVehiculo(veh)) {
            lugaresOcupados--;
            boolean a = true;
            int i = 0;
            while (a && i < n) {
                int j = 0;
                while (a && j < m) {
                    if (estacionamiento[i][j] != null) {
                        if (estacionamiento[i][j].equals(veh)) {
                            estacionamiento[i][j] = null;
                            a = false;
                        }
                    }
                    j++;
                }
                i++;
            }
            System.out.println("El vehículo de placas " + veh.getPlacas() + " ha salido. \n");

            if (veh.getEsPensionado()) {
                System.out.println("Automovil Pensionado. No genera ticket nuevo");
            } else {
                Ticket tick = new Ticket(veh, contadorTicket);
                contadorTicket++;
            }

            imprimeEstacionamiento();
            System.out.println(regresaHora());

        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * Método privado para buscar vehiculos dentro del estacionamiento.
     *
     * @param veh Vehiculo a buscar.
     * @return Vehiculo encontrado.
     */
    private boolean buscaVehiculo(Vehiculo veh) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (estacionamiento[i][j] != null) {
                    if (veh.equals(estacionamiento[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Método privado para imprimir en pantalla el estacionamiento.
     */
    private void imprimeEstacionamiento() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (estacionamiento[i][j] != null) { // Si el lugar está ocupado.
                    System.out.print("◼ ");
                } else {
                    System.out.print("◻ "); // Lugar desocupado.
                }

            }
            System.out.println("\n");
        }

    }

    /**
     * Método privado para imprimir la hora en que se hacen los movimientos
     *
     * @return representación en cadena de la hora.
     */
    private String regresaHora() {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = "Fecha y Hora actuales: " + fechaActual.format(fechaFormato) + "\n";
        return formattedDate;
    }
}
