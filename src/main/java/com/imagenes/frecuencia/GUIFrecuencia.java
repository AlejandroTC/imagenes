package com.imagenes.frecuencia;

import javax.swing.*;

import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUIFrecuencia extends JPanel {
    private static Image Original;
    private static Image RandImage;
    private static JPanel panelOriginalImage;
    private static JPanel panelFrecuencias;
    private static JPanel panelResultantImage;

    public GUIFrecuencia() {
        setName("Procesamiento de Frecuencias");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
        // Panel de Botones - Imágenes
        JPanel panelImageButtons = new JPanel();
        JButton selectImage = new JButton("Seleccionar Imagen");
        panelImageButtons.add(selectImage);
        JButton testImage = new JButton("Usar imagen de prueba");
        panelImageButtons.add(testImage);

        // Funcionamiento Panel de Botones - Imágenes
        selectImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Original = OpenFile.abrirImagen();
                RandImage = null;
                createImages(Original);
                processImage(Original);
            }
        });

        testImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RandImage = OpenFile.abrirImagenAleatoria("src/TestImages");
                if (RandImage != null) {
                    Original = null;
                    createImages(RandImage);
                    processImage(RandImage);
                }
            }
        });

        // Paneles de Imágenes
        panelOriginalImage = new JPanel();
        panelFrecuencias = new JPanel();
        panelResultantImage = new JPanel();
        // Añadir componentes
        add(panelImageButtons);
        add(panelOriginalImage);
        add(panelFrecuencias);
        add(panelResultantImage);
    }

    private void createImages(Image original) {
        Image originalEscalada = ImageSettings.toBufferedImage(original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel jLabelOriginal = new JLabel(new ImageIcon(originalEscalada));
        panelOriginalImage.removeAll();
        panelOriginalImage.add(jLabelOriginal);
        revalidate();
        repaint();
    }

    private void processImage(Image original) {
        Gestor gestor = new Gestor();
        BufferedImage bImage = ImageSettings.toBufferedImage(original);
        NumeroComplejo[][] aux = gestor.obtenerDatosPorCanal(bImage, ColorSettings.CanalColor.VERDE);
        BufferedImage biFrecuencias = gestor.obtenerImagenFrecuencias(aux, bImage.getWidth(), bImage.getHeight(), true);
        BufferedImage resultante = gestor.obtenerImagenEspacial();

        JLabel JLabelFrecuencias = new JLabel(new ImageIcon(ImageSettings.toImage(biFrecuencias)));
        JLabel JLabelResultante = new JLabel(new ImageIcon(ImageSettings.toImage(resultante)));

        panelResultantImage.removeAll();
        panelFrecuencias.removeAll();
        panelResultantImage.setLayout(new BorderLayout());

        panelFrecuencias.add(JLabelFrecuencias);        
        panelResultantImage.add(JLabelResultante);

        revalidate();
        repaint();
    }
}
