/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mx.itson.terminal;

import java.util.Scanner;

/**
 * Programa que simula un sistema de gestión de boletos para una terminal de autobuses.
 * Incluye funcionalidades para vender boletos, mover a la siguiente terminal
 * y generar un reporte final con los datos de los pasajeros.
 * 
 * @author Thinkpad
 */
public class TerminalDeAutobuses {

    // Array para representar los 20 asientos del autobús (true = ocupado, false = libre)
    static boolean[] asientos = new boolean[20];
    
    // Variable que almacena la suma total de las ventas de boletos
    static double gananciaTotal = 0;
    
    // Arrays para almacenar la información de los pasajeros
    static String[] nombres = new String[20]; // Nombres de los pasajeros
    static String[] destinos = new String[20]; // Destinos de los pasajeros
    static String[] origenes = new String[20]; // Orígenes de los pasajeros
    static double[] precios = new double[20]; // Precios de los boletos
    static int[] asientosOcupados = new int[20]; // Números de los asientos ocupados

    /**
     * Método principal del programa. Controla el flujo de ejecución del sistema
     * mediante un menú interactivo.
     *
     * @param args Argumentos de la línea de comandos (no se usan en este programa).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        // Inicializar todos los asientos como libres (false)
        for (int i = 0; i < 20; i++) {
            asientos[i] = false;
        }

        // Ciclo para mostrar el menú principal hasta que el usuario decida salir
        while (opcion != 4) {
            System.out.println("\nMenú:");
            System.out.println("1. Vender boleto");
            System.out.println("2. Mover a siguiente terminal");
            System.out.println("3. Mostrar reporte final");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();

            // Opciones del menú
            if (opcion == 1) {
                venderBoleto(sc); // Llama al método para vender un boleto
            } else if (opcion == 2) {
                moverTerminal(); // Llama al método para mover a la siguiente terminal
            } else if (opcion == 3) {
                mostrarReporte(); // Llama al método para mostrar el reporte final
            } else if (opcion != 4) {
                System.out.println("Opción no válida."); // Manejo de opciones inválidas
            }
        }
    }

    /**
     * Método para vender un boleto. Solicita datos al usuario y asigna el asiento seleccionado.
     *
     * @param sc Objeto Scanner para la entrada de datos del usuario.
     */
    public static void venderBoleto(Scanner sc) {
        System.out.println("Asientos disponibles:");
        // Mostrar el estado de los asientos (ocupado o libre)
        for (int i = 0; i < 20; i++) {
            if (asientos[i]) {
                System.out.print("[X] "); // Asiento ocupado
            } else {
                System.out.print("[" + (i + 1) + "] "); // Asiento libre
            }
            if ((i + 1) % 2 == 0) {
                System.out.println(); // Salto de línea cada 2 asientos
            }
        }

        // Solicitar al usuario que seleccione un asiento
        System.out.print("Selecciona el número de asiento (1-20): ");
        int asiento = sc.nextInt() - 1;

        // Verificar si el asiento está ocupado
        if (asientos[asiento]) {
            System.out.println("El asiento está ocupado.");
        } else {
            sc.nextLine(); // Limpiar el buffer del Scanner
            // Solicitar información del pasajero
            System.out.print("Introduce el nombre del pasajero: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce el origen del pasajero: ");
            String origen = sc.nextLine();
            System.out.print("Introduce el destino del pasajero: ");
            String destino = sc.nextLine();
            System.out.print("Introduce el precio del boleto: ");
            double precio = sc.nextDouble();

            // Marcar el asiento como ocupado y guardar los datos del pasajero
            asientos[asiento] = true;
            gananciaTotal += precio; // Sumar el precio del boleto a la ganancia total
            nombres[asiento] = nombre;
            origenes[asiento] = origen;
            destinos[asiento] = destino;
            precios[asiento] = precio;
            asientosOcupados[asiento] = asiento + 1;

            System.out.println("Boleto vendido con éxito."); // Confirmación de venta
        }
    }

    /**
     * Método para simular el movimiento a la siguiente terminal.
     * En esta implementación solo muestra un mensaje.
     */
    public static void moverTerminal() {
        System.out.println("Se mueve a la siguiente terminal.");
    }

    /**
     * Método para mostrar un reporte final con los datos de todos los pasajeros.
     * Incluye la ganancia total y los detalles de los boletos vendidos.
     */
    public static void mostrarReporte() {
        System.out.println("Ganancia total: $" + gananciaTotal);
        System.out.println("Listado de pasajeros:");
        // Recorrer los asientos y mostrar información solo de los ocupados
        for (int i = 0; i < 20; i++) {
            if (asientos[i]) {
                System.out.println("Nombre: " + nombres[i]);
                System.out.println("Origen: " + origenes[i]);
                System.out.println("Destino: " + destinos[i]);
                System.out.println("Precio: $" + precios[i]);
                System.out.println("Asiento: " + asientosOcupados[i]);
                System.out.println("-----------------------------");
            }
        }
    }
}

