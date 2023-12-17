package com.imagenes.Herramientas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Basicas {
    public static Image escalaDeGrises(Image original)
    {
        //To buffer para leer la informacion
        BufferedImage originalBuffered = ImageSettings.toBufferedImage(original);
        //Recorrer imagen buffereada
        for(int i=0; i < originalBuffered.getWidth(); i++){
            for (int j=0; j < originalBuffered.getHeight(); j++){
                //Promedio de colores RGB:
                int valorpix = originalBuffered.getRGB(i, j);
                Color whatColors = new Color(valorpix);
                int prom = ((whatColors.getRed() + whatColors.getGreen() + whatColors.getBlue())/3);
                // System.out.println(prom);
                Color bn = new Color(prom, prom, prom);
                int bnC = bn.getRGB();
                originalBuffered.setRGB(i, j, bnC);
            }
        }
        Image ImageBN = ImageSettings.toImage(originalBuffered);
        return ImageBN;
    }

     public static Image aumentarIluminacion(Image original, int m)
    {
        //To buffer para leer la informacion
        BufferedImage originalBuffered = ImageSettings.toBufferedImage(original);
        //Recorrer imagen buffereada
        for(int i=0; i < originalBuffered.getWidth(); i++){
            for (int j=0; j < originalBuffered.getHeight(); j++){
                //Obtener color:
                int valorpix = originalBuffered.getRGB(i, j);
                Color whatColors = new Color(valorpix);
                //Crear nuevo color
                whatColors = new Color(validar(whatColors.getRed()+m), validar(whatColors.getGreen()+m), validar(whatColors.getBlue()+m));
                originalBuffered.setRGB(i, j, whatColors.getRGB());
            }
        }
        Image ImageColored = ImageSettings.toImage(originalBuffered);
        return ImageColored;
    }

    public static Image calido(Image original, int h)
    {
        //To buffer para leer la informacion
        BufferedImage originalBuffered = ImageSettings.toBufferedImage(original);
        //Recorrer imagen buffereada
        for(int i=0; i < originalBuffered.getWidth(); i++){
            for (int j=0; j < originalBuffered.getHeight(); j++){
                //Obtener color:
                int valorpix = originalBuffered.getRGB(i, j);
                Color whatColors = new Color(valorpix);
                whatColors = new Color(validar(whatColors.getRed()+h), validar(whatColors.getGreen()), validar(whatColors.getBlue()-h));
                originalBuffered.setRGB(i, j, whatColors.getRGB());
            }
        }
        Image ImageColored = ImageSettings.toImage(originalBuffered);
        return ImageColored;
    }

    public static Image frio(Image original, int m)
    {
        //To buffer para leer la informacion
        BufferedImage originalBuffered = ImageSettings.toBufferedImage(original);
        //Recorrer imagen buffereada
        for(int i=0; i < originalBuffered.getWidth(); i++){
            for (int j=0; j < originalBuffered.getHeight(); j++){
                //Obtener color:
                int valorpix = originalBuffered.getRGB(i, j);
                Color whatColors = new Color(valorpix);
                whatColors = new Color(validar(whatColors.getRed()-m), validar(whatColors.getGreen()), validar(whatColors.getBlue()+m));
                originalBuffered.setRGB(i, j, whatColors.getRGB());
            }
        }
        Image ImageColored = ImageSettings.toImage(originalBuffered);
        return ImageColored;
    }

    public static Image negativeGrises(Image original, int m){
        
        //To buffer para leer la informacion
        BufferedImage originalBuffered = ImageSettings.toBufferedImage(escalaDeGrises(original));
        //Recorrer imagen buffereada
        for(int i=0; i < originalBuffered.getWidth(); i++){
            for (int j=0; j < originalBuffered.getHeight(); j++){
                //Obtener color:
                int valorpix = originalBuffered.getRGB(i, j);
                Color whatColors = new Color(m - valorpix);
                whatColors = new Color(validar(whatColors.getRed()), validar(whatColors.getGreen()), validar(whatColors.getBlue()));
                originalBuffered.setRGB(i, j, whatColors.getRGB());
            }
        }
        Image ImageNegative = ImageSettings.toImage(originalBuffered);
        return ImageNegative;
    }

    public static Image negative(Image original, int m){
        
        //To buffer para leer la informacion
        BufferedImage originalBuffered = ImageSettings.toBufferedImage(original);
        //Recorrer imagen buffereada
        for(int i=0; i < originalBuffered.getWidth(); i++){
            for (int j=0; j < originalBuffered.getHeight(); j++){
                //Obtener color:
                int valorpix = originalBuffered.getRGB(i, j);
                Color whatColors = new Color(m - valorpix);
                whatColors = new Color(validar(whatColors.getRed()), validar(whatColors.getGreen()), validar(whatColors.getBlue()));
                originalBuffered.setRGB(i, j, whatColors.getRGB());
            }
        }
        Image ImageNegative = ImageSettings.toImage(originalBuffered);
        return ImageNegative;
    }



    private static int validar (int i){
        if(i>255) return 255;
        if(i<0) return 0;
        return i;
    }
}
