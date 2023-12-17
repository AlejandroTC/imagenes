package com.imagenes.ruido;

import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.imagenes.herramientas.ImageSettings;

public class Ruido {
    
    public static Image agregarRuidoAditivo(Image original, double por){
        BufferedImage bi = ImageSettings.toBufferedImage(original);
        // double aux = por/100;
        int cp = (int)((por/100)*(bi.getHeight()*bi.getWidth()));
        Color aditivo = new Color(255,255,255);
        Random ran = new Random();
        int x,y;
        for(int j=0; j<cp;j++){
            // posicion aleatoria dentro de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            bi.setRGB(x, y, aditivo.getRGB());
        }
        
        return ImageSettings.toImage(bi);
    }

    public static Image agregarRuidoSustractivo(Image original, double porcentaje) {
        BufferedImage bi = ImageSettings.toBufferedImage(original);
        int cantidadPixeles = (int) ((porcentaje / 100) * (bi.getHeight() * bi.getWidth()));
        Random ran = new Random();
        int x, y;
        
        for (int j = 0; j < cantidadPixeles; j++) {
            // Posición aleatoria dentro de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            Color pixelColor = new Color(bi.getRGB(x, y));

            // Valor de ruido sustractivo
            int ruido = ran.nextInt(256);

            // Resta el ruido al valor de cada componente RGB, usando Math.min para evitar valores negativos
            int nuevoRojo = Math.min(255, Math.max(0, pixelColor.getRed() - ruido));
            int nuevoVerde = Math.min(255, Math.max(0, pixelColor.getGreen() - ruido));
            int nuevoAzul = Math.min(255, Math.max(0, pixelColor.getBlue() - ruido));

            bi.setRGB(x, y, new Color(nuevoRojo, nuevoVerde, nuevoAzul).getRGB());
        }

        return ImageSettings.toImage(bi); // Devuelve la imagen con ruido sustractivo
    }

    public static Image agregarRuidoMezclado(Image original, double porcentajeAditivo, double porcentajeSustractivo){
        // Aplica primero el ruido aditivo
        Image conRuidoAditivo = agregarRuidoAditivo(original, porcentajeAditivo);
        // Aplica luego el ruido sustractivo sobre la imagen con ruido aditivo
        Image conRuidoMezclado = agregarRuidoSustractivo(conRuidoAditivo, porcentajeSustractivo);
        return conRuidoMezclado; // Devuelve la imagen con ruido mezclado
    }
    
}
