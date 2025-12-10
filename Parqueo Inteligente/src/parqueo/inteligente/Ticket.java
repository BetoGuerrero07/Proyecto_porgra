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
    private int horaSalida;
    private int minutoSalida;
    private Vehiculo vehiculo;

    public Ticket(int fila, int columna, int horaEntrada, int minutoEntrada, Vehiculo vehiculo) {
        this.fila = fila;
        this.columna = columna;
        this.horaEntrada = horaEntrada;
        this.minutoEntrada = minutoEntrada;
        this.vehiculo = vehiculo;
        
        this.horaSalida = -1;
        this.minutoSalida = -1;
    }

    public void marcarSalida(int hora, int minuto) {
        this.horaSalida = hora;
        this.minutoSalida = minuto;
        vehiculo.setEstaEstacionado(false);
    }
    // Calcular minutos total estacionado
    public int calcularTiempoEstacionado() {
        if (horaSalida == -1 || minutoSalida == -1) {
            return -1; // Aún no salió
        }
        return (horaSalida * 60 + minutoSalida) - (horaEntrada * 60 + minutoEntrada);
    }
    // Getters
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

    

}
