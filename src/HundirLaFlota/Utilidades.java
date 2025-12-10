package HundirLaFlota;

import java.util.Scanner;

public class Utilidades {

    private static final Scanner SC = new Scanner(System.in);

    /*Devuelve un número entero aleatorio entre min y max*/
    public static int numeroAleatorio(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /*Convierte una coordenada tipo "A5" en la fila (parte numérica).*/
    public static int convertirFila(String coord) {

        if (coord == null || coord.length() < 2)
            return -1;
        String numero = coord.substring(1);

        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /*Convierte una coordenada tipo "A5" en la columna y devolver -1 si no es válido.
     */
    public static int convertirColumna(String coord) {

        if (coord == null || coord.length() < 2)
            return -1;

        char letra = Character.toUpperCase(coord.charAt(0));

        if (letra < 'A' || letra > 'Z')
            return -1;

        return letra - 'A'; // A=0, B=1...
    }
    public static String leerLinea() {
        return SC.nextLine();
    }
}
