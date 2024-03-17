/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mastermind;

import java.util.Scanner;

/**
 * Un programa per jugar al Mastermind
 *
 * @author Jordi Urmeneta
 */
public class MasterMind {

    //Declaració de Constants
    public final static char POS_CORRECTE = 'O';
    public final static char MALA_POSICIO = 'X';
    public final static char INCORRECTE = '-';
    public final static String ENCERTAT = "OOOOO";
    public final static int LONG_SECRET = 5;
    public final static String ABC = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Mètode principal per generar la paruala secreta i iniciar el joc
     * @param args 
     */
    public static void main(String[] args) {
        String secret = generarParaulaSecreta();
        boolean encertat = false;
        while (!encertat) {
            String resposta = preguntarResposta();
            encertat = resoldreResposta(secret, resposta);
        }
    }

    //Llistat de mètodes associats al problema general, primer primer nivell 
    //de descomposició
    
    /**
     * Generació de la paraula secreta segons la longitud establerta
     * @return una paraula de LONG_SECRET lletres (un String)
     */
    public static String generarParaulaSecreta() {
        String res = "";
        for (int i = 0; i < LONG_SECRET; i++) {
            res = res + generarLletraAleatoria();
        }
        return res;
    }
    
    /**
     * Mètode encarregat de preguntar la resposta per teclat i retornar-la
     * @return string amb la paraula de resposta
     */
    public static String preguntarResposta() {
        Scanner lector = new Scanner(System.in);
        boolean lecturaOk;
        String res = null;
        do {
            System.out.print("Escriu " + LONG_SECRET + " lletres minuscules: ");
            res = lector.next();
            lector.nextLine();
            lecturaOk = comprovarResposta(res);
            if (!lecturaOk) {
                System.out.println("Aquesta resposta no es valida!");
            }
        } while (!lecturaOk);
        return res;
    }

    /**
     * Mètode que s'encarrega de resoldre la resposta donada
     * @param secret el valor secret
     * @param resposta la resposta de l'usuari
     * @return si s'ha encertat o no
     */
    public static boolean resoldreResposta(String secret, String resposta) {
        String res = generarPista(secret, resposta);
        boolean fi = false;
        System.out.print("La resposta és [" + res + "].");
        if (res.equals(ENCERTAT)) {
            System.out.println("Has encertat!");
            fi = true;
        } else {
            System.out.println("Continua intentant-ho!");
        }
        return fi;
    }
    
    //Mètodes associats al segon nivell de descomposició
    
    /**
     * Mètode encarregat de generar una lletra aleatoria a partir de la variable
     * ABC
     * @return una lletra (un caràcter)
     */
    public static char generarLletraAleatoria() {
        long nano = System.nanoTime();
        int index = (int) (nano % ABC.length());
        return ABC.charAt(index);
    }

    /**
     * Mètode encarregat de comprovar la resposta
     * @param resposta text a comprovar
     * @return si és correcte o no
     */
    public static boolean comprovarResposta(String resposta) {
        if (resposta.length() != LONG_SECRET) {
            //Ja sabem que no és correcte.
            //Podem acabar l'execució del mètode immediatament.
            return false;
        }
        for (int i = 0; i < resposta.length(); i++) {
            char c = resposta.charAt(i);
            if (-1 == ABC.indexOf(c)) {
                //Ja sabem que no és correcte.
                //Podem acabar l'execució del mètode immediatament.
                return false;
            }
        }
        //Si tot es compleix, segur que és correcte
        return true;
    }
    
    /**
     * Mètode encarregat de generar la pista si s'escau, pot ser POS_CORRECTE,
 MALA_POSICIÓ i INCORRECTE
     * @param s el secret que cal comparar
     * @param r la resposta 
     * @return la pista que cal mostrar
     */
    public static String generarPista(String s, String r) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char charSecret = s.charAt(i);
            char charResposta = r.charAt(i);
            if (charSecret == charResposta) {
                res = res + POS_CORRECTE;
            } else if (s.indexOf(charResposta) != -1) {
                res = res + MALA_POSICIO;
            } else {
                res = res + INCORRECTE;
            }
        }
        return res;
    }
}
