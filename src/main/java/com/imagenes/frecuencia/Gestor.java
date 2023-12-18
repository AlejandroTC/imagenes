package com.imagenes.frecuencia;

import java.awt.image.BufferedImage;

import com.imagenes.frecuencia.ColorSettings.*;
import com.imagenes.herramientas.Basicas;

public class Gestor {
    NumeroComplejo[][] transformada;
    
    public Gestor() {
    
    }
     
    
public NumeroComplejo[][] obtenerDatosPorCanal(BufferedImage imagenOriginal, CanalColor color) {
        NumeroComplejo[][] aux = new NumeroComplejo[imagenOriginal.getWidth()][imagenOriginal.getHeight()];
        // obtenemos los datos por canal
        for (int y = 0; y < imagenOriginal.getHeight(); y++) {
            for (int x = 0; x < imagenOriginal.getWidth(); x++) {
                aux[x][y] = new NumeroComplejo(ColorSettings.obtenerValorPorCanal(imagenOriginal.getRGB(x, y), color), 0);
            }
        }
        return aux;
    }

    public BufferedImage obtenerImagenFrecuencias(NumeroComplejo[][] datosIO,int w, int h,boolean reAjustarCuadrante) {
        /// obtenemos las dimensiones
        int anchoImagen = w;
        int altoImagen = h;
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT
     
        this.transformada = fft.calculateFT(datosIO, false);
       
            // crear la imagen del espectro 
            for (int y = 0; y < aux.getHeight(); y++) {
                for (int x = 0; x < aux.getWidth(); x++) {
                    // modificamos la posicion de los cuadrantes 
                    int ejeX = reAjustarCuadrante ? (x + (anchoImagen / 2)) % anchoImagen : x;
                    int ejeY = reAjustarCuadrante ? (y + (altoImagen / 2)) % altoImagen : y;
                    // setear la info a la imagen 
                    // el que se ecuentre en la imagen 
                    int color1 = aux.getRGB(x, y);
                    int color2 = obtenerColorRealDeFrecuencia(ejeX, ejeY, transformada, ColorSettings.CanalColor.VERDE);
                    int rgb = ColorSettings.acumularColor(color1, color2);
                    aux.setRGB(x, y, rgb);

                }
            }
        
        return aux;
    }
    
     public BufferedImage obtenerImagenEspacial() {
        /// obtenemos las dimensiones
        int anchoImagen = this.transformada.length;
        int altoImagen = this.transformada.length;
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT

            NumeroComplejo[][] transformadaInv = fft.calculateFT(this.transformada, true);
           
            // crear la imagen del espectro 
            for (int y = 0; y < aux.getHeight(); y++) {
                for (int x = 0; x < aux.getWidth(); x++) {

                    int color = (int) Math.abs(transformadaInv[x][y].getParteReal());
                    color = Basicas.validar(color);
                    color = ColorSettings.obtenerRGBPorCanal(color, ColorSettings.CanalColor.ROJO);

                    int rgb = ColorSettings.acumularColor(aux.getRGB(x, y), color);
                    aux.setRGB(x, y, rgb);
                }
            }
       
        return aux;

    }

    private int obtenerColorRealDeFrecuencia(int ejeX, int ejeY, NumeroComplejo[][] transformada, CanalColor canal) {
        int color = (int) Math.abs(transformada[ejeX][ejeY].getParteReal());
        color = Basicas.validar(color);
        color = ColorSettings.obtenerRGBPorCanal(color, canal);
        return color;
    }
    
}