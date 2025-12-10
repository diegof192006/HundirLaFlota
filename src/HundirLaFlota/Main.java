package HundirLaFlota;

import java.util.Scanner;

public class Main {

    public static final int FILAS = 10;
    public static final int COLUMNAS = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Tamaños de los barcos
        int[] tamanosBarcos = {5, 4, 3, 3, 2};
        int numBarcos = tamanosBarcos.length;
        // Tableros
        int[][] tableroBarcosJugador = Tablero.crearTableroBarcos(FILAS, COLUMNAS);
        int[][] tableroBarcosCPU = Tablero.crearTableroBarcos(FILAS, COLUMNAS);
        char[][] tableroDisparosJugador = Tablero.crearTableroDisparos(FILAS, COLUMNAS);
        char[][] tableroDisparosCPU = Tablero.crearTableroDisparos(FILAS, COLUMNAS);
        // Arrays de los impactos
        int[] impactosJugador = new int[numBarcos];
        int[] impactosCPU = new int[numBarcos];
        for (int i = 0; i < numBarcos; i++) {
            impactosJugador[i] = 0;
            impactosCPU[i] = 0;
        }
        // Colocación de los barcos del jugador
        System.out.println("Colocando barcos del jugador...");
        Barcos.colocarBarcosAleatorios(tableroBarcosJugador, tamanosBarcos);
        System.out.println("Colocando barcos de la CPU...");
        Barcos.colocarBarcosAleatorios(tableroBarcosCPU, tamanosBarcos);

        boolean finPartida = false;
        boolean turnoJugador = true;

        while (!finPartida) {
            System.out.println();
            System.out.println("=============================");
            System.out.println("HUNDIR LA FLOTA - NUEVO TURNO");
            System.out.println("=============================");

            if (turnoJugador) {

                System.out.println("Turno del JUGADOR");

                System.out.println("Tu tablero (con tus barcos):");
                Tablero.mostrarTableroConBarcos(tableroBarcosJugador, tableroDisparosCPU);

                System.out.println("Tus disparos sobre la CPU:");
                Tablero.mostrarTableroDisparos(tableroDisparosJugador);

                // Pedir coordenada
                String coord;
                int fila, columna;

                while (true) {
                    System.out.print("Introduce coordenada (ej. A5): ");
                    coord = sc.nextLine().trim().toUpperCase();

                    fila = Utilidades.convertirFila(coord);
                    columna = Utilidades.convertirColumna(coord);

                    if (fila == -1 || columna == -1) {
                        System.out.println("Formato inválido. Intenta de nuevo.");
                        continue;
                    }

                    break;
                }

                if (!Tablero.esCoordenadaValida(fila, columna, FILAS, COLUMNAS)) {
                    System.out.println("Coordenada fuera del tablero. Pierdes el turno.");
                } else if (Disparos.yaDisparado(tableroDisparosJugador, fila, columna)) {
                    System.out.println("Ya habías disparado ahí. Pierdes el turno.");
                } else {

                    boolean hundido = Disparos.procesarDisparo(
                            fila,
                            columna,
                            tableroBarcosCPU,
                            tableroDisparosJugador,
                            impactosCPU,
                            tamanosBarcos
                    );

                    if (hundido) {
                        System.out.println("¡Has hundido un barco enemigo!");
                    } else {
                        System.out.println("Disparo realizado.");
                    }
                }

                // ¿Ha perdido la CPU?
                if (Barcos.todosHundidos(impactosCPU, tamanosBarcos)) {
                    System.out.println("¡HAS GANADO! La CPU ha perdido todos sus barcos.");
                    finPartida = true;
                }

            } else {
                System.out.println("Turno de la CPU");

                int filaCPU, columnaCPU;

                // Buscar coordenada no usada
                while (true) {
                    filaCPU = Utilidades.numeroAleatorio(0, FILAS - 1);
                    columnaCPU = Utilidades.numeroAleatorio(0, COLUMNAS - 1);

                    if (!Disparos.yaDisparado(tableroDisparosCPU, filaCPU, columnaCPU)) {
                        break;
                    }
                }

                System.out.println("La CPU dispara a (" + filaCPU + ", " + columnaCPU + ")");

                boolean hundidoJugador = Disparos.procesarDisparo(
                        filaCPU,
                        columnaCPU,
                        tableroBarcosJugador,
                        tableroDisparosCPU,
                        impactosJugador,
                        tamanosBarcos
                );

                if (hundidoJugador) {
                    System.out.println("La CPU te ha hundido un barco.");
                }

                // ¿Ha perdido el jugador?
                if (Barcos.todosHundidos(impactosJugador, tamanosBarcos)) {
                    System.out.println("Has perdido. La CPU ha hundido todos tus barcos.");
                    finPartida = true;
                }
            }

            turnoJugador = !turnoJugador;
        }

        sc.close();
        System.out.println("Fin de la partida.");
    }
}
