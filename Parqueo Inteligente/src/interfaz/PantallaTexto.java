package interfaz;

/**
 * Clase utilitaria para desplegar texto en una pantalla con bordes.
 * Crea una interfaz visual de texto con ancho fijo y alto dinamico
 * que se ajusta automaticamente segun el contenido.
 *
 * Uso: PantallaTexto.mostrar(texto);
 * 
 * Caracteristicas:
 * - Ancho fijo de 50 caracteres
 * - Alto dinamico (se ajusta al contenido + margen)
 * - Ajuste automatico para graficos grandes
 * @author Jeremiyah Mercado
 */
public class PantallaTexto {
    
    /**
     * Ancho total de la pantalla en caracteres (incluye los bordes)
     */
    private static final int ANCHO = 50;
    
    /**
     * Margen minimo de lineas vacias arriba y abajo del contenido.
     * AJUSTAR ESTE VALOR para cambiar el espaciado vertical:
     * - 0 = sin margen (contenido pegado al borde)
     * - 1 = margen minimo (recomendado)
     * - 2 = margen normal
     * - 3+ = margen amplio
     */
    private static final int MARGEN_ALTO = 1;
    
    /**
     * Muestra texto en una pantalla con formato y bordes.
     * El texto se divide en lineas automaticamente y se ajusta
     * al ancho disponible. El alto se calcula dinamicamente
     * segun la cantidad de lineas del contenido.
     * 
     * @param texto Contenido a mostrar en la pantalla
     */
    public static void mostrar(String texto) {
        limpiarConsola();
        //dividir texto en lineas
        String[] lineas = dividirEnLineas(texto);
        
        //calcular alto necesario (lineas + margen superior e inferior)
        int altoTotal = lineas.length + (MARGEN_ALTO * 2);
        
        //borde superior
        imprimirLinea();
        
        //margen superior
        for (int i = 0; i < MARGEN_ALTO; i++) {
            System.out.print("|");
            System.out.print(espacios(ANCHO - 2));
            System.out.println("|");
        }
        
        //imprimir contenido
        for (int i = 0; i < lineas.length; i++) {
            System.out.print("|" + " ");
            System.out.print(ajustarAncho(lineas[i]));
            System.out.println("|");
        }
        
        //margen inferior
        for (int i = 0; i < MARGEN_ALTO; i++) {
            System.out.print("|");
            System.out.print(espacios(ANCHO - 2));
            System.out.println("|");
        }
        
        //borde inferior
        imprimirLinea();
    }
    
    /**
     * Divide un texto en lineas separadas por saltos de linea (\n).
     * Implementa word wrap automatico: si una linea es muy larga,
     * se divide en multiples lineas que caben en el ancho disponible.
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
        
        int anchoMax = ANCHO - 3; //ancho disponible por linea
        
        //primer paso: dividir por saltos de linea
        int numLineas = 1;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '\n') {
                numLineas++;
            }
        }
        
        String[] lineasTemp = new String[numLineas];
        int indice = 0;
        String lineaActual = "";
        
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '\n') {
                lineasTemp[indice] = lineaActual;
                indice++;
                lineaActual = "";
            } else {
                lineaActual += c;
            }
        }
        lineasTemp[indice] = lineaActual;
        
        //segundo paso: aplicar word wrap a lineas largas
        int totalLineasFinal = 0;
        for (int i = 0; i < lineasTemp.length; i++) {
            if (lineasTemp[i].length() > anchoMax) {
                //calcular cuantas lineas se necesitan
                int lineasNecesarias = (lineasTemp[i].length() + anchoMax - 1) / anchoMax;
                totalLineasFinal += lineasNecesarias;
            } else {
                totalLineasFinal++;
            }
        }
        
        //crear arreglo final con word wrap
        String[] lineasFinal = new String[totalLineasFinal];
        int indiceFinal = 0;
        
        for (int i = 0; i < lineasTemp.length; i++) {
            String linea = lineasTemp[i];
            
            if (linea.length() <= anchoMax) {
                lineasFinal[indiceFinal] = linea;
                indiceFinal++;
            } else {
                //dividir linea larga
                int inicio = 0;
                while (inicio < linea.length()) {
                    int fin = inicio + anchoMax;
                    if (fin > linea.length()) {
                        fin = linea.length();
                    }
                    
                    String segmento = "";
                    for (int j = inicio; j < fin; j++) {
                        segmento += linea.charAt(j);
                    }
                    
                    lineasFinal[indiceFinal] = segmento;
                    indiceFinal++;
                    inicio = fin;
                }
            }
        }
        
        return lineasFinal;
    }
    
    /**
     * Ajusta una linea al ancho disponible de la pantalla.
     * Si la linea es mas corta, se rellena con espacios.
     * Si es mas larga, NO se corta, sino que continua en la siguiente linea.
     * MARGEN HORIZONTAL: 1 espacio entre el borde y el contenido.
     * Para cambiar el margen horizontal, modifica los valores en mostrar():
     * - Cambiar ANCHO - 2 por ANCHO - 4 para margen de 2 espacios
     * - Cambiar "|" + " " por "|" + "  " para margen de 2 espacios
     * 
     * @param linea Texto de la linea a ajustar
     * @return Linea ajustada al ancho de la pantalla (rellena con espacios)
     */
    private static String ajustarAncho(String linea) {
        int anchoDisponible = ANCHO - 3; // -2 bordes, -1 espacio margen
        String resultado = "";
        
        if (linea.length() <= anchoDisponible) {
            resultado = linea;
            int espaciosFaltantes = anchoDisponible - linea.length();
            for (int i = 0; i < espaciosFaltantes; i++) {
                resultado += " ";
            }
        } else {
            //tomar solo lo que cabe en esta linea
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
     */
    private static void limpiarConsola() {
        //imprimir lineas en blanco para simular limpieza
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}