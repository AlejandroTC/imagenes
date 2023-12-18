package com.imagenes.ruido;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.imagenes.convolucion.Convolucion;
import com.imagenes.convolucion.ConvolucionFive;
import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;

public class GUISustractivo extends JPanel {
    private Image original;
    private Image randImage;
    private Image modificada;
    private Image modificadaConvolution;
    private Image modificadaConvolution2;
    private JPanel panelOriginalImage;
    private JPanel panelRuidoImage;
    private JPanel convolucionImage;
    private JPanel convolucionImage2;
    private JSlider sliderRuido;
    private JTextField divisor3;
    private JTextField divisor5;
    private Convolucion convolucionador;
    private ConvolucionFive convolucionadorFive;



    public GUISustractivo() {
        setName("Aditivo");
        setSize(600, 500);
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
        setupImageButtons();
        setupRuidoSlider();
        setupImagePanels();
        setupConvolucionButtons();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
    }

    private void setupImageButtons() {
        JPanel panelImageButtons = new JPanel();
        JButton selectImage = new JButton("Seleccionar Imagen");
        JButton testImage = new JButton("Usar imagen de prueba");

        panelImageButtons.add(selectImage);
        panelImageButtons.add(testImage);

        selectImage.addActionListener(e -> {
            original = OpenFile.abrirImagen();
            randImage = null;
            createImages(original);
        });

        testImage.addActionListener(e -> {
            randImage = OpenFile.abrirImagenAleatoria("src/TestImages");
            if (randImage != null) {
                original = null;
                createImages(randImage);
            }
        });

        add(panelImageButtons);
    }

    private void setupRuidoSlider() {
        sliderRuido = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        sliderRuido.setMajorTickSpacing(20);
        sliderRuido.setMinorTickSpacing(5);
        sliderRuido.setPaintTicks(true);
        sliderRuido.setPaintLabels(true);

        sliderRuido.addChangeListener(e -> {
            if (randImage != null) {
                agregarRuido(randImage);
            } else {
                agregarRuido(original);
            }
        });

        add(sliderRuido);
    }

    private void setupImagePanels() {
        JPanel panelImage = new JPanel();
        panelOriginalImage = new JPanel();
        panelRuidoImage = new JPanel();

        panelImage.add(panelOriginalImage);
        panelImage.add(panelRuidoImage);

        add(panelImage);
    }

    private void setupConvolucionButtons() {
        JPanel panelConvolutionButtons = new JPanel();
        JButton convolutionThree = new JButton("Convolución 3x3");
        JButton convolutionFive = new JButton("Convolución 5x5");
        JLabel divlabel3 = new JLabel("Divisor 3x3");
        divisor3 = new JTextField(5);
        JLabel divlabel5 = new JLabel("Divisor 5x5");
        divisor5 = new JTextField(5);

        panelConvolutionButtons.add(divlabel3);
        panelConvolutionButtons.add(divisor3);
        panelConvolutionButtons.add(convolutionThree);
        panelConvolutionButtons.add(divlabel5);
        panelConvolutionButtons.add(divisor5);
        panelConvolutionButtons.add(convolutionFive);

        convolutionThree.addActionListener(e -> usarConvolucion(3, modificada));
        convolutionFive.addActionListener(e -> usarConvolucion(5, modificada));

        add(panelConvolutionButtons);

        JPanel panelConvolutionImage = new JPanel();
        convolucionImage = new JPanel();
        convolucionImage2 = new JPanel();
        panelConvolutionImage.add(convolucionImage);
        panelConvolutionImage.add(convolucionImage2);

        add(panelConvolutionImage);
    }

    protected void createImages(Image original) {
        Image originalEscalada = ImageSettings.toBufferedImage(original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel jLabelOriginal = new JLabel(new ImageIcon(originalEscalada));
        panelOriginalImage.removeAll();
        panelOriginalImage.add(BorderLayout.CENTER, jLabelOriginal);
        panelOriginalImage.revalidate();
        panelOriginalImage.repaint();
    }

    private void agregarRuido(Image original) {
        int nivelRuido = sliderRuido.getValue();
        modificada = Ruido.agregarRuidoSustractivo(original, nivelRuido);
        Image modificadaEscalada = ImageSettings.toBufferedImage(modificada).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel jLabelRuido = new JLabel(new ImageIcon(modificadaEscalada));
        panelRuidoImage.removeAll();
        panelRuidoImage.add(BorderLayout.CENTER, jLabelRuido);
        panelRuidoImage.revalidate();
        panelRuidoImage.repaint();
    }

    private void usarConvolucion(int option, Image imagen) {
        try {
            if (option == 3) {
                int[] mascara = {1, 1, 1, 1, 4, 1, 1, 1, 1};
                convolucionador = new Convolucion(ImageSettings.toBufferedImage(imagen));
                modificadaConvolution = convolucionador.convolucionar(mascara, Integer.parseInt(divisor3.getText()));
                Image modificadaConvolutionEscalada = ImageSettings.toBufferedImage(modificadaConvolution).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
                JLabel jLabelConvolution = new JLabel(new ImageIcon(modificadaConvolutionEscalada));
                convolucionImage.removeAll();
                convolucionImage.add(BorderLayout.WEST, jLabelConvolution);
                convolucionImage.revalidate();
                convolucionImage.repaint();
            } else {
                int[] mascara = {1, 4, 6, 4, 1, 4, 16, 24, 16, 4, 6, 24, 36, 24, 6, 4, 16, 24, 16, 4, 1, 4, 6, 4, 1};
                convolucionadorFive = new ConvolucionFive(ImageSettings.toBufferedImage(imagen));
                modificadaConvolution2 = convolucionadorFive.convolucionar(mascara, Integer.parseInt(divisor5.getText()));
                Image modificadaConvolutionEscalada = ImageSettings.toBufferedImage(modificadaConvolution2).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
                JLabel jLabelConvolution = new JLabel(new ImageIcon(modificadaConvolutionEscalada));
                convolucionImage2.removeAll();
                convolucionImage2.add(BorderLayout.EAST, jLabelConvolution);
                convolucionImage2.revalidate();
                convolucionImage2.repaint();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor válido para el divisor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
