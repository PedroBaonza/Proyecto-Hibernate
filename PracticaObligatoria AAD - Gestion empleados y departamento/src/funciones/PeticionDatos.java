package funciones;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PeticionDatos {

    BufferedReader lectorConsola = new BufferedReader(new InputStreamReader(System.in));

    public int escribirNumero(String frase) {
        int numero = 0;
        boolean esValido = false;
        while (!esValido) {
            try {
                System.out.print(frase);
                numero = Integer.parseInt(lectorConsola.readLine());
                esValido = true;
            } catch (Exception e) {
                System.out.println("Error: Por favor, ingresa un número válido.");
            }
        }
        return numero;
    }
    
    public String escribirCadena(String frase) {
        String cadena = "";
        boolean esValido = false;
        while (!esValido) {
            try {
                System.out.print(frase);
                cadena = lectorConsola.readLine();
                esValido = true;
            } catch (Exception e) {
                System.out.println("Error: Por favor, ingresa una cadena válida.");
            }
        }
        return cadena;
    }
    
    public float escribirFloat(String frase) {
        float numero = 0;
        boolean esValido = false;
        while (!esValido) {
            try {
                System.out.print(frase);
                numero = Float.parseFloat(lectorConsola.readLine());
                esValido = true;
            } catch (Exception e) {
                System.out.println("Error: Por favor, ingresa un número decimal válido.");
            }
        }
        return numero;
    }
}
