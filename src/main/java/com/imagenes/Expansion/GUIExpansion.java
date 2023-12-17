package com.imagenes.expansion;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.imagenes.herramientas.OpenFile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUIExpansion extends JPanel {
    private JLabel originalLabel;
    private JLabel expandidaLabel;
    private JSlider sliderR1;
    private JSlider sliderR2;
    private BufferedImage imagenOriginal;

    public GUIExpansion() {
        setName("Expansión");
        setSize(600, 500);
        initComponents();
    }
 
    private void initComponents() {
        originalLabel = new JLabel();
        expandidaLabel = new JLabel();

        sliderR1 = new JSlider(JSlider.HORIZONTAL, 0, 255, 50);
        sliderR2 = new JSlider(JSlider.HORIZONTAL, 0, 255, 50);

        sliderR1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                aplicarExpansionLineal();
            }
        });

        sliderR2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                aplicarExpansionLineal();
            }
        });
        
        JButton cargarImagenButton = new JButton("Cargar Imagen");
        JButton expansionLnButton = new JButton("Expansión Logarítmica");
        JButton expansionExpButton = new JButton("Expansión Exponencial");

        cargarImagenButton.addActionListener(e -> cargarImagen());
        expansionLnButton.addActionListener(e -> aplicarExpansionLn());
        expansionExpButton.addActionListener(e -> aplicarExpansionExp());

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(cargarImagenButton);
        botonesPanel.add(expansionLnButton);
        botonesPanel.add(expansionExpButton);

        JPanel imagenesPanel = new JPanel();
        imagenesPanel.setLayout(new GridLayout(1, 2));
        imagenesPanel.add(originalLabel);
        imagenesPanel.add(expandidaLabel);

        JPanel slidersPanel = new JPanel();
        slidersPanel.setLayout(new GridLayout(2, 2));
        slidersPanel.setSize(10,10);
        slidersPanel.add(new JLabel("R1:"));
        slidersPanel.add(sliderR1);
        slidersPanel.add(new JLabel("R2:"));
        slidersPanel.add(sliderR2);

        setLayout(new BorderLayout());
        add(imagenesPanel, BorderLayout.SOUTH);
        add(botonesPanel, BorderLayout.CENTER);
        add(slidersPanel, BorderLayout.NORTH);
    }

    private void cargarImagen() {
        OpenFile.abrirImagen();
    }
    
    

    private void mostrarImagenOriginal(BufferedImage imagen) {
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        originalLabel.setIcon(icono);
    }

    private void mostrarImagenExpandida(BufferedImage imagen) {
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        expandidaLabel.setIcon(icono);
    }

    private void aplicarExpansionLineal() {
        if (imagenOriginal != null) {
            int r1 = sliderR1.getValue();
            int r2 = sliderR2.getValue();

            Expansion.ExpansionHistograma expansionHistograma = new Expansion().new ExpansionHistograma();
            Image imagenExpandida = expansionHistograma.expansionLineal(r1, r2, imagenOriginal);
            mostrarImagenExpandida((BufferedImage) imagenExpandida);
        }
    }

    private void aplicarExpansionLn() {
        if (imagenOriginal != null) {
            Expansion.ExpansionHistograma expansionHistograma = new Expansion().new ExpansionHistograma();
            Image imagenExpandida = expansionHistograma.expansionLn(imagenOriginal);
            mostrarImagenExpandida((BufferedImage) imagenExpandida);
        }
    }

    private void aplicarExpansionExp() {
        if (imagenOriginal != null) {
            Expansion.ExpansionHistograma expansionHistograma = new Expansion().new ExpansionHistograma();
            Image imagenExpandida = expansionHistograma.expansionExp(imagenOriginal, 0.1);
            mostrarImagenExpandida((BufferedImage) imagenExpandida);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIExpansion().setVisible(true));
    }
}
