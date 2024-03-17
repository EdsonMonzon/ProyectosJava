import java.util.Scanner;

public class inputU {
    final static Scanner input=new Scanner(System.in);

    public static int between(int a,int b, String mensaje){
        int numero=0;
        
        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println(mensaje);
            numero=between(a,b,mensaje);
        }

        return numero;
    }

    public static int between(int a,int b){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println("El numero introducido no es valido , vuelva a intentarlo");
            numero=between(a,b);
        }

        return numero;
    }
}
