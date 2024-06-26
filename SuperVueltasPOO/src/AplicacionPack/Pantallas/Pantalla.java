package AplicacionPack.Pantallas;

import AplicacionPack.Layout.Boton;
import AplicacionPack.Layout.TextBox;
import AplicacionPack.Layout.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//Clase que extiende de JPanel, se inserta en la ventana
public abstract class Pantalla extends JPanel {

    private String tipoDePantalla;
    private BufferedImage imagen;
    private ArrayList<Boton>botones=new ArrayList<>();
    private ArrayList<TextBox>textBoxes=new ArrayList<>();
    private ArrayList<Label>labels=new ArrayList<>();

    /**
     * Al ser iniciado se le coloca el nombre de la pantalla que se pasa por parametro
     * @param tipoDePantalla, nombre que tendra la pantalla
     */
    public Pantalla(String tipoDePantalla) {
        this.tipoDePantalla = tipoDePantalla;

        //Verifica si un boton fue presionado y repinta constantemente
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for (Boton b : botones) {
                    b.verificaPressBoton(x, y);
                }
                repaint();
            }
        });

        //Verifica la posicion del mouse y repinta constantemente
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for (Boton b : botones) {
                    b.verificaSobreBoton(x, y);
                }
                repaint();
            }
        });
    }

    //Contiene todas las cajas de texto de la pantalla
    public ArrayList<TextBox> getTextBoxes() {
        return textBoxes;
    }

    //Contiene todos los botones de la pantalla
    public ArrayList<Boton> getBotones() {
        return botones;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public void setTipoDePantalla(String tipoDePantalla) {
        this.tipoDePantalla = tipoDePantalla;
    }

    public String getTipoDePantalla() {
        return tipoDePantalla;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage getImagen() {
        return imagen;
    }


    //Al repintar
    protected void repintar(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // Escalar la imagen de fondo al tamaño de la pantalla
        g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);


        //Escala todos los botones, les cambia el sprite de ser nesesario y repinta
        for(Boton b : botones){
            b.escalaBoton();
            b.cambiaImgBoton();
            b.dibujar(g2d);
        }

        for(Label l : labels){
            l.dibujar(g2d);
        }

        //Igual con las text boxes
        for(TextBox t : textBoxes){
            t.escalaTextBox();
            t.dibujar(g2d);
        }
    }
}
