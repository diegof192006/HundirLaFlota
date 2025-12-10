package HundirLaFlota;

public class Disparos {

    /**Devuelve true si ya se ha disparado previamente a esta casilla.*/
    public static boolean yaDisparado(char[][] tableroDisparos, int fila, int columna) {
        return tableroDisparos[fila][columna] != '~';
    }

    /**Procesa un disparo en (fila, columna).*/
    public static boolean procesarDisparo(
            int fila,
            int columna,
            int[][] tableroBarcos,
            char[][] tableroDisparos,
            int[] impactosBarco,
            int[] tamanosBarco
    ) {

        int idBarco = tableroBarcos[fila][columna];
        // 1. AGUA
        if (idBarco == -1) {
            tableroDisparos[fila][columna] = 'A'; // agua
            return false; // no hundido
        }
        // 2. TOCADO

        impactosBarco[idBarco]++;  // sumamos impacto al barco
        int impactosActuales = impactosBarco[idBarco];
        int tamanoBarco = tamanosBarco[idBarco];

        // Si aún no está hundido → Marcamos "T"
        if (impactosActuales < tamanoBarco) {
            tableroDisparos[fila][columna] = 'T'; // tocado
            return false;
        }
        // 3. HUNDIDO
        if (impactosActuales == tamanoBarco) {

            // Recorremos tableroBarcos para marcar todas las posiciones de este barco como 'H'
            for (int f = 0; f < tableroBarcos.length; f++) {
                for (int c = 0; c < tableroBarcos[0].length; c++) {
                    if (tableroBarcos[f][c] == idBarco) {
                        tableroDisparos[f][c] = 'H'; // hundido
                    }
                }
            }

            return true; // barco hundido en este disparo
        }

        return false;
    }
}

