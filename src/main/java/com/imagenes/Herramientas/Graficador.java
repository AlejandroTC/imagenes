package com.imagenes.Herramientas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficador {
    private JFreeChart grafica;
    private XYSeriesCollection series;
    private String ejeX, ejeY,titulo;
    
    public Graficador(String ejeX, String ejeY, String titulo) {
        this.grafica = null;
        this.series = new XYSeriesCollection();
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.titulo = titulo;
    }    
    
    public void agregarSerie(String nombre, double[] datos) {
        // Verificar si ya existe una serie con el mismo nombre
        int index = -1;
        for (int i = 0; i < series.getSeriesCount(); i++) {
            if (nombre.equals(series.getSeriesKey(i))) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            // No existe una serie con este nombre, crear una nueva serie
            XYSeries serie = new XYSeries(nombre);
            // agregar cada uno de los datos en la serie 
            for (int x = 0; x < datos.length; x++) {
                serie.add(x, datos[x]);
            }
            // agregamos la serie que se generó 
            this.series.addSeries(serie);
        } else {
            // La serie con el mismo nombre ya existe, puedes manejar este caso como desees
            // Por ejemplo, podrías generar un nombre único o mostrar un mensaje de error.
            System.err.println("La serie con el nombre '" + nombre + "' ya existe.");
        }
    }
    
    
    public void crearGrafica(){
        this.grafica = ChartFactory.createXYAreaChart(titulo, ejeX, ejeY, series);
    }
    // Nuevo Frame o ventana con la gráfica
     public void muestraGrafica(int x, int y){
        ChartFrame frame = new ChartFrame("Histograma de color", grafica);
        frame.setVisible(true);
        frame.setSize(500,370);
        frame.setLocation(x, y);
    }

    public JFreeChart getGrafica() {
        return this.grafica;
    }
}
