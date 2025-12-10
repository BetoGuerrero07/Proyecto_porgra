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
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                area = false;
            }
        }

    }

    /**
     * metodo que revisa si el parque esta lleno
     *
     * @param mapa
     * @return
     */
    public static boolean parqueoLleno(boolean[][] mapa) {
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                if (area == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void imprimirParqueo(boolean[][] mapa) {
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                if (area) {
                    System.out.printf("%2c%2c%2c%n",'-','-','-');
                    System.out.printf("%2c%2c%2c%n",'|','#','|');
                    System.out.printf("%2c%2c%2c%n",'-','-','-');
                    
                }else{
                    System.out.printf("%2c%2c%2c%n",'-','-','-');
                    System.out.printf("%2c%2c%2c%n",'|',' ','|');
                    System.out.printf("%2c%2c%2c%n",'-','-','-');
                }
                
            }
            System.out.println();
        }
    }
    
    

}
