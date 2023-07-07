package estacionamiento;

public class ElementAlreadyExistsException extends Exception {

    public ElementAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

}