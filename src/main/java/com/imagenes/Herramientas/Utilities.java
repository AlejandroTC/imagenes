package com.imagenes.herramientas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;

public class Utilities {
public static void calcularHistograma(Image imagen, String titulo, JPanel panel) {        
    //Contadores
        double[] contR = new double[256];
        double[] contG = new double[256];
        double[] contB = new double[256];
        // recorrer los pixeles de la imagen
        BufferedImage bi = ImageSettings.toBufferedImage(imagen);
        for(int i=0; i<bi.getWidth(); i++){
            for(int j=0; j<bi.getHeight(); j++){
                Color thisColor = new Color(bi.getRGB(i, j));
                contR[thisColor.getRed()] = contR[thisColor.getRed()]+1;
                contG[thisColor.getGreen()] = contG[thisColor.getGreen()]+1;
                contB[thisColor.getBlue()] = contB[thisColor.getBlue()]+1;
            }
        }
        Graficador graficador = new Graficador("Valor", "Frecuencia", titulo);
        graficador.agregarSerie("Rojo", contR);
        graficador.agregarSerie("Azul", contB);
        graficador.agregarSerie("Verde", contG);
        graficador.crearGrafica();
        //graficador.muestraGrafica(x, y);

        // Agregar la gráfica al panel
        ChartPanel chartPanel = new ChartPanel(graficador.getGrafica());
        chartPanel.setPreferredSize(new Dimension(300, 300));
        panel.add(chartPanel);
        panel.revalidate();
    }
}
