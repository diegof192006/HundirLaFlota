package HundirLaFlota;

public class Barcos {

    /**Coloca todos los barcos indicados en tamanosBarcos.*/
    public static void colocarBarcosAleatorios(int[][] tableroBarcos, int[] tamanosBarcos) {

        for (int id = 0; id < tamanosBarcos.length; id++) {

            boolean colocado = false;
            int tam = tamanosBarcos[id];

            while (!colocado) {
                int fila = (int) (Math.random() * tableroBarcos.length);
                int columna = (int) (Math.random() * tableroBarcos[0].length);
                boolean horizontal = Math.random() < 0.5;

                if (sePuedeColocarBarco(tableroBarcos, fila, columna, tam, horizontal)) {
                    colocarBarco(tableroBarcos, fila, columna, tam, horizontal, id);
                    colocado = true;
                }
            }
        }
    }

    /**Comprueba si un barco cabe.*/
    public static boolean sePuedeColocarBarco(int[][] tablero, int fila, int columna, int tamano, boolean horizontal) {

        int filas = tablero.length;
        int columnas = tablero[0].length;

        if (horizontal) {
            if (columna + tamano > columnas) return false;

            for (int c = columna; c < columna + tamano; c++)
                if (tablero[fila][c] != -1) return false;

        } else {
            if (fila + tamano > filas) return false;

            for (int f = fila; f < fila + tamano; f++)
                if (tablero[f][columna] != -1) return false;
        }

        return true;
    }

    /**Coloca realmente el barco en el tablero asignando su ID.*/
    public static void colocarBarco(int[][] tablero, int fila, int columna, int tamano, boolean horizontal, int idBarco) {

        if (horizontal) {
            for (int c = columna; c < columna + tamano; c++)
                tablero[fila][c] = idBarco;

        } else {
            for (int f = fila; f < fila + tamano; f++)
                tablero[f][columna] = idBarco;
        }
    }

    /**Devuelve true si TODOS los barcos han sido hundidos.*/
    public static boolean todosHundidos(int[] impactos, int[] tamanosBarcos) {

        for (int i = 0; i < tamanosBarcos.length; i++) {
            if (impactos[i] < tamanosBarcos[i]) return false;
        }

        return true;
    }
}
