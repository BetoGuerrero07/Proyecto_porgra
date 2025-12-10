/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueo.inteligente;

import java.util.Random;

/**
 *
 * @author euced
 */
public class Ticket {
    private Random random = new Random();
    private int fila;
    private int columna;

    public Ticket(int ultimaFila, int ultimaColumna ) {
        fila = random.nextInt(0, ultimaFila);
        columna = random.nextInt(0, ultimaFila);
    
        
        
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    
}
