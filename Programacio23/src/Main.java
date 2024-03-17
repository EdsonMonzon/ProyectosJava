import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase contiene métodos para procesar datos de un archivo de texto y generar un resumen en otro archivo.
 */
public class Main {

    /**
     * El método principal ,extrae los datos de un archivo y genera otro archivo con el resumen.
     */
    public static void main(String[] args) {
        File f = new File("datos.txt");
        ArrayList<String> generaciones = new ArrayList<>();
        ArrayList[] listaDeDatos = new ArrayList[4];

        generaciones = separadorDeGeneraciones(f);
        listaDeDatos = datosExtractor(generaciones);

        f = new File("resumen.txt");
        creadorResumen(listaDeDatos, f);
    }

    /**
     * Lee el archivo de texto y separa las generaciones.
     *
     * @param f El archivo de texto a procesar.
     * @return Una lista de cadenas, cada una representando una generación.
     */
    public static ArrayList<String> separadorDeGeneraciones(File f) {
        ArrayList<String> generaciones = new ArrayList<>();
        try (Scanner sc = new Scanner(f)) {
            String generacio = "";
            String c = "";

            while (sc.hasNextLine()) {
                generacio += sc.nextLine() + "\n";

                if (sc.hasNext()) {
                    c = sc.next();
                } else {
                    generaciones.add(generacio);
                }

                if (c.equals("Lliga")) {
                    generaciones.add(generacio);
                    generacio = c;
                } else {
                    generacio += c;
                }
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        return generaciones;
    }

    /**
     * Extrae datos de un ArrayList de Strings.
     *
     * @param generaciones Lista con el arhivo de texto separado por generaciones en formato String.
     * @return Una lista de arreglos que contienen los datos extraídos.
     */
    public static ArrayList[] datosExtractor(ArrayList<String> generaciones) {
        ArrayList[] listaFinal = new ArrayList[4];
        ArrayList<String> promediosEdad = new ArrayList<>();
        ArrayList<String> promediosAltura = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> capceleras = new ArrayList<>();

        for (String generacion : generaciones) {
            String bloqueNombres = "";
            double sumaEdades = 0;
            double sumaAlturas = 0;
            int contador = 0;

            try (Scanner sc = new Scanner(generacion)) {
                while (sc.hasNext()) {
                    String c = "";

                    if (!sc.hasNextInt() && !sc.hasNextDouble()) {
                        if (sc.hasNext()) {
                            c = sc.next();
                        }
                        if (c.equals("Lliga")) {
                            capceleras.add(c + sc.nextLine());
                        } else {
                            bloqueNombres += c + "\n";
                            contador++;
                        }
                    } else if (sc.hasNextInt()) {
                        sumaEdades += sc.nextInt();
                    } else {
                        sumaAlturas += sc.nextDouble();
                    }
                }
            }

            sumaAlturas /= contador;
            sumaEdades /= contador;

            nombres.add(bloqueNombres);
            promediosAltura.add(String.valueOf(((int) (sumaAlturas * 100)) / 100.0));
            promediosEdad.add(String.valueOf(((int) (sumaEdades * 100)) / 100.0));
        }

        listaFinal[0] = capceleras;
        listaFinal[1] = nombres;
        listaFinal[2] = promediosEdad;
        listaFinal[3] = promediosAltura;

        return listaFinal;
    }

    /**
     * Crea un resumen con todos los datos en un archivo de texto.
     *
     * @param datos Lista de arreglos que contienen los datos.
     * @param f     El archivo donde se escribirá el resumen.
     */
    public static void creadorResumen(ArrayList[] datos, File f) {
        try (FileWriter fw = new FileWriter(f)) {
            for (int i = 0; i < datos[0].size(); i++) {
                fw.write(datos[0].get(i).toString() + "\n");
                fw.write("Los sujetos del estudio son:\n");
                fw.write(datos[1].get(i).toString());
                fw.write("El promedio de edades de esta generacion es: ");
                fw.write(datos[2].get(i).toString() + "\n");
                fw.write("El promedio de altura de esta generacion es: ");
                fw.write(datos[3].get(i).toString() + "\n");
                fw.write("\n");
            }
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
