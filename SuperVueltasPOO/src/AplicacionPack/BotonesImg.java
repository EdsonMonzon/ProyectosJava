package AplicacionPack;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BotonesImg {
    //Al llamar con un get y un id me devuelva la bufferedImg, esto para cada tipo

    static String[] PantallasImg = new String[]
            {"Imagenes/imgFondos/PantallaInicio.jpg",
                    "Imagenes/imgFondos/PantallaModosJuego.jpg",
                    "Imagenes/imgFondos/PantallaPartidaCompetitiva.jpg",
                    "Imagenes/imgFondos/PantallaPartidaPersonalizada.jpg",
                    "Imagenes/imgFondos/PantallaCargarPartida.jpg",
                    "Imagenes/imgFondos/PantallaSeleccionHeroe.jpg"};
    static String[] botonesNormales = new String[]
            {"Imagenes/imgBotones/imgBotonesNormales/BotonAtras_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonStart_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonSalir_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonPartidaCompetitiva_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonPartidaPersonalizada_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargarPartida_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargar1_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargar2_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargar3_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/Boton2Jugadores_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/Boton3Jugadores_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/Boton4Jugadores_Normal.jpg",
                    "Imagenes/imgHeroes/imgHeroesNormales/HeroeArquero_Normal.jpg",
                    "Imagenes/imgHeroes/imgHeroesNormales/HeroeBanquero_Normal.jpg",
                    "Imagenes/imgHeroes/imgHeroesNormales/HeroeCorredor_Normal.jpg",
                    "Imagenes/imgHeroes/imgHeroesNormales/HeroePaladin_Normal.jpg"};
    static String[] botonesHover = new String[]
            {"Imagenes/imgBotones/imgBotonesHover/BotonAtras_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonStart_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonSalir_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonPartidaCompetitiva_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonPartidaPersonalizada_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargarPartida_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargar1_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargar2_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargar3_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/Boton2Jugadores_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/Boton3Jugadores_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/Boton4Jugadores_Hover.jpg",
                    "Imagenes/imgHeroes/imgHeroesHover/HeroeArquero_Hover.jpg",
                    "Imagenes/imgHeroes/imgHeroesHover/HeroeBanquero_Hover.jpg",
                    "Imagenes/imgHeroes/imgHeroesHover/HeroeCorredor_Hover.jpg",
                    "Imagenes/imgHeroes/imgHeroesHover/HeroePaladin_Hover.jpg"
            };
    static String[] botonesPress = new String[]
            {"Imagenes/imgBotones/imgBotonesPress/BotonAtras_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonStart_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonSalir_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonPartidaCompetitiva_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonPartidaPersonalizada_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargarPartida_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargar1_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargar2_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargar3_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/Boton2Jugadores_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/Boton3Jugadores_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/Boton4Jugadores_Press.jpg",
                    "Imagenes/imgHeroes/imgHeroesPress/HeroeArquero_Press.jpg",
                    "Imagenes/imgHeroes/imgHeroesPress/HeroeBanquero_Press.jpg",
                    "Imagenes/imgHeroes/imgHeroesPress/HeroeCorredor_Press.jpg",
                    "Imagenes/imgHeroes/imgHeroesPress/HeroePaladin_Press.jpg"
            };

    public static String getPantallasImg(int i) {
        return PantallasImg[i-1];
    }

    public static String getBotonesNormales(int i) {
        return botonesNormales[i-1];
    }

    public static String getBotonesHover(int i) {
        return botonesHover[i-1];
    }

    public static String getBotonesPress(int i) {
        return botonesPress[i-1];
    }

}
