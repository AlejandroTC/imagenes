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
}
