package interfaz;

/**
 * Clase utilitaria para acumular texto antes de mostrarlo en pantalla.
 * Permite construir contenido de forma incremental y luego desplegarlo
 * en la pantalla de texto con formato.
 * 
 * Uso:
 * -Crear instancia: CapturadorSalida capturador = new CapturadorSalida();
 * -Agregar contenido: capturador.agregarLinea("texto");
 * -Mostrar: capturador.mostrarEnPantalla();
 * -Limpiar: capturador.limpiar();
 * 
 * @author Sistema Parqueo Inteligente
 * @version 1.0
 */
public class CapturadorSalida {
    
    /**
     * Contenido acumulado que sera mostrado en pantalla
     */
    private String contenido;
    
    /**
     * Constructor que inicializa el capturador con contenido vacio
     */
    public CapturadorSalida() {
        contenido = "";
    }
    
    /**
     * Agrega una linea de texto al contenido acumulado.
     * Automaticamente añade un salto de linea al final.
     * 
     * @param linea Texto a agregar como nueva linea
     */
    public void agregarLinea(String linea) {
        contenido += linea + "\n";
    }
    
    /**
     * Agrega texto sin salto de linea al final.
     * Util para concatenar texto en la misma linea.
     * 
     * @param texto Texto a agregar sin salto de linea
     */
    public void agregar(String texto) {
        contenido += texto;
    }
    
    /**
     * Obtiene el contenido acumulado hasta el momento.
     * 
     * @return String con todo el contenido acumulado
     */
    public String obtenerContenido() {
        return contenido;
    }
    
    /**
     * Limpia el contenido acumulado, dejandolo vacio.
     * Se debe llamar despues de mostrar en pantalla para
     * preparar el capturador para nueva informacion.
     */
    public void limpiar() {
        contenido = "";
    }
    
    /**
     * Muestra el contenido acumulado en la pantalla de texto.
     * Delega el formateo y despliegue a la clase PantallaTexto.
     * Despues de llamar este metodo, se recomienda llamar limpiar().
     */
    public void mostrarEnPantalla() {
        PantallaTexto.mostrar(contenido);
    }
}
