/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueo.inteligente;

/**
 * Clase de utilidades para el parqueo con metodos estáticos.
 * Contiene funciones de validacion y calculo de costos.
 * 
 * @author David Euceda
 */
public class UtilidadesParqueo {
    /**
     * Valida que la hora y minuto estén dentro del rango 0-23 y 0-59
     * 
     * @param hora   hora a validar
     * @param minuto minuto a validar
     * @return true si es valido, false si no
     */
    public static boolean horaValida(int hora, int minuto) {
        if (hora < 0 || hora > 23)
            return false;
        if (minuto < 0 || minuto > 59)
            return false;
        return true;
    }

    /**
     * Calcula el costo del estacionamiento segun el tiempo en minutos
     * 
     * @param minutos tiempo estacionado
     * @return costo en lempiras
     */
    public static int calcularCosto(int minutos) {
        if (minutos <= 0)
            return 0;
        if (minutos <= 180)
            return 10;
        else if (minutos <= 240)
            return 20;
        else if (minutos <= 300)
            return 30;
        else if (minutos <= 360)
            return 40;
        else
            return 100;
    }
}
