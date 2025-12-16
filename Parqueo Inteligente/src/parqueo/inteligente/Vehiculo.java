package parqueo.inteligente;

/**
 * Representa un vehiculo que puede ser estacionado en un parqueo.
 * Contiene matricula y estado de estacionamiento(si esta estacionado o no).
 * 
 * @author David Euceda
 */
public class Vehiculo {

    private boolean estaEstacionado;
    private String matricula;

    /**
     * Constructor de vehículo.
     * 
     * @param matricula Matricula del vehiculo.
     */
    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.estaEstacionado = false;
    }

    /** @return Matricula del vehiculo */
    public String getMatricula() { return matricula; }

    /** @return true si el vehiculo esta estacionado, false si no */
    public boolean getEstaEstacionado() { return estaEstacionado; }

    /**
     * Cambia el estado de estacionamiento del vehiculo.
     * 
     * @param estacionado true si se estaciona, false si se retira.
     */
    public void setEstaEstacionado(boolean estacionado) { this.estaEstacionado = estacionado; }
}