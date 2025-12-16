package parqueo.inteligente;

import interfaz.CapturadorSalida;

/**
 *
 * @author Jerem
 */
public class Pruebas {

    public static void pruebas() {
        CapturadorSalida capturador = new CapturadorSalida();

        
        capturador.agregarLinea("Hola");
        
        capturador.agregar("Hola");
    }

}
