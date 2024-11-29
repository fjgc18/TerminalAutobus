package mx.itson.terminal;

import java.util.Scanner;

/**
 *
 * @author Thinkpad
 */


/**
 * Programa que simula un viaje en autobus en Sonora.
 * Incluye funcionalidades para vender boletos, mover entre terminales,
 * y generar un reporte final del viaje.
 */
public class TerminalDeAutobuses {

    static final String[] TERMINALES = { 
        "Navojoa", "Obregón", "Empalme", "Guaymas", "Hermosillo", 
        "Santa Ana", "Magdalena", "Imuris", "Nogales"
    };

    static final int NUM_ASIENTOS = 20;
    static boolean[] asientos = new boolean[NUM_ASIENTOS];
    static String[] nombres = new String[NUM_ASIENTOS];
    static String[] destinos = new String[NUM_ASIENTOS];
    static String[] origenes = new String[NUM_ASIENTOS];
    static double[] precios = new double[NUM_ASIENTOS];

    static int terminalActual = 0; // Indica la terminal actual
    static double gananciaTotal = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        // Iniciar todos los asientos como disponibles
        for (int i = 0; i < NUM_ASIENTOS; i++) {
            asientos[i] = false;
        }

        while (opcion != 4) {
            System.out.println("\nTerminal de Autobuses");
            System.out.println("Actualmente estamos en: " + TERMINALES[terminalActual]);
            System.out.println("1. Vender boleto");
            System.out.println("2. Mover a siguiente terminal");
            System.out.println("3. Mostrar reporte final");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();

            if (opcion == 1) {
                venderBoleto(sc);
            } else if (opcion == 2) {
                moverTerminal();
            } else if (opcion == 3) {
                mostrarReporte();
            } else if (opcion == 4) {
                System.out.println("Gracias por usar el sistema.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }

    public static void venderBoleto(Scanner sc) {
        mostrarAsientos();

        System.out.print("Selecciona el número de asiento (1-20): ");
        int asiento = sc.nextInt() - 1;

        if (asiento < 0 || asiento >= NUM_ASIENTOS || asientos[asiento]) {
            System.out.println("El asiento seleccionado no está disponible.");
            return;
        }

        sc.nextLine(); 
        System.out.print("Introduce el nombre del pasajero: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el destino del pasajero: ");
        String destino = sc.nextLine();
        System.out.print("Introduce el precio del boleto: ");
        double precio = sc.nextDouble();

       

        // Registrar datos del pasajero
        asientos[asiento] = true;
        nombres[asiento] = nombre;
        origenes[asiento] = TERMINALES[terminalActual];
        destinos[asiento] = destino;
        precios[asiento] = precio;
        gananciaTotal += precio;

        System.out.println("Boleto vendido con exito.");
    }

    public static void moverTerminal() {
        if (terminalActual < TERMINALES.length - 1) {
            terminalActual++;
            int pasajerosBajados = 0;

            // Liberar asientos de pasajeros cuyo destino es la terminal actual
            for (int i = 0; i < NUM_ASIENTOS; i++) {
                if (asientos[i] && destinos[i].equalsIgnoreCase(TERMINALES[terminalActual])) {
                    asientos[i] = false;
                    nombres[i] = null;
                    destinos[i] = null;
                    origenes[i] = null;
                    precios[i] = 0;
                    pasajerosBajados++;
                }
            }

            System.out.println("Nos hemos movido a: " + TERMINALES[terminalActual]);
            System.out.println("Se bajaron " + pasajerosBajados + " pasajeros.");
        } else {
            System.out.println("Ya estamos en la ultima terminal: " + TERMINALES[terminalActual]);
        }
    }

    public static void mostrarReporte() {
        System.out.println("\nREPORTE FINAL DEL VIAJE");
        System.out.println("Terminal final: " + TERMINALES[TERMINALES.length - 1]);
        System.out.println("Ganancia total: $" + gananciaTotal);
        System.out.println("Listado de pasajeros:");

        for (int i = 0; i < NUM_ASIENTOS; i++) {
            if (nombres[i] != null) {
                System.out.println("Nombre: " + nombres[i]);
                System.out.println("Origen: " + origenes[i]);
                System.out.println("Destino: " + destinos[i]);
                System.out.println("Precio: $" + precios[i]);
                System.out.println("Asiento: " + (i + 1));
                System.out.println("-----------------------------");
            }
        }
    }

     public static void mostrarAsientos() {
        System.out.println("\nEstado de los asientos:");
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 2; columna++) {
                int asientoIzquierdo = columna * 10 + fila; 
                int asientoDerecho = asientoIzquierdo + 5; 

                if (asientos[asientoIzquierdo]) {
                    System.out.print("[X] "); // Asiento ocupado
                } else {
                    System.out.print("[" + (asientoIzquierdo + 1) + "] "); // Asiento libre
                }

                if (asientos[asientoDerecho]) {
                    System.out.print("[X] "); // Asiento ocupado
                } else {
                    System.out.print("[" + (asientoDerecho + 1) + "] "); // Asiento libre
                }
                System.out.println();
            }
         
        }
    }
}


