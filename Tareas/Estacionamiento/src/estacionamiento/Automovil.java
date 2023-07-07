package estacionamiento;

/**
 * Clase que extiende a Vehiculo y permite ser un vehiculo pensionado.
 * 
 * @author walls
 * @author gentle_earthquake
 */
public class Automovil extends Vehiculo {

    // Por defecto los coches no inician en pensión, pero serán los unicos que
    // podrán.

    /**
     * Constructor para Automovil.
     * 
     * @param año    Año del automovil.
     * @param placas Placas del automovil.
     * @param color  Color del Automovil.
     * @param marca  Marca del Automovil.
     */
    public Automovil(int año, int placas, String color, String marca) {
        super(año, placas, color, marca);
    }

    /**
     * Método para activar la pensión del Automovil.
     */
    public void activarPension() {
        this.esPensionado = true;
    }

}
