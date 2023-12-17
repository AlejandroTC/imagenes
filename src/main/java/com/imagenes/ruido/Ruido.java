package com.imagenes.ruido;

import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.imagenes.herramientas.ImageSettings;

public class Ruido {
    public static Image agergarRuidoAditivo(Image original, double por){
        Random ran = new Random();
        BufferedImage bi = ImageSettings.toBufferedImage(original);
        // double aux = por/100;
        int cp = (int) ((por/100)*(bi.getHeight()*bi.getWidth()));
        Color aditivo = new Color(255, 255, 255);
        int x, y;
        for(int j=0; j<cp; j++){
            //posicion aleatoria dentro de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            bi.setRGB(x, y, aditivo.getRGB());
        }
        return  null;
    }
    public static Image agregarRuidoSustractivo(Image original, double porcentaje){
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        int cantidadPixeles = (int) ((porcentaje / 100) * (bi.getHeight() * bi.getWidth()));
        Random ran = new Random();
        int x, y;
        for(int j = 0; j < cantidadPixeles; j++){
            // Posición aleatoria dentro de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            Color pixelColor = new Color(bi.getRGB(x, y));
            int ruido = ran.nextInt(256); // Valor de ruido sustractivo
            // Resta el ruido al valor de cada componente RGB
            int nuevoRojo = Math.max(0, pixelColor.getRed() - ruido);
            int nuevoVerde = Math.max(0, pixelColor.getGreen() - ruido);
            int nuevoAzul = Math.max(0, pixelColor.getBlue() - ruido);
            bi.setRGB(x, y, new Color(nuevoRojo, nuevoVerde, nuevoAzul).getRGB());
        }
        return original; // Devuelve la imagen con ruido sustractivo
    }
    
    // ToDo: Agregar ruido mezclado (primero aditivo y luego sustractivo) a la imagen original con las intensidades especificadas
    public static Image agregarRuidoMezclado(Image original, double porcentajeAditivo, double porcentajeSustractivo){
        // Aplica primero el ruido aditivo
        Image conRuidoAditivo = agregarRuidoAditivo(original, porcentajeAditivo);
        // Aplica luego el ruido sustractivo sobre la imagen con ruido aditivo
        Image conRuidoMezclado = agregarRuidoSustractivo(conRuidoAditivo, porcentajeSustractivo);
        return conRuidoMezclado; // Devuelve la imagen con ruido mezclado
    }
    
}
