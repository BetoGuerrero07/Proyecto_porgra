/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parqueo.inteligente;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author euced
 */
public class ParqueoInteligente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("Bienvenido al Parqueo Inteligente");
        System.out.print("Ingrese número de filas: ");
        int filas = sc.nextInt();
        System.out.print("Ingrese número de columnas: ");
        int columnas = sc.nextInt();

        Parqueo parqueo = new Parqueo(filas, columnas);
        ArrayList<Ticket> tickets = new ArrayList<>();
        sc.nextLine(); // limpiar buffer

        int opcion;
        do {
            System.out.println("\n--- MENÚ PARQUEO ---");
            System.out.println("1. Estacionar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Buscar vehículo");
            System.out.println("4. Mostrar parqueo graficado");
            System.out.println("5. Generar ticket aleatorio");
            System.out.println("6. Salir");
            System.out.print("Elija opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1:
                    System.out.print("Ingrese matrícula del vehículo: ");
                    String mat = sc.nextLine();
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
                        System.out.println("Vehículo estacionado correctamente en fila " + fila + ", columna " + col);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese matrícula del vehículo a retirar: ");
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
                        System.out.println("Vehículo no encontrado o ya retirado.");
                    }
                    sc.nextLine(); // limpiar buffer
                    break;

                case 3:
                    System.out.print("Ingrese matrícula del vehículo a buscar: ");
                    String matriculaBuscar = sc.nextLine();
                    boolean encontrado = false;
                    for (Ticket t : tickets) {
                        if (t.getVehiculo().getMatricula().equalsIgnoreCase(matriculaBuscar) && t.getVehiculo().getEstaEstacionado()) {
                            System.out.println("Vehículo encontrado en fila " + t.getFila() + ", columna " + t.getColumna());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Vehículo no encontrado o ya retirado.");
                    }
                    break;

                case 4:
                    parqueo.ImprimirParqueo();
                    break;

                case 5:
                    int randomFila = rnd.nextInt(filas);
                    int randomCol = rnd.nextInt(columnas);
                    System.out.println("Ticket aleatorio generado en fila " + randomFila + ", columna " + randomCol);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while(opcion != 6);

        sc.close();
    }
}