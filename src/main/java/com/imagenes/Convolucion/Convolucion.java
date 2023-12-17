package com.imagenes.Convolucion;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.imagenes.Herramientas.ImageSettings;

/*
 * ToDo: tenemos que ddeterminar el divisior
 * offset: es un valor que se añade al resultante
 */

public class Convolucion {
    // mascaras de deteccion de bordes
    BufferedImage imagenOriginal;

    public Convolucion(BufferedImage imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }
    // convolucion simple    
    public Image convolucionar(int[] mascara, int div){
        Color nuevo;
        // Color f = new Color(this.imagenOriginal.getRGB(0,0));
        // System.out.println();
        // el proceso de convolucion se hace para todos los pixeles
        for(int x=0;x<this.imagenOriginal.getWidth();x++){
            for(int y=0;y<this.imagenOriginal.getHeight();y++){
                // tenemos un nuevo tono
                int rgb = convolucionar(x,y,mascara,div);
                nuevo = new Color(rgb);
                this.imagenOriginal.setRGB(x, y, nuevo.getRGB());
            }
        }
        return ImageSettings.toImage(imagenOriginal);
    }    

    private int convolucionar(int x, int y, int[] mascara, int div) {
    //    //recorremos la mascara
    //    for(int j=0;j<mascara.length;j++){
    //        for(int m=0;m<mascara.length;m++){
    //            // verificar si la mascara es diferente a 0
    //            if(mascara[j][m]!=0){
    //                // convolucionar
    //            }
    //        }
    //    }
        Color aux;
        int aR=0,aG=0,aB=0;
        for(int j=0; j < mascara.length;j++){
            // canales     
            int rgb = obtenerRGB(x,y,j);
            if(rgb!=0){
                aux = new Color(rgb);
                aR+=aux.getRed()*mascara[j];
                aG+=aux.getGreen()*mascara[j];
                aB+=aux.getBlue()*mascara[j];
            }
        }
        // evaluar la división
        aR/=div;
        aG/=div;
        aB/=div;
        aux = new Color(aR, aG, aB);
        return aux.getRGB();
    }

    private int obtenerRGB(int x, int y, int j) {
        int rgb = 0;
        switch (j){
            case 0:{
                if((x-1<0) || (y-1<0)) return 0;
                if((x-1>=this.imagenOriginal.getHeight())
                        ||(y-1>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x-1, y-1);
                break;
            }
            case 1:{
                if((x-1<0) || (y<0)) return 0;
                if((x-1>=this.imagenOriginal.getHeight())
                        ||(y>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x-1, y);
                break;
            }
            case 2:{
                if((x-1<0) || (y+1<0)) return 0;
                if((x-1>=this.imagenOriginal.getHeight())
                        ||(y+1>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x-1, y+1);
                break;
            }
            case 3:{
                if((x<0) || (y-1<0)) return 0;
                if((x>=this.imagenOriginal.getHeight())
                        ||(y-1>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x, y-1);
                break;
            }
            
            case 4:{
                if((x<0) || (y<0)) return 0;
                if((x>=this.imagenOriginal.getHeight())
                        ||(y>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x, y);
                break;
            }
            
            case 5:{
                if((x<0) || (y+1<0)) return 0;
                if((x>=this.imagenOriginal.getHeight())
                        ||(y+1>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x, y+1);
                break;
            }
            
             case 6:{
                if((x+1<0) || (y-1<0)) return 0;
                if((x+1>=this.imagenOriginal.getHeight())
                        ||(y-1>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x+1, y-1);
                break;
            }
             
            case 7:{
                if((x+1<0) || (y<0)) return 0;
                if((x+1>=this.imagenOriginal.getHeight())
                        ||(y>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x+1, y);
                break;
            }
            
            case 8:{
                if((x+1<0) || (y+1<0)) return 0;
                if((x+1>=this.imagenOriginal.getHeight())
                        ||(y+1>=this.imagenOriginal.getWidth()))return 0; 
                rgb = this.imagenOriginal.getRGB(x+1, y+1);
                break;
            }   
        }
        return rgb;
    }
}
