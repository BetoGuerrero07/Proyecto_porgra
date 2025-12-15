package parqueo.inteligente;

/**
 * Representa un parqueo con espacios disponibles para vehiculos. Cada espacio
 * se representa como un valor booleano: true ocupado, false libre.
 *
 * @author euced
 */
public class Parqueo {

    private boolean[][] mapa;

    /**
     * Constructor de Parqueo.
     *
     * @param filas Cantidad de filas del parqueo.
     * @param columnas Cantidad de columnas del parqueo.
     */
    public Parqueo(int filas, int columnas) {
        mapa = new boolean[filas][columnas];
    }

    /**
     * Revisa si el parqueo esta completamente lleno.
     *
     * @return true si todos los espacios estan ocupados, false si hay al menos
     * uno libre.
     */
    public boolean ParqueoLleno() {
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                if (!area) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Imprime el parqueo graficado en consola. '#' indica espacio ocupado, ' '
     * espacio libre.
     */
    public void ImprimirParqueo() {
        System.out.println("\n#: Espacio ocupado por vehiculo\n");

        for (boolean[] fila : mapa) {
            // Línea superior de cada casilla
            for (boolean area : fila) {
                System.out.print("--- ");
            }
            System.out.println();

            // Línea del medio
            for (boolean area : fila) {
                char simbolo;
                if (area) {
                    simbolo = '#';
                } else {
                    simbolo = ' ';
                }
                System.out.printf("|%c| ", simbolo);
            }
            System.out.println();

            // Línea inferior
            for (boolean area : fila) {
                System.out.print("--- ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Libera un espacio de parqueo y marca la salida del vehiculo.
     *
     * @param ticket Ticket del vehiculo a retirar.
     * @param horaSalida Hora de salida (0-23).
     * @param minutoSalida Minuto de salida (0-59).
     */
    public void Retirar(Ticket ticket, int horaSalida, int minutoSalida) {
        int fila = ticket.getFila();
        int columna = ticket.getColumna();

        if (!mapa[fila][columna]) {
            System.out.println("Error: el espacio no esta disponible");
            return;
        }

        mapa[fila][columna] = false;
        ticket.marcarSalida(horaSalida, minutoSalida);
        System.out.println("Tiempo estacionado: " + ticket.calcularTiempoEstacionado() + " minutos");
    }

    /**
     * Libera un espacio de parqueo indicando fila y columna directamente.
     *
     * @param fila Fila del espacio.
     * @param columna Columna del espacio.
     * @param horaSalida Hora de salida (0-23).
     * @param minutoSalida Minuto de salida (0-59).
     */
    public void Retirar(int fila, int columna, int horaSalida, int minutoSalida) {

        // Validar posicion
        if (fila < 0 || fila >= mapa.length || columna < 0 || columna >= mapa[0].length) {
            System.out.println("Posicion invalida");
            return;
        }

        // Validar que el espacio este ocupado
        if (!mapa[fila][columna]) {
            System.out.println("El espacio ya esta libre");
            return;
        }

        mapa[fila][columna] = false;
        System.out.println("Vehiculo retirado del espacio [" + fila + "][" + columna + "]");
        System.out.println("Hora de salida registrada: " + horaSalida + ":" + minutoSalida);
    }

    /**
     * Devuelve el numero de espacios libres en el parqueo.
     *
     * @return Numero de espacios libres.
     */
    public int EspaciosDisponibles() {
        int contador = 0;
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                if (!area) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * Estaciona un vehiculo en una posicion específica.
     *
     * @param vehiculo Vehículo a estacionar.
     * @param fila Fila donde se estacionará.
     * @param columna Columna donde se estacionará.
     * @param hora Hora de entrada.
     * @param minuto Minuto de entrada.
     * @return Ticket generado si se estaciona correctamente, null si hay error.
     */
    public Ticket Aparcar(Vehiculo vehiculo, int fila, int columna, int hora, int minuto) {
        if (fila < 0 || fila >= mapa.length || columna < 0 || columna >= mapa[0].length) {
            System.out.println("Posicion invalida");
            return null;
        }
        if (mapa[fila][columna]) {
            System.out.println("Espacio ocupado, elija otro");
            return null;
        }

        mapa[fila][columna] = true;
        vehiculo.setEstaEstacionado(true);
        return new Ticket(fila, columna, hora, minuto, vehiculo);
    }
}
