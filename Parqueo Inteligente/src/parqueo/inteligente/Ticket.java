package parqueo.inteligente;

import java.util.Random;

/**
 * Representa un ticket generado al estacionar un vehículo. Contiene información
 * de ubicacion (fila y columna), hora de entrada y salida, y el vehículo.
 * Genera un ID aleatorio único.
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
    private int idTicket;

    /**
     * Constructor del ticket.
     *
     * @param fila          Fila donde se estaciona el vehiculo.
     * @param columna       Columna donde se estaciona el vehiculo.
     * @param horaEntrada   Hora de entrada del vehiculo.
     * @param minutoEntrada Minuto de entrada del vehiculo.
     * @param vehiculo      Vehiculo asociado al ticket.
     */
    public Ticket(int fila, int columna, int horaEntrada, int minutoEntrada, Vehiculo vehiculo) {
        Random rnd = new Random();
        this.fila = fila;
        this.columna = columna;
        this.horaEntrada = horaEntrada;
        this.minutoEntrada = minutoEntrada;
        this.vehiculo = vehiculo;
        this.horaSalida = -1;
        this.minutoSalida = -1;
        this.idTicket = rnd.nextInt(100000);
    }

    /**
     * Marca la salida del vehículo.
     *
     * @param hora   Hora de salida.
     * @param minuto Minuto de salida.
     */
    public void marcarSalida(int hora, int minuto) {
        this.horaSalida = hora;
        this.minutoSalida = minuto;
        vehiculo.setEstaEstacionado(false);
    }

    /**
     * Calcula el tiempo total estacionado en minutos.
     *
     * @return Tiempo estacionado en minutos, -1 si no ha salido.
     */
    public int calcularTiempoEstacionado() {
        if (horaSalida == -1 || minutoSalida == -1) {
            return -1;
        }
        return (horaSalida * 60 + minutoSalida) - (horaEntrada * 60 + minutoEntrada);

    }

    /**
     * Valida si la hora validada esta dentro del rango de horas y si esta ocurre
     * despues de la hora inicial
     * 
     * @param hora
     * @param minuto
     * @return
     */
    public boolean horaSalidaValida(int hora, int minuto) {

        if (hora < 0 || hora > 23) {
            return false;
        }

        if (minuto < 0 || minuto > 59) {
            return false;
        }

        int entradaTotal = horaEntrada * 60 + minutoEntrada;
        int salidaTotal = hora * 60 + minuto;

        if (salidaTotal <= entradaTotal) {
            return false;
        }

        return true;
    }

    /**
     * @return Fila donde esta estacionado el vehiculo
     */
    public int getFila() {
        return fila;
    }

    /**
     * @return Columna donde esta estacionado el vehiculo
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @return Hora de entrada
     */
    public int getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @return Minuto de entrada
     */
    public int getMinutoEntrada() {
        return minutoEntrada;
    }

    /**
     * @return Vehiculo enlazado al ticket
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @return ID del ticket
     */
    public int getIdTicket() {
        return idTicket;
    }
}
