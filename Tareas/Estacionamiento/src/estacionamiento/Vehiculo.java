package estacionamiento;

import java.time.Instant;
import java.time.Duration;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Random;

/**
 * Clase general de características de vehiculo.
 *
 * @author walls
 * @author gentle_earthquake
 * @version 1.0
 */
public class Vehiculo {

    // Variable aleatoria para añadir tiempo al reloj.
    Random test = new Random(System.currentTimeMillis());
    int testing2 = test.nextInt(150) + 20;

    // formato para imprimir hora de salida y entrada.
    DateTimeFormatter formato = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.US)
            .withZone(ZoneId.systemDefault());

    // Caracterísiticas básicas del vehículo.
    private final int año;
    private final int placas;
    private final String marca;
    private final String color;

    // Tiempo estacionado.
    private long tiempoFinal;

    // Booleano sobre ticket perdido
    protected boolean boletoPerdido = false;

    // Boolean sobre si es o no pensionado el vehiculo.
    protected boolean esPensionado = false;

    // Momentos de iniciación.
    private Instant starts, ends;
    private String horaEntrada;
    private String horaSalida;

    /**
     * Constructor de Vehiculo
     * 
     * @param año    Año del Vehiculo.
     * @param placas Placas del Vehiculo.
     * @param color  Color del Vehiculo.
     * @param marca  Marca del Vehiculo.
     */
    public Vehiculo(int año, int placas, String color, String marca) {
        Random random = new Random();
        int u = random.nextInt(8);
        if (u == 0) {
            this.boletoPerdido = true;
        }
        this.starts = Instant.now();
        horaEntrada = formato.format(starts);
        this.año = año;
        this.placas = placas;
        this.color = color;
        this.marca = marca;
    }

    /**
     * Getter para conocer el estatus de pensión del Automovil.
     * 
     * @return Si el automovil es o no pensionado.
     */
    public boolean getEsPensionado() {
        return esPensionado;
    }

    /**
     * Método que calcula cuanto tiempo estuvo estacionado un vehículo.
     * 
     * @return Cantidad de minutos que estuvo el vehiculo estacionado.
     */
    protected long timeElapsed() {
        this.ends = Instant.now();
        horaSalida = formato.format(this.ends);
        Duration tiempoEstacionado = Duration.between(starts, ends);
        testing2 += 15;
        return this.tiempoFinal = tiempoEstacionado.plusMinutes(testing2).toMinutes();
    }

    /**
     * Getter de las placas del vehículo.
     * 
     * @return Placas.
     */
    public int getPlacas() {
        return placas;
    }

    /**
     * Getter del posible boleto perdido.
     * 
     * @return Status del boleto, perdido o no.
     */
    public boolean getBoletoPerdido() {
        return boletoPerdido;
    }

    /**
     * Método que nos genera una cadena en caso de tener o no el boleto perdido.
     * 
     * @return Cadena sobre el resultado de getBoletoPerdido
     */
    private String lost() {
        if (getBoletoPerdido() == true)
            return "El boleto de este vehiculo fue perdido.";
        else
            return "Boleto en Mano.";
    }

    /**
     * Nuestro método toString para imprimir la información del vehiculo en una
     * cadena.
     * 
     * @return información en cadena del vehiculo.
     */

    protected String infoToText() {
        timeElapsed();
        String information = "";
        information += "Información del Vehículo: \n";
        information += "Tipo de Vehiculo: " + tipoDeVehiculo(this);
        information += "Fecha y Hora de Entrada: " + horaEntrada + "\n";
        information += "Modelo: " + this.marca + ", " + this.color + "\n";
        information += "Año del Vehiculo: " + this.año + "\n";
        information += "Placas del Vehiculo: " + this.placas + "\n";
        information += "Fecha y Hora de Salida: " + horaSalida + "\n";
        information += "Status del boleto: " + lost() + "\n";
        return information;
    }

    /**
     * Método para saber el tipo de Vehiculo del que se trata.
     * 
     * @param veh Vehiculo a analizar.
     * @return Cadena sobre el tipo de vehículo.
     */
    private String tipoDeVehiculo(Vehiculo veh) {
        if (veh instanceof Automovil) {
            return "Automovil\n";
        } else
            return "Motocicleta\n";
    }

    /**
     * Método para comparar si dos automoviles son o no, el mismo
     * 
     * @param vehiculo Vehiculo en cuestión.
     * @return Si en efecto dos vehiculos son iguales.
     */
    public boolean equals(Vehiculo vehiculo) {
        return vehiculo.placas == placas;
    }

}