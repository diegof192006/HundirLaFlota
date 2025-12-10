package HundirLaFlota;

public class Tablero {
/**Crea un tablero de barcos e inicializa*/
public static int[][] crearTableroBarcos(int filas, int columnas) {
    int[][] tablero = new int[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            tablero[i][j] = -1;  // agua
        }
    }

    return tablero;
}

/*'~' significa "no disparado".*/
public static char[][] crearTableroDisparos(int filas, int columnas) {
    char[][] tablero = new char[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            tablero[i][j] = '~'; // no disparado
        }
    }

    return tablero;}
    /*Muestra un tablero de disparos imprimiendo coordenadas de filas y columnas.*/
    public static void mostrarTableroDisparos(char[][] tableroDisparos) {

        int filas = tableroDisparos.length;
        int columnas = tableroDisparos[0].length;

        // Encabezado
        System.out.print("   ");
        for (int c = 0; c < columnas; c++)
            System.out.print(c + " ");
        System.out.println();

        // Filas
        for (int f = 0; f < filas; f++) {
            System.out.print(f + "  ");
            for (int c = 0; c < columnas; c++) {
                System.out.print(tableroDisparos[f][c] + " ");
            }
            System.out.println();
        }
    }

}
