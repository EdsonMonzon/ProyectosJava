public class Casilla{
    int numero;
    int filaPos=0;
    int columnaPos=0;
    int valor;
    boolean hayFicha=false;
    boolean revelada;
    public Casilla(int i, int j, int valor){
        this.filaPos=i;
        this.columnaPos=j;
        this.valor=valor;

    }
}
