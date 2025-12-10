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
    private String matricula;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.estaEstacionado = false;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean getEstaEstacionado() {
        return estaEstacionado;
    }

    public void setEstaEstacionado(boolean estacionado) {
        this.estaEstacionado = estacionado;
    }


}
