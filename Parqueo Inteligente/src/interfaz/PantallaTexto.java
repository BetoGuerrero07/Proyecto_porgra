package interfaz;

/**
 * Clase utilitaria para desplegar texto en una pantalla con bordes.
 * Crea una interfaz visual de texto con dimensiones fijas y
 * formato automatico para el contenido.
 *
 * Uso: PantallaTexto.mostrar(texto);
 * 
 * Caracteristicas:
 * - Ancho fijo de 50 caracteres
 * - Alto de 25 lineas
 */
public class PantallaTexto {
    
    /**
     * Ancho total de la pantalla en caracteres (incluye bordes)
     */
    private static final int ANCHO = 50;
    
    /**
     * Alto total de la pantalla en lineas (incluye bordes)
     */
    private static final int ALTO = 25;
    
    /**
     * Muestra texto en una pantalla con formato y bordes.
     * El texto se divide en lineas automaticamente y se ajusta
     * al ancho disponible. Las lineas vacias se rellenan con espacios.
     * 
     * @param texto Contenido a mostrar en la pantalla
     */
    public static void mostrar(String texto) {
//        limpiarConsola();
        
        //borde superior
        imprimirLinea();
        
        //dividir texto en lineas
        String[] lineas = dividirEnLineas(texto);
        
        //imprimir contenido
        for (int i = 0; i < ALTO - 2; i++) {
            System.out.print("| ");
            if (i < lineas.length) {
                System.out.print(ajustarAncho(lineas[i]));
            } else {
                System.out.print(espacios(ANCHO - 4));
            }
            System.out.println(" |");
        }
        
        //borde inferior
        imprimirLinea();
    }
    
    /**
     * Divide un texto en lineas separadas por saltos de linea (\n).
     * Cuenta manualmente los saltos de linea y crea un arreglo
     * con cada linea como elemento separado.
     * 
     * @param texto Texto a dividir en lineas
     * @return Arreglo de strings, cada uno representando una linea
     */
    private static String[] dividirEnLineas(String texto) {
        if (texto == null || texto.length() == 0) {
            return new String[0];
        }
        
        //contar saltos de linea
        int numLineas = 1;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '\n') {
                numLineas++;
            }
        }
        
        //crear arreglo
        String[] lineas = new String[numLineas];
        int indice = 0;
        String lineaActual = "";
        
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '\n') {
                lineas[indice] = lineaActual;
                indice++;
                lineaActual = "";
            } else {
                lineaActual += c;
            }
        }
        lineas[indice] = lineaActual;
        
        return lineas;
    }
    
    /**
     * Ajusta una linea al ancho disponible de la pantalla.
     * Si la linea es mas corta, se rellena con espacios.
     * Si es mas larga, se corta al ancho maximo.
     * 
     * @param linea Texto de la linea a ajustar
     * @return Linea ajustada al ancho de la pantalla
     */
    private static String ajustarAncho(String linea) {
        int anchoDisponible = ANCHO - 4;
        String resultado = "";
        
        if (linea.length() <= anchoDisponible) {
            resultado = linea;
            int espaciosFaltantes = anchoDisponible - linea.length();
            for (int i = 0; i < espaciosFaltantes; i++) {
                resultado += " ";
            }
        } else {
            //cortar linea manualmente sin substring
            for (int i = 0; i < anchoDisponible; i++) {
                resultado += linea.charAt(i);
            }
        }
        
        return resultado;
    }
    
    /**
     * Crea una cadena con una cantidad especifica de espacios.
     * Usado para rellenar lineas vacias o ajustar formato.
     * 
     * @param cantidad Numero de espacios a generar
     * @return String con la cantidad de espacios especificada
     */
    private static String espacios(int cantidad) {
        String resultado = "";
        for (int i = 0; i < cantidad; i++) {
            resultado += " ";
        }
        return resultado;
    }
    
    /**
     * Imprime una linea horizontal de borde.
     * Forma el borde superior e inferior de la pantalla
     * usando caracteres '+' en las esquinas y '-' en el medio.
     */
    private static void imprimirLinea() {
        System.out.print("+");
        for (int i = 0; i < ANCHO - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
    
    /**
     * Limpia la consola imprimiendo lineas en blanco.
     * Detecta el sistema operativo manualmente para determinar
     * el metodo apropiado, aunque actualmente usa un metodo universal.
     * Nota: Metodo actualmente comentado en mostrar().
     */
    private static void limpiarConsola() {
        //verificar sistema operativo manualmente
        String os = System.getProperty("os.name");
        boolean esWindows = false;
        
        //buscar "Windows" manualmente sin contains
        for (int i = 0; i <= os.length() - 7; i++) {
            if (os.charAt(i) == 'W' && i + 6 < os.length() &&
                os.charAt(i+1) == 'i' && os.charAt(i+2) == 'n' && 
                os.charAt(i+3) == 'd' && os.charAt(i+4) == 'o' && 
                os.charAt(i+5) == 'w' && os.charAt(i+6) == 's') {
                esWindows = true;
                break;
            }
        }
        
        //imprimir lineas en blanco para simular limpieza
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
