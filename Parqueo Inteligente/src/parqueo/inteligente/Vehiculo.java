package parqueo.inteligente;

/**
 * Representa un vehículo que puede ser estacionado en un parqueo.
 * Contiene matrícula y estado de estacionamiento(si esta estacionado o no).
 * 
 * @author euced
 */
public class Vehiculo {

    private boolean estaEstacionado;
    private String matricula;

    /**
     * Constructor de vehículo.
     * 
     * @param matricula Matrícula del vehículo.
     */
    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.estaEstacionado = false;
    }

    /** @return Matricula del vehículo */
    public String getMatricula() { return matricula; }

    /** @return true si el vehículo está estacionado, false si no */
    public boolean getEstaEstacionado() { return estaEstacionado; }

    /**
     * Cambia el estado de estacionamiento del vehículo.
     * 
     * @param estacionado true si se estaciona, false si se retira.
     */
    public void setEstaEstacionado(boolean estacionado) { this.estaEstacionado = estacionado; }
}