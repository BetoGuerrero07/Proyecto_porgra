package parqueo.inteligente;

import interfaz.CapturadorSalida;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase main que contiene el menu de la aplicación y ejecuta el programa de
 * Valet Parking. Permite estacionar, retirar, buscar ubicacion vehiculo y
 * mostrar el parqueo (espacios libres y ocupados).
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
        CapturadorSalida capturador = new CapturadorSalida();

        capturador.agregarLinea("Bienvenido al Valet Parking");
        capturador.mostrarEnPantalla();
        capturador.limpiar();
        
        System.out.print("Ingrese numero de filas: ");
        int filas = sc.nextInt();
        System.out.print("Ingrese numero de columnas: ");
        int columnas = sc.nextInt();

        Parqueo parqueo = new Parqueo(filas, columnas);
        ArrayList<Ticket> tickets = new ArrayList<>();
        sc.nextLine(); // limpiar scanner

        int opcion;
        do {
            capturador.agregarLinea("--- MENU PARQUEO ---");
            capturador.agregarLinea("1. Estacionar vehiculo");
            capturador.agregarLinea("2. Retirar vehiculo");
            capturador.agregarLinea("3. Buscar vehiculo");
            capturador.agregarLinea("4. Mostrar parqueo graficado");
            capturador.agregarLinea("5. Salir");
            capturador.mostrarEnPantalla();
            capturador.limpiar();
            System.out.print("Elija opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese matricula del vehiculo: ");
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

                    String validacion = parqueo.ValidarPosicion(fila, col);
                    if (validacion.length() > 0) {
                        capturador.agregar(validacion);
                        capturador.mostrarEnPantalla();
                        capturador.limpiar();
                    } else {
                        Ticket ticket = parqueo.Aparcar(veh, fila, col, hora, min);
                        if (ticket != null) {
                            tickets.add(ticket);
                            capturador.agregarLinea("Vehiculo estacionado correctamente.");
                            capturador.agregarLinea("Ticket generado # " + ticket.getIdTicket());
                            capturador.agregarLinea("Ubicacion: fila " + (fila) + ", columna " + (col));
                            capturador.mostrarEnPantalla();
                            capturador.limpiar();
                        }
                    }
                    break;

                case 2:
                    char seleccion;
                    do {
                        capturador.agregarLinea("Como desea retirar vehiculo?");
                        capturador.agregarLinea("1: matricula");
                        capturador.agregarLinea("2: fila y columna");
                        capturador.mostrarEnPantalla();
                        capturador.limpiar();
                        System.out.print("Seleccione: ");
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
                                capturador.agregarLinea("Seleccione una opcion valida");
                                capturador.mostrarEnPantalla();
                                capturador.limpiar();
                        }

                        if (ticketRetiro != null) {
                            // Validar hora de salida
                            do {
                                System.out.print("Hora de salida (0-23): ");
                                hs = sc.nextInt();
                                System.out.print("Minuto de salida (0-59): ");
                                ms = sc.nextInt();

                                if (!UtilidadesParqueo.horaValida(hs, ms) || !ticketRetiro.horaSalidaValida(hs, ms)) {
                                    capturador.agregarLinea("Hora de salida invalida. Debe ser posterior a la entrada.");
                                    capturador.mostrarEnPantalla();
                                    capturador.limpiar();
                                } else {
                                    break;
                                }
                            } while (true);
                            sc.nextLine(); // limpiar buffer

                            // Retirar vehículo y mostrar costo
                            String mensajeRetiro = parqueo.RetirarConMensaje(ticketRetiro, hs, ms);
                            capturador.agregar(mensajeRetiro);

                            int tiempo = ticketRetiro.calcularTiempoEstacionado();
                            int costo = UtilidadesParqueo.calcularCosto(tiempo);
                            capturador.agregarLinea("Costo a pagar: L." + costo);
                            capturador.mostrarEnPantalla();
                            capturador.limpiar();

                        } else {
                            capturador.agregarLinea("Vehiculo no encontrado o ya retirado.");
                            capturador.mostrarEnPantalla();
                            capturador.limpiar();
                        }

                    } while (seleccion != '1' && seleccion != '2');
                    break;

                case 3:
                    System.out.print("Ingrese matricula del vehiculo a buscar: ");
                    String matriculaBuscar = sc.nextLine();
                    boolean encontrado = false;
                    for (Ticket t : tickets) {
                        if (t.getVehiculo().getMatricula().equalsIgnoreCase(matriculaBuscar) && t.getVehiculo().getEstaEstacionado()) {
                            capturador.agregarLinea("Vehiculo encontrado en fila " + t.getFila() + ", columna " + t.getColumna());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        capturador.agregarLinea("Vehiculo no encontrado o ya retirado.");
                    }
                    capturador.mostrarEnPantalla();
                    capturador.limpiar();
                    break;

                case 4:
                    String grafico = parqueo.ObtenerParqueoGrafico();
                    capturador.agregar(grafico);
                    capturador.mostrarEnPantalla();
                    capturador.limpiar();
                    break;

                case 5:
                    capturador.agregarLinea("Saliendo...");
                    capturador.mostrarEnPantalla();
                    capturador.limpiar();
                    break;

                default:
                    capturador.agregarLinea("Opcion invalida.");
                    capturador.mostrarEnPantalla();
                    capturador.limpiar();
            }

        } while (opcion != 5);
    }
}
