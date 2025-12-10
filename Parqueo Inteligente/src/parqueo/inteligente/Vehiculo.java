/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueo.inteligente;

/**
 *
 * @author euced
 */
public class Vehiculo {
    private boolean estaEstacionado;
    private int tiempoEstacionado;
    private String matricula;
    private int[] numTicket;

    public Vehiculo(int tiempoEstacionado, String matricula, Ticket ticket) {
        estaEstacionado = false;
        this.tiempoEstacionado = tiempoEstacionado;
        this.matricula = matricula;
        
        numTicket = new int[2];
        numTicket[0]= ticket.getFila();
        numTicket[1]= ticket.getColumna();
        
        
        
    }

    public boolean isEstaEstacionado() {
        return estaEstacionado;
    }

    public void setEstaEstacionado(boolean estaEstacionado) {
        this.estaEstacionado = estaEstacionado;
    }

    public int getTiempoEstacionado() {
        return tiempoEstacionado;
    }

    public void setTiempoEstacionado(int tiempoEstacionado) {
        this.tiempoEstacionado = tiempoEstacionado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int[] getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(int[] numTicket) {
        this.numTicket = numTicket;
    }
    
    
    
    
}
