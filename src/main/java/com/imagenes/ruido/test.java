package com.imagenes.ruido;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.imagenes.convolucion.Convolucion;
import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;

//Version antigua del GUIAditivo

public class test extends JPanel {
    private static Image original;
    private static Image randImage;
    private static Image modificada;
    private static Image modificadaConvolution;
    private static JPanel panelOriginalImage;
    private static JPanel panelRuidoImage;
    private static JPanel panelConvolutionImage;
    private static JSlider sliderRuido;
    private static JTextField divisor;
    private static Convolucion convolucionador;

    public test() {
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
                randImage = OpenFile.abrirImagenAleatoria("src/TestImages");
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

        // Panel de botones de convolucion
        JPanel panelConvolutionButtons = new JPanel();
        JButton convolutionThree = new JButton("Convolución 3x3");
        JButton convolutionFive = new JButton("Convolución 5x5");
        panelConvolutionButtons.add(convolutionThree);
        panelConvolutionButtons.add(convolutionFive);
        JLabel divlabel = new JLabel("Valor del divisors");
        divisor = new JTextField(5);
        panelConvolutionButtons.add(divlabel);
        panelConvolutionButtons.add(divisor);
        // Funcionamiento Panel de Botones - Convolucion
        convolutionThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    usarConvolucion(3, modificada);
            }
        });

        convolutionFive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(original == null){
                    usarConvolucion(5, randImage);
                }
                else {
                    usarConvolucion(5, original);
                }
            }
        });

        // Panel de imagenes con convolución
        panelConvolutionImage = new JPanel();
        // Agregar componentes al panel principal
        add(panelImageButtons);
        add(sliderRuido);
        add(panelImage);
        add(panelConvolutionButtons);
        add(panelConvolutionImage);
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

    private static void usarConvolucion(int i, Image imagen){
        if(i == 3){
            convolucionador = new Convolucion(ImageSettings.toBufferedImage(imagen));
            int[] mascara =  new int[]{1, 1, 1, 1, 4, 1, 1, 1, 1};
            modificadaConvolution = convolucionador.convolucionar(mascara, Integer.parseInt(divisor.getText()));
            Image modificadaConvolutionEscalada = ImageSettings.toBufferedImage(modificadaConvolution).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
            JLabel jLabelConvolution = new JLabel(new ImageIcon(modificadaConvolutionEscalada));
            panelConvolutionImage.removeAll();
            panelConvolutionImage.add(BorderLayout.CENTER, jLabelConvolution);
            panelConvolutionImage.revalidate();
            panelConvolutionImage.repaint();
        }
        else{

        }
    }
}
