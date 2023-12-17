package com.imagenes.binarizacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.imagenes.herramientas.ImageSettings;

public class Binarizacion {
    public static Image binarizarImagen(Image original, int j){
        BufferedImage bfi = ImageSettings.toBufferedImage(original);

        for(int x=0; x<bfi.getWidth(); x++){
            for (int y=0; y<bfi.getHeight(); y++){
                int valorpix = bfi.getRGB(x, y);
                Color colpix = new Color(valorpix);
                int colorBase = colpix.getBlue();
                if (colorBase >= j) colorBase = 255;
                if (colorBase < j) colorBase = 0;
                colpix = new Color(colorBase, colorBase, colorBase);
                bfi.setRGB(x, y, colpix.getRGB());
                
            }
        }
        return ImageSettings.toImage(bfi);
    }
    public static Image binarizarImagen(Image original, int j, int i){
        if ( j > i){    
            int aux = i;
            i = j;
            j = aux;
        }
        BufferedImage bfi = ImageSettings.toBufferedImage(original);

        for(int x=0; x<bfi.getWidth(); x++){
            for (int y=0; y<bfi.getHeight(); y++){
                int valorpix = bfi.getRGB(x, y);
                Color colpix = new Color(valorpix);
                int colorBase = colpix.getBlue();
                if (colorBase < j && colorBase > i) colorBase = 255;
                if (colorBase < i && colorBase > j) colorBase = 0;
                colpix = new Color(colorBase, colorBase, colorBase);
                bfi.setRGB(x, y, colpix.getRGB());
                
            }
        }
        return ImageSettings.toImage(bfi);
    }
}
