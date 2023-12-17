package com.imagenes.ruido;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;

public class GUIAditivo extends JPanel {
    private static Image original;
    private static Image randImage;
    private static Image modificada;
    private static JPanel panelOriginalImage;
    private static JPanel panelRuidoImage;
    private static JSlider sliderRuido;

    public GUIAditivo() {
        setName("Aditivo");
        setSize(900, 900);
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
                original = OpenFile.abrirImagen();
                randImage = null;
                createImages(original);
            }
        });

        testImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                randImage = OpenFile.abrirImagenAleatoria("src\\TestImages");
                if (randImage != null) {
                    original = null;
                    createImages(randImage);
                }
            }
        });

        // Slider para el ruido
        sliderRuido = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        sliderRuido.setMajorTickSpacing(20);
        sliderRuido.setMinorTickSpacing(5);
        sliderRuido.setPaintTicks(true);
        sliderRuido.setPaintLabels(true);

        // Escucha de cambios en el slider
        sliderRuido.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (randImage != null) {
                    agregarRuido(randImage);
                }
                else {
                    agregarRuido(original);
                }
            }
        });

        // Panel de imagenes
        JPanel panelImage = new JPanel();
        panelOriginalImage = new JPanel();
        panelRuidoImage = new JPanel();
        panelImage.add(panelOriginalImage);
        panelImage.add(panelRuidoImage);
        // Agregar componentes al panel principal
        add(panelImageButtons);
        add(sliderRuido);
        add(panelImage);
        add(new JLabel("Nivel de Ruido:"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
    }

    protected static void createImages(Image original) {
        Image originalEscalada = ImageSettings.toBufferedImage(original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel jLabelOriginal = new JLabel(new ImageIcon(originalEscalada));
        panelOriginalImage.removeAll();
        panelOriginalImage.add(BorderLayout.CENTER, jLabelOriginal);
        panelOriginalImage.revalidate();
        panelOriginalImage.repaint();
    }

    private static void agregarRuido(Image original) {
        int nivelRuido = sliderRuido.getValue();
        modificada = Ruido.agregarRuidoAditivo(original, nivelRuido);
        Image modificadaEscalada = ImageSettings.toBufferedImage(modificada).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel jLabelRuido = new JLabel(new ImageIcon(modificadaEscalada));
        panelRuidoImage.removeAll();
        panelRuidoImage.add(BorderLayout.CENTER, jLabelRuido);
        panelRuidoImage.revalidate();
        panelRuidoImage.repaint();
    }
}
