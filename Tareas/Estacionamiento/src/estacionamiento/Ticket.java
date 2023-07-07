package estacionamiento;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase generadora de los tickets del estacionamiento
 *
 * @author walls
 * @author gentle_earthquake
 */
public class Ticket {

    Random test = new Random(System.currentTimeMillis());
    int pagadoCon = test.nextInt(450) + 1;

    // Variable usada para calcular el cobro.
    private int cobro;

    // Variable para contabilizar dos horas de tiempo y el tiempo transcurrido de
    // entrada a salida.
    private double dosHoras = 120.0, tiempoTranscurrido;

    /**
     * Constructor de Ticket.
     *
     * @param veh      Vehiculo del cual se realizará el ticket.
     * @param contador Número de Ticket.
     */
    public Ticket(Vehiculo veh, int contador) {
        try {
            generaTicket(veh, contador);
        } catch (IOException ex) {
            System.out.println("Hubo un error generando el ticket");
        }
    }

    /**
     * Método para revisar si el vehiculo es pensionado y ajustar su cobro.
     *
     * @param veh Vehiculo a revisar.
     * @return Cadena sobre el resultado de si es o no pensionado.
     */
    private String revisarPension(Vehiculo veh) {
        if (veh.getEsPensionado() == true) {
            cobro = 750;
            return "El vehiculo es pensionado. Aplica precio fijo.";
        } else {
            return "El vehículo no tiene una pensíon activa";
        }
    }

    /**
     * Método que calcula el cobro que se hará en el Ticket.
     *
     * @param veh Vehiculo en cuestión
     * @return Total del cobro.
     */
    private int cobro(Vehiculo veh) {
        revisarPension(veh);
        tiempoTranscurrido = veh.timeElapsed();
        if (tiempoTranscurrido <= dosHoras && !veh.getBoletoPerdido()) {
            cobro = 10;
        } else if (veh.getBoletoPerdido() == true) {
            cobro = 350;
        } else if (tiempoTranscurrido > dosHoras && !veh.getBoletoPerdido()) {
            int a = (int) (tiempoTranscurrido - dosHoras);
            a /= 15;
            a++;
            cobro = 15 * a + 10;
        }

        return cobro;
    }

    /**
     * Método que calcula el cambio del ticket.
     * 
     * @param cobrar cobro del ticket.
     * @return cambio a entregar.
     */
    public double darCambio(int cobrar) {
        return pagadoCon - cobrar;
    }

    /**
     * Métódo que genera el ticket.
     *
     * @param veh     Vehiculo del cual se generará el ticket.
     * @param counter Número de Ticket
     * @return Ticket en formato .txt generado como archivo externo.
     * @throws IOException En caso de que el archivo no se pueda generar.
     */
    public FileWriter generaTicket(Vehiculo veh, int counter) throws IOException {
        FileWriter ticket = new FileWriter("Ticket" + counter + ".txt");
        String startTicket = "";
        startTicket += veh.infoToText();
        startTicket += "Tiempo estacionado: " + veh.timeElapsed() + " minutos. \n";
        startTicket += "Tarifa a Pagar: $ " + cobro(veh) + " M.N \n";
        startTicket += "Se pago con: $ " + pagadoCon + " M.N\n";
        startTicket += "Cambio al cliente: $ " + darCambio(cobro) + "\n";
        ticket.write(startTicket);
        ticket.close();
        System.out.println("El ticket fue impreso en un nuevo archivo de texto.");
        return ticket;
    }
}
