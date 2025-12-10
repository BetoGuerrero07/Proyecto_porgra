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
    public boolean ParqueoLleno(boolean[][] mapa) {
        for (boolean[] fila : mapa) {
            for (boolean area : fila) {
                if (area == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public void ImprimirParqueo(boolean[][] mapa) {
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
    
    public void Aparcar(Vehiculo vehiculo){
        int[] valorTicket = vehiculo.getNumTicket();
        vehiculo.setEstaEstacionado(true);
        
        mapa[valorTicket[0]][valorTicket[1]]= true;
        
        
    }
    
    public void Retirar(int fila, int columna){
        if (mapa[fila][columna]) {
            System.out.println("espacio no esta disponible");
        }else{
            mapa[fila][columna]= false;
        }
        
    }
    
    public int EspaciosDisponibles(){
        int contadorEspacios = 0;
        for (boolean[] filas: mapa) {
            for (boolean area:filas) {
                if (!area) {
                    contadorEspacios+= 1;
                }
            }
        }
        return contadorEspacios;
        
    }
    
    
    

}
