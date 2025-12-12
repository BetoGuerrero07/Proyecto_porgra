package parqueo.inteligente;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase main que contiene el menu de la aplicación
 * y ejecuta el programa de Parqueo Inteligente.
 * Permite estacionar, retirar, buscar vehiculos y mostrar el parqueo (espacios libres y ocupados).
 * 
 * Autor: euced
 */
public class MenuPrincipal {

    /**
     * Metodo principal que ejecuta la aplicacion.
     * 
     * @param args Argumentos del main (no usados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("Bienvenido al Parqueo Inteligente");
        System.out.print("Ingrese numero de filas: ");
        int filas = sc.nextInt();
        System.out.print("Ingrese numero de columnas: ");
        int columnas = sc.nextInt();

        Parqueo parqueo = new Parqueo(filas, columnas);
        ArrayList<Ticket> tickets = new ArrayList<>();
        sc.nextLine(); // limpiar scanner

        int opcion;
        do {
            System.out.println("\n--- MENU PARQUEO ---");
            System.out.println("1. Estacionar vehiculo");
            System.out.println("2. Retirar vehiculo");
            System.out.println("3. Buscar vehiculo");
            System.out.println("4. Mostrar parqueo graficado");
            System.out.println("5. Salir");
            System.out.print("Elija opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese matricula del vehiculo: ");
                    String mat = sc.nextLine();
                    //
                    Vehiculo veh = new Vehiculo(mat);

                    System.out.print("Ingrese fila para estacionar: ");
                    int fila = sc.nextInt();
                    System.out.print("Ingrese columna para estacionar: ");
                    int col = sc.nextInt();
                    System.out.print("Hora de entrada (0-23): ");
                    int hora = sc.nextInt();
                    System.out.print("Minuto de entrada (0-59): ");
                    int min = sc.nextInt();

                    Ticket ticket = parqueo.Aparcar(veh, fila, col, hora, min);

                    if (ticket != null) {
                        tickets.add(ticket);
                        System.out.println("Vehiculo estacionado correctamente.");
                        System.out.println("Ticket generado # " + ticket.getIdTicket());
                        System.out.println("Ubicacion: fila " + fila + ", columna " + col);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese matricula del vehiculo a retirar: ");
                    String matriculaRetiro = sc.nextLine();
                    Ticket ticketRetiro = null;
                    for (Ticket t : tickets) {
                        if (t.getVehiculo().getMatricula().equalsIgnoreCase(matriculaRetiro) && t.getVehiculo().getEstaEstacionado()) {
                            ticketRetiro = t;
                            break;
                        }
                    }
                    if (ticketRetiro != null) {
                        System.out.print("Hora de salida: ");
                        int hs = sc.nextInt();
                        System.out.print("Minuto de salida: ");
                        int ms = sc.nextInt();
                        parqueo.Retirar(ticketRetiro, hs, ms);
                    } else {
                        System.out.println("Vehiculo no encontrado o ya retirado.");
                    }
                    sc.nextLine(); // limpiar scanner
                    break;

                case 3:
                    System.out.print("Ingrese matricula del vehiculo a buscar: ");
                    String matriculaBuscar = sc.nextLine();
                    boolean encontrado = false;
                    for (Ticket t : tickets) {
                        if (t.getVehiculo().getMatricula().equalsIgnoreCase(matriculaBuscar) && t.getVehiculo().getEstaEstacionado()) {
                            System.out.println("Vehiculo encontrado en fila " + t.getFila() + ", columna " + t.getColumna());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Vehiculo no encontrado o ya retirado.");
                    }
                    break;

                case 4:
                    parqueo.ImprimirParqueo();
                    break;
                    
                    
                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 5);
    }
}