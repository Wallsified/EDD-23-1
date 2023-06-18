package estacionamiento;

/**
 *
 * Clase que extiende a Vehiculo. Motocicleta tiene la particularidad de que
 * no podrán ser pensionadas.
 *
 * @author walls
 * @author gentle_earthquake
 * @version 1.0
 */
public class Motocicleta extends Vehiculo {

    // Las motocicletas no serán pensionadas.
    final boolean esPensionado = false;

    /**
     * Método constructor de Moticicleta
     * 
     * @param año    Año de la Motocicleta.
     * @param placas Número de placas de la Motocicleta.
     * @param color  Color de la Motocicleta.
     * @param marca  Marca de la Motocicleta.
     */
    public Motocicleta(int año, int placas, String color, String marca) {
        super(año, placas, color, marca);

    }

    /**
     * Métódo para revisar si el vehiculo es pensionado.
     * 
     * @return Si es pensionado o no.
     */
    @Override
    public boolean getEsPensionado() {
        return esPensionado;
    }

}
