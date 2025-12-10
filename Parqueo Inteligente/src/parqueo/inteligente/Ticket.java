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

    private int fila;
    private int columna;
    private int horaEntrada;
    private int minutoEntrada;
    private Vehiculo vehiculo;

    public Ticket(int fila, int columna, int horaEntrada, int minutoEntrada, Vehiculo vehiculo) {
        this.fila = fila;
        this.columna = columna;
        this.horaEntrada = horaEntrada;
        this.minutoEntrada = minutoEntrada;
        this.vehiculo = vehiculo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public int getMinutoEntrada() {
        return minutoEntrada;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    public int calcularTiempoEstacionado(int horaSalida, int minutoSalida) {
        return (horaSalida * 60 + minutoSalida) - (horaEntrada * 60 + minutoEntrada);
    }

}
