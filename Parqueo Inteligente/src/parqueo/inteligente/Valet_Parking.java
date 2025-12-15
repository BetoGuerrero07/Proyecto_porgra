package parqueo.inteligente;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase main que contiene el menu de la aplicación y ejecuta el programa de
 * Valet Parking. Permite estacionar, retirar, buscar ubicacion vehiculo y mostrar
 * el parqueo (espacios libres y ocupados).
 *
 * Autor: David Euceda, Alberto Guerrero, Jeremyah Mercado
 */
public class Valet_Parking {

    /**
     * Metodo principal que ejecuta la aplicacion.
     *
     * @param args Argumentos del main (no usados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("Bienvenido al Valet Parking");
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
                    //se reciben los datos del vehiculo
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
                        System.out.println("Ubicacion: fila " + (fila ) + ", columna " + (col));
                    }
                    break;

                case 2:
                    char seleccion;
                    do {
                        System.out.println("Como desea retirar vehiculo? (1: matricula, 2: fila y columna)");
                        seleccion = sc.nextLine().charAt(0);

                        Ticket ticketRetiro = null;
                        int hs, ms;

                        switch (seleccion) {
                            case '1': // por matrícula
                                System.out.print("Ingrese matricula del vehiculo: ");
                                String matricula = sc.nextLine();
                                for (Ticket t : tickets) {
                                    if (t.getVehiculo().getMatricula().equalsIgnoreCase(matricula)
                                            && t.getVehiculo().getEstaEstacionado()) {
                                        ticketRetiro = t;
                                        break;
                                    }
                                }
                                break;

                            case '2': // por fila y columna
                                System.out.print("Ingrese fila: ");
                                int fila2 = sc.nextInt();
                                System.out.print("Ingrese columna: ");
                                int col2 = sc.nextInt();
                                for (Ticket t : tickets) {
                                    if (t.getFila() == fila2 && t.getColumna() == col2
                                            && t.getVehiculo().getEstaEstacionado()) {
                                        ticketRetiro = t;
                                        break;
                                    }
                                }
                                sc.nextLine(); // limpiar buffer
                                break;

                            default:
                                System.out.println("Seleccione una opción válida");
                        }

                        if (ticketRetiro != null) {
                            // Validar hora de salida
                            do {
                                System.out.print("Hora de salida (0-23): ");
                                hs = sc.nextInt();
                                System.out.print("Minuto de salida (0-59): ");
                                ms = sc.nextInt();

                                if (!ticketRetiro.horaSalidaValida(hs, ms)) {
                                    System.out.println("Hora de salida inválida. Debe ser posterior a la entrada.");
                                } else {
                                    break;
                                }
                            } while (true);
                            sc.nextLine(); // limpiar buffer

                            // Retirar vehículo y mostrar costo
                            parqueo.Retirar(ticketRetiro, hs, ms);
                            int costo = ticketRetiro.calcularCosto();
                            System.out.println("Costo a pagar: L." + costo);

                        } else {
                            System.out.println("Vehiculo no encontrado o ya retirado.");
                        }

                    } while (seleccion != '1' && seleccion != '2');
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
