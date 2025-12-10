/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueo.inteligente;

/**
 *
 * @author euced
 */
public class Parqueo {

    private boolean[][] mapa;

    /**
     * Constructor de clase Parqueo Genera automaticamente un mapa tipo booleano
     * vacio
     *
     * @param filas
     * @param columnas
     */
    public Parqueo(int filas, int columnas) {
        mapa = new boolean[filas][columnas];

    }

    /**
     * metodo que revisa si el parque esta lleno
     *
     * @param mapa
     * @return
     */
    public boolean ParqueoLleno() {
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                if (area == false) {
                    return false;
                }
            }
        }
        return true;
    }

    //recibe atributo privado "mapa"
    public void ImprimirParqueo() {
        for (boolean[] fila : mapa) {

            // Línea superior de cada casilla
            for (boolean area : fila) {
                System.out.printf("%2c%2c%2c ", '-', '-', '-');
            }
            System.out.println();

            // Línea del medio
            for (boolean area : fila) {
                if (area) {
                    System.out.printf("%2c%2c%2c ", '|', '#', '|');
                } else {
                    System.out.printf("%2c%2c%2c ", '|', ' ', '|');
                }
            }
            System.out.println();

            // Línea inferior
            for (boolean area : fila) {
                System.out.printf("%2c%2c%2c ", '-', '-', '-');
            }
            System.out.println();   // terminar la fila completa
            System.out.println();   // espacio entre filas
        }
    }

    public void Retirar(Ticket ticket, int horaSalida, int minutoSalida) {
        int fila = ticket.getFila();
        int columna = ticket.getColumna();

        if (mapa[fila][columna]) {
            System.out.println("Error: el espacio no esta disponible");
        }
        
        mapa[fila][columna] = false;
        ticket.marcarSalida(horaSalida, minutoSalida);
        System.out.println("Tiempo estacionado: " + ticket.calcularTiempoEstacionado() + " minutos");
    }

    public int EspaciosDisponibles() {
        int contadorEspacios = 0;
        for (boolean[] filas : mapa) {
            for (boolean area : filas) {
                if (!area) {
                    contadorEspacios += 1;
                }
            }
        }
        return contadorEspacios;

    }

    public Ticket Aparcar(Vehiculo vehiculo, int fila, int columna, int hora, int minuto) {
        if (fila < 0 || fila >= mapa.length || columna < 0 || columna >= mapa[0].length) {
            System.out.println("Posición inválida");
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
